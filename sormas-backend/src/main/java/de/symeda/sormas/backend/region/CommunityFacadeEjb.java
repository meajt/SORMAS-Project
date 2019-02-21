/*******************************************************************************
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2018 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.symeda.sormas.backend.region;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Validations;
import de.symeda.sormas.api.region.CommunityCriteria;
import de.symeda.sormas.api.region.CommunityDto;
import de.symeda.sormas.api.region.CommunityFacade;
import de.symeda.sormas.api.region.CommunityReferenceDto;
import de.symeda.sormas.api.region.DistrictReferenceDto;
import de.symeda.sormas.api.utils.ValidationRuntimeException;
import de.symeda.sormas.backend.user.User;
import de.symeda.sormas.backend.user.UserService;
import de.symeda.sormas.backend.util.DtoHelper;
import de.symeda.sormas.backend.util.ModelConstants;

@Stateless(name = "CommunityFacade")
public class CommunityFacadeEjb implements CommunityFacade {

	@PersistenceContext(unitName = ModelConstants.PERSISTENCE_UNIT_NAME)
	protected EntityManager em;

	@EJB
	private CommunityService communityService;
	@EJB
	private UserService userService;
	@EJB
	private DistrictService districtService;

	@Override
	public List<CommunityReferenceDto> getAllByDistrict(String districtUuid) {
		
		District district = districtService.getByUuid(districtUuid);
		
		return district.getCommunities().stream()
				.map(f -> toReferenceDto(f))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<CommunityDto> getAllAfter(Date date) {
		return communityService.getAllAfter(date, null).stream()
			.map(c -> toDto(c))
			.collect(Collectors.toList());
	}

	@Override
	public List<CommunityDto> getIndexList(CommunityCriteria criteria) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CommunityDto> cq = cb.createQuery(CommunityDto.class);
		Root<Community> community = cq.from(Community.class);
		Join<Community, District> district = community.join(Community.DISTRICT, JoinType.LEFT);
		Join<District, Region> region = district.join(District.REGION, JoinType.LEFT);

		if (criteria != null) {
			Predicate filter = communityService.buildCriteriaFilter(criteria, cb, community);
			if (filter != null) {
				cq.where(filter);
			}
		}
		
		cq.multiselect(community.get(Community.CREATION_DATE), community.get(Community.CHANGE_DATE),
				community.get(Community.UUID), community.get(Community.NAME),
				region.get(Region.UUID), region.get(Region.NAME),
				district.get(District.UUID), district.get(District.NAME));
		cq.orderBy(cb.asc(region.get(Region.NAME)), cb.asc(district.get(District.NAME)), cb.asc(community.get(Community.NAME)));

		List<CommunityDto> resultList = em.createQuery(cq).getResultList();
		return resultList;
	}
	
	@Override
	public List<String> getAllUuids(String userUuid) {
		
		User user = userService.getByUuid(userUuid);
		
		if (user == null) {
			return Collections.emptyList();
		}
		
		return communityService.getAllUuids(user);
	}
	
	@Override
	public CommunityDto getByUuid(String uuid) {
		return toDto(communityService.getByUuid(uuid));
	}
	
	@Override
	public List<CommunityDto> getByUuids(List<String> uuids) {
		return communityService.getByUuids(uuids)
				.stream()
				.map(c -> toDto(c))
				.collect(Collectors.toList());
	}
	
	@Override
	public CommunityReferenceDto getCommunityReferenceByUuid(String uuid) {
		return toReferenceDto(communityService.getByUuid(uuid));
	}
	
	@Override
	public void saveCommunity(CommunityDto dto) throws ValidationRuntimeException {
		Community community = communityService.getByUuid(dto.getUuid());
		
		if (dto.getDistrict() == null) {
			throw new ValidationRuntimeException(I18nProperties.getValidationError(Validations.validDistrict));
		}

		community = fillOrBuildEntity(dto, community);
		communityService.ensurePersisted(community);
	}

	@Override
	public List<CommunityReferenceDto> getByName(String name, DistrictReferenceDto districtRef) {
		return communityService.getByName(name, districtService.getByReferenceDto(districtRef)).stream().map(c -> toReferenceDto(c)).collect(Collectors.toList());
	}
	
	public static CommunityReferenceDto toReferenceDto(Community entity) {
		if (entity == null) {
			return null;
		}
		CommunityReferenceDto dto = new CommunityReferenceDto(entity.getUuid(), entity.toString());
		return dto;
	}
	
	private CommunityDto toDto(Community entity) {
		if (entity == null) {
			return null;
		}
		CommunityDto dto = new CommunityDto();
		DtoHelper.fillDto(dto, entity);
		
		dto.setName(entity.getName());
		dto.setDistrict(DistrictFacadeEjb.toReferenceDto(entity.getDistrict()));
		dto.setRegion(RegionFacadeEjb.toReferenceDto(entity.getDistrict().getRegion()));

		return dto;
	}
	
	private Community fillOrBuildEntity(@NotNull CommunityDto source, Community target) {
		if (target == null) {
			target = new Community();
			target.setUuid(source.getUuid());
		}
		
		DtoHelper.validateDto(source, target);
		
		target.setName(source.getName());
		target.setDistrict(districtService.getByReferenceDto(source.getDistrict()));
		
		return target;
	}
	
	@LocalBean
	@Stateless
	public static class CommunityFacadeEjbLocal extends CommunityFacadeEjb {
	}
}
