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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.symeda.sormas.backend.epidata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import de.symeda.auditlog.api.Audited;
import de.symeda.sormas.api.epidata.*;
import de.symeda.sormas.api.epidata.CaseDetectionMethod;
import de.symeda.sormas.api.symptoms.DisabilityGrading;
import de.symeda.sormas.api.symptoms.LeprosyResult;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.backend.activityascase.ActivityAsCase;
import de.symeda.sormas.backend.common.AbstractDomainObject;
import de.symeda.sormas.backend.common.NotExposedToApi;
import de.symeda.sormas.backend.exposure.Exposure;

import static de.symeda.sormas.api.utils.FieldConstraints.CHARACTER_LIMIT_BIG;

@Entity
@Audited
public class EpiData extends AbstractDomainObject {

	private static final long serialVersionUID = -8294812479501735785L;

	public static final String TABLE_NAME = "epidata";

	public static final String CONTACT_WITH_SOURCE_CASE_KNOWN = "contactWithSourceCaseKnown";
	public static final String EXPOSURES = "exposures";
	public static final String ACTIVITIES_AS_CASE = "activitiesAsCase";

	private YesNoUnknown exposureDetailsKnown;
	private YesNoUnknown activityAsCaseDetailsKnown;
	private YesNoUnknown contactWithSourceCaseKnown;
	private YesNoUnknown highTransmissionRiskArea;
	private YesNoUnknown largeOutbreaksArea;
	private YesNoUnknown areaInfectedAnimals;

	private List<Exposure> exposures = new ArrayList<>();
	private List<ActivityAsCase> activitiesAsCase = new ArrayList<>();
	@NotExposedToApi
	private Date changeDateOfEmbeddedLists;
	private MalariaEpiData malariaEpiData;
	private CaseDetectionMethodGroup caseDetectionMethodGroup;
	private CaseDetectionMethod caseDetectionMethod;
	private Boolean familyHistoryOfLeprosy;
	private Boolean contactExaminationDone;
	private Integer noOfFamilyContact;
	private Integer noOfNeighbourContact;
	private Integer noOfSocialContact;
	private Boolean skinSmearTestPositive;
	private LeprosyResult leprosyResult;
	private YesNoUnknown exposureToMothOrOthers;
	private ShapuExposureType shapuExposureType;
	private DirectIndirectExposure directIndirectExposure;
	private ContactArea contactArea;
	private ContactItem contactItem;
	private String otherExposureType;
	private Date exposureDate;
	private String remark;
	private String otherContactArea;
	private String otherContactItem;

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getExposureDetailsKnown() {
		return exposureDetailsKnown;
	}

	public void setExposureDetailsKnown(YesNoUnknown exposureDetailsKnown) {
		this.exposureDetailsKnown = exposureDetailsKnown;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getActivityAsCaseDetailsKnown() {
		return activityAsCaseDetailsKnown;
	}

	public void setActivityAsCaseDetailsKnown(YesNoUnknown activityAsCaseDetailsKnown) {
		this.activityAsCaseDetailsKnown = activityAsCaseDetailsKnown;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = Exposure.EPI_DATA)
	public List<Exposure> getExposures() {
		return exposures;
	}

	public void setExposures(List<Exposure> exposures) {
		this.exposures = exposures;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = Exposure.EPI_DATA)
	public List<ActivityAsCase> getActivitiesAsCase() {
		return activitiesAsCase;
	}

	public void setActivitiesAsCase(List<ActivityAsCase> activitiesAsCase) {
		this.activitiesAsCase = activitiesAsCase;
	}

	/**
	 * This change date has to be set whenever exposures are modified
	 */
	public Date getChangeDateOfEmbeddedLists() {
		return changeDateOfEmbeddedLists;
	}

	public void setChangeDateOfEmbeddedLists(Date changeDateOfEmbeddedLists) {
		this.changeDateOfEmbeddedLists = changeDateOfEmbeddedLists;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getAreaInfectedAnimals() {
		return areaInfectedAnimals;
	}

	public void setAreaInfectedAnimals(YesNoUnknown areaInfectedAnimals) {
		this.areaInfectedAnimals = areaInfectedAnimals;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getHighTransmissionRiskArea() {
		return highTransmissionRiskArea;
	}

	public void setHighTransmissionRiskArea(YesNoUnknown highTransmissionRiskArea) {
		this.highTransmissionRiskArea = highTransmissionRiskArea;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getLargeOutbreaksArea() {
		return largeOutbreaksArea;
	}

	public void setLargeOutbreaksArea(YesNoUnknown largeOutbreaksArea) {
		this.largeOutbreaksArea = largeOutbreaksArea;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getContactWithSourceCaseKnown() {
		return contactWithSourceCaseKnown;
	}

	public void setContactWithSourceCaseKnown(YesNoUnknown contactWithSourceCaseKnown) {
		this.contactWithSourceCaseKnown = contactWithSourceCaseKnown;
	}
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	public MalariaEpiData getMalariaEpiData() {
		return malariaEpiData;
	}

	public void setMalariaEpiData(MalariaEpiData malariaEpiData) {
		this.malariaEpiData = malariaEpiData;
	}

	@Enumerated(EnumType.STRING)
	public CaseDetectionMethodGroup getCaseDetectionMethodGroup() {
		return caseDetectionMethodGroup;
	}

	public void setCaseDetectionMethodGroup(CaseDetectionMethodGroup caseDetectionMethodGroup) {
		this.caseDetectionMethodGroup = caseDetectionMethodGroup;
	}

	@Enumerated(EnumType.STRING)
	public CaseDetectionMethod getCaseDetectionMethod() {
		return caseDetectionMethod;
	}

	public void setCaseDetectionMethod(CaseDetectionMethod caseDetectionMethod) {
		this.caseDetectionMethod = caseDetectionMethod;
	}

	public Boolean getFamilyHistoryOfLeprosy() {
		return familyHistoryOfLeprosy;
	}

	public void setFamilyHistoryOfLeprosy(Boolean familyHistoryOfLeprosy) {
		this.familyHistoryOfLeprosy = familyHistoryOfLeprosy;
	}

	public Boolean getContactExaminationDone() {
		return contactExaminationDone;
	}

	public void setContactExaminationDone(Boolean contactExaminationDone) {
		this.contactExaminationDone = contactExaminationDone;
	}

	public Integer getNoOfFamilyContact() {
		return noOfFamilyContact;
	}

	public void setNoOfFamilyContact(Integer noOfFamilyContact) {
		this.noOfFamilyContact = noOfFamilyContact;
	}

	public Integer getNoOfNeighbourContact() {
		return noOfNeighbourContact;
	}

	public void setNoOfNeighbourContact(Integer noOfNeighbourContact) {
		this.noOfNeighbourContact = noOfNeighbourContact;
	}

	public Integer getNoOfSocialContact() {
		return noOfSocialContact;
	}

	public void setNoOfSocialContact(Integer noOfSocialContact) {
		this.noOfSocialContact = noOfSocialContact;
	}

	public Boolean getSkinSmearTestPositive() {
		return skinSmearTestPositive;
	}

	public void setSkinSmearTestPositive(Boolean skinSmearTestPositive) {
		this.skinSmearTestPositive = skinSmearTestPositive;
	}

	public LeprosyResult getLeprosyResult() {
		return leprosyResult;
	}

	public void setLeprosyResult(LeprosyResult result) {
		this.leprosyResult = result;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getExposureToMothOrOthers() {
		return exposureToMothOrOthers;
	}

	public void setExposureToMothOrOthers(YesNoUnknown exposureToMothOrOthers) {
		this.exposureToMothOrOthers = exposureToMothOrOthers;
	}

	@Enumerated(EnumType.STRING)
	public ShapuExposureType getShapuExposureType() {
		return shapuExposureType;
	}

	public void setShapuExposureType(ShapuExposureType shapuExposureType) {
		this.shapuExposureType = shapuExposureType;
	}

	@Enumerated(EnumType.STRING)
	public DirectIndirectExposure getDirectIndirectExposure() {
		return directIndirectExposure;
	}

	public void setDirectIndirectExposure(DirectIndirectExposure directIndirectExposure) {
		this.directIndirectExposure = directIndirectExposure;
	}

	@Enumerated(EnumType.STRING)
	public ContactArea getContactArea() {
		return contactArea;
	}

	public void setContactArea(ContactArea contactArea) {
		this.contactArea = contactArea;
	}

	@Enumerated(EnumType.STRING)
	public ContactItem getContactItem() {
		return contactItem;
	}

	public void setContactItem(ContactItem contactItem) {
		this.contactItem = contactItem;
	}

	public String getOtherExposureType() {
		return otherExposureType;
	}

	public void setOtherExposureType(String otherExposureType) {
		this.otherExposureType = otherExposureType;
	}

	public Date getExposureDate() {
		return exposureDate;
	}

	public void setExposureDate(Date exposureDate) {
		this.exposureDate = exposureDate;
	}

	@Column(length = CHARACTER_LIMIT_BIG)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOtherContactArea() {
		return otherContactArea;
	}

	public void setOtherContactArea(String otherContactArea) {
		this.otherContactArea = otherContactArea;
	}

	public String getOtherContactItem() {
		return otherContactItem;
	}

	public void setOtherContactItem(String otherContactItem) {
		this.otherContactItem = otherContactItem;
	}
}
