package de.symeda.sormas.utils;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import de.symeda.sormas.api.facility.FacilityType;
import de.symeda.sormas.backend.caze.Case;
import de.symeda.sormas.backend.event.Event;
import de.symeda.sormas.backend.event.EventGroup;
import de.symeda.sormas.backend.event.EventParticipant;
import de.symeda.sormas.backend.facility.Facility;
import de.symeda.sormas.backend.location.Location;
import de.symeda.sormas.backend.person.Person;
import de.symeda.sormas.backend.region.Community;
import de.symeda.sormas.backend.region.District;
import de.symeda.sormas.backend.region.Region;
import de.symeda.sormas.backend.user.User;
import de.symeda.sormas.backend.util.AbstractDomainObjectJoins;

public class EventJoins<T> extends AbstractDomainObjectJoins<T, Event> {

	private Join<Event, User> reportingUser;
	private Join<Event, User> responsibleUser;

	private Join<Event, Location> location;
	private Join<Location, Region> region;
	private Join<Location, District> district;
	private Join<Location, Community> community;
	private Join<Location, Facility> facility;
	private Join<Location, FacilityType> facilityType;

	private Join<Event, EventParticipant> eventParticipants;
	private Join<EventParticipant, Person> eventParticipantPersons;
	private Join<EventParticipant, Case> eventParticipantCases;

	private Join<Event, EventGroup> eventGroup;

	public EventJoins(From<T, Event> event) {
		super(event);
	}

	public Join<Event, User> getReportingUser() {
		return getOrCreate(reportingUser, Event.REPORTING_USER, JoinType.LEFT, this::setReportingUser);
	}

	public void setReportingUser(Join<Event, User> reportingUser) {
		this.reportingUser = reportingUser;
	}

	public Join<Event, User> getResponsibleUser() {
		return getOrCreate(responsibleUser, Event.RESPONSIBLE_USER, JoinType.LEFT, this::setResponsibleUser);
	}

	public void setResponsibleUser(Join<Event, User> responsibleUser) {
		this.responsibleUser = responsibleUser;
	}

	public Join<Event, Location> getLocation() {
		return getOrCreate(location, Event.EVENT_LOCATION, JoinType.LEFT, this::setLocation);
	}

	public void setLocation(Join<Event, Location> location) {
		this.location = location;
	}

	public Join<Location, Region> getRegion() {
		return getOrCreate(region, Location.REGION, JoinType.LEFT, getLocation(), this::setRegion);
	}

	public void setRegion(Join<Location, Region> region) {
		this.region = region;
	}

	public Join<Location, District> getDistrict() {
		return getOrCreate(district, Location.DISTRICT, JoinType.LEFT, getLocation(), this::setDistrict);
	}

	public void setDistrict(Join<Location, District> district) {
		this.district = district;
	}

	public Join<Location, Community> getCommunity() {
		return getOrCreate(community, Location.COMMUNITY, JoinType.LEFT, getLocation(), this::setCommunity);
	}

	public void setCommunity(Join<Location, Community> community) {
		this.community = community;
	}

	public Join<Location, Facility> getFacility() {
		return getOrCreate(facility, Location.FACILITY, JoinType.LEFT, getLocation(), this::setFacility);
	}

	public void setFacility(Join<Location, Facility> facility) {
		this.facility = facility;
	}

	public Join<Location, FacilityType> getFacilityType() {
		return getOrCreate(facilityType, Location.FACILITY_TYPE, JoinType.LEFT, getLocation(), this::setFacilityType);
	}

	public void setFacilityType(Join<Location, FacilityType> facilityType) {
		this.facilityType = facilityType;
	}

	public Join<Event, EventParticipant> getEventParticipants() {
		return getOrCreate(eventParticipants, Event.EVENT_PERSONS, JoinType.LEFT, this::setEventParticipants);
	}

	public void setEventParticipants(Join<Event, EventParticipant> eventParticipants) {
		this.eventParticipants = eventParticipants;
	}

	public Join<EventParticipant, Person> getEventParticipantPersons() {
		return getOrCreate(eventParticipantPersons, EventParticipant.PERSON, JoinType.LEFT, getEventParticipants(), this::setEventParticipantPersons);
	}

	public void setEventParticipantPersons(Join<EventParticipant, Person> eventParticipantPersons) {
		this.eventParticipantPersons = eventParticipantPersons;
	}

	public Join<EventParticipant, Case> getEventParticipantCases() {
		return getOrCreate(
			eventParticipantCases,
			EventParticipant.RESULTING_CASE,
			JoinType.LEFT,
			getEventParticipants(),
			this::setEventParticipantCases);
	}

	public void setEventParticipantCases(Join<EventParticipant, Case> eventParticipantCases) {
		this.eventParticipantCases = eventParticipantCases;
	}

	public Join<Event, EventGroup> getEventGroup() {
		return getOrCreate(eventGroup, Event.EVENT_GROUPS, JoinType.LEFT, this::setEventGroup);
	}

	public void setEventGroup(Join<Event, EventGroup> eventGroup) {
		this.eventGroup = eventGroup;
	}
}
