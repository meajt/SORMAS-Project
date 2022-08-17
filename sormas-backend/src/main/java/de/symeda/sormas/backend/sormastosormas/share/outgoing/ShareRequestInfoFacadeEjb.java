/*
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2022 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package de.symeda.sormas.backend.sormastosormas.share.outgoing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.symeda.sormas.api.i18n.Captions;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.sormastosormas.SormasServerDescriptor;
import de.symeda.sormas.api.sormastosormas.share.ShareRequestCriteria;
import de.symeda.sormas.api.sormastosormas.share.ShareRequestDetailsDto;
import de.symeda.sormas.api.sormastosormas.share.ShareRequestIndexDto;
import de.symeda.sormas.api.sormastosormas.share.outgoing.ShareRequestInfoFacade;
import de.symeda.sormas.api.utils.SortProperty;
import de.symeda.sormas.backend.sormastosormas.access.SormasToSormasDiscoveryService;
import de.symeda.sormas.backend.sormastosormas.share.ShareDataBuilderHelper;
import de.symeda.sormas.backend.user.User;
import de.symeda.sormas.backend.user.UserService;
import de.symeda.sormas.backend.util.DtoHelper;
import de.symeda.sormas.backend.util.ModelConstants;
import de.symeda.sormas.backend.util.Pseudonymizer;
import de.symeda.sormas.backend.util.QueryHelper;

@Stateless(name = "ShareRequestInfoFacade")
public class ShareRequestInfoFacadeEjb implements ShareRequestInfoFacade {

	@PersistenceContext(unitName = ModelConstants.PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@EJB
	private ShareRequestInfoService shareRequestInfoService;

	@EJB
	private SormasToSormasDiscoveryService sormasToSormasDiscoveryService;
	@EJB
	private ShareDataBuilderHelper dataBuilderHelper;
	@EJB
	private UserService userService;

	@Override
	public List<ShareRequestIndexDto> getIndexList(ShareRequestCriteria criteria, Integer offset, Integer size, List<SortProperty> sortProperties) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ShareRequestIndexDto> cq = cb.createQuery(ShareRequestIndexDto.class);
		Root<ShareRequestInfo> requestRoot = cq.from(ShareRequestInfo.class);
		Path<SormasToSormasShareInfo> sharesJoin = requestRoot.join(ShareRequestInfo.SHARES);
		Join<ShareRequestInfo, User> senderJoin = requestRoot.join(ShareRequestInfo.SENDER, JoinType.LEFT);

		cq.multiselect(
			requestRoot.get(ShareRequestInfo.UUID),
			requestRoot.get(ShareRequestInfo.CREATION_DATE),
			requestRoot.get(ShareRequestInfo.DATA_TYPE),
			requestRoot.get(ShareRequestInfo.REQUEST_STATUS),
			sharesJoin.get(SormasToSormasShareInfo.ORGANIZATION_ID),
			cb.concat(cb.concat(senderJoin.get(User.FIRST_NAME), " "), senderJoin.get(User.LAST_NAME)),
			sharesJoin.get(SormasToSormasShareInfo.OWNERSHIP_HANDED_OVER),
			requestRoot.get(ShareRequestInfo.COMMENT));

		Predicate filter = null;
		if (criteria != null) {
			filter = shareRequestInfoService.buildCriteriaFilter(criteria, cb, requestRoot);
		}

		if (filter != null) {
			cq.where(filter);
		}

		List<Order> order = new ArrayList<>();
		if (sortProperties != null && sortProperties.size() > 0) {
			for (SortProperty sortProperty : sortProperties) {
				Expression<?> expression;
				switch (sortProperty.propertyName) {
				case ShareRequestIndexDto.UUID:
				case ShareRequestIndexDto.CREATION_DATE:
				case ShareRequestIndexDto.DATA_TYPE:
				case ShareRequestIndexDto.COMMENT:
					expression = requestRoot.get(sortProperty.propertyName);
					break;
				case ShareRequestIndexDto.STATUS:
					expression = requestRoot.get(ShareRequestInfo.REQUEST_STATUS);
					break;
				case ShareRequestIndexDto.SENDER_NAME:
					expression = cb.concat(senderJoin.get(User.FIRST_NAME), senderJoin.get(User.LAST_NAME));
					break;
				case ShareRequestIndexDto.ORGANIZATION_ID:
				case ShareRequestIndexDto.OWNERSHIP_HANDED_OVER:
					expression = sharesJoin.get(sortProperty.propertyName);
					break;
				default:
					throw new IllegalArgumentException(sortProperty.propertyName);
				}
				order.add(sortProperty.ascending ? cb.asc(expression) : cb.desc(expression));
			}
		}

		cq.orderBy(order);

		List<ShareRequestIndexDto> requests = QueryHelper.getResultList(em, cq, offset, size);

		if (!requests.isEmpty()) {
			Map<String, SormasServerDescriptor> serverDescriptorMap = sormasToSormasDiscoveryService.getAllAvailableServers()
				.stream()
				.collect(Collectors.toMap(SormasServerDescriptor::getId, Function.identity()));

			requests.forEach(request -> {
				String organizationId = request.getOrganizationId();
				SormasServerDescriptor serverDescriptor = serverDescriptorMap.get(organizationId);

				request.setOrganizationName(serverDescriptor != null ? serverDescriptor.getName() : organizationId);
			});
		}

		return requests;
	}

	@Override
	public long count(ShareRequestCriteria criteria) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ShareRequestInfo> requestRoot = cq.from(ShareRequestInfo.class);

		Predicate filter = null;
		if (criteria != null) {
			filter = shareRequestInfoService.buildCriteriaFilter(criteria, cb, requestRoot);
		}

		if (filter != null) {
			cq.where(filter);
		}

		cq.select(cb.count(requestRoot));

		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public ShareRequestDetailsDto getShareRequestDetails(String requestUuid) {
		ShareRequestInfo requestInfo = shareRequestInfoService.getByUuid(requestUuid);

		ShareRequestDetailsDto details = new ShareRequestDetailsDto();
		DtoHelper.fillDto(details, requestInfo);
		details.setDataType(requestInfo.getDataType());
		details.setStatus(requestInfo.getRequestStatus());

		Pseudonymizer pseudonymizer = Pseudonymizer.getDefault(userService::hasRight, I18nProperties.getCaption(Captions.inaccessibleValue));
		details.setCases(
			requestInfo.getShares()
				.stream()
				.map(SormasToSormasShareInfo::getCaze)
				.filter(Objects::nonNull)
				.map(c -> dataBuilderHelper.getCasePreview(c, pseudonymizer))
				.collect(Collectors.toList()));
		details.setContacts(
			requestInfo.getShares()
				.stream()
				.map(SormasToSormasShareInfo::getContact)
				.filter(Objects::nonNull)
				.map(c -> dataBuilderHelper.getContactPreview(c, pseudonymizer))
				.collect(Collectors.toList()));
		details.setEvents(
			requestInfo.getShares()
				.stream()
				.map(SormasToSormasShareInfo::getEvent)
				.filter(Objects::nonNull)
				.map(c -> dataBuilderHelper.getEventPreview(c, pseudonymizer))
				.collect(Collectors.toList()));
		details.setEventParticipants(
			requestInfo.getShares()
				.stream()
				.map(SormasToSormasShareInfo::getEventParticipant)
				.filter(Objects::nonNull)
				.map(c -> dataBuilderHelper.getEventParticipantPreview(c, pseudonymizer))
				.collect(Collectors.toList()));

		return details;
	}

	@LocalBean
	@Stateless
	public static class ShareRequestInfoFacadeEjbLocal extends ShareRequestInfoFacadeEjb {

	}
}
