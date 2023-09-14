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
package de.symeda.sormas.api.epidata;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import de.symeda.sormas.api.CountryHelper;
import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.ImportIgnore;
import de.symeda.sormas.api.activityascase.ActivityAsCaseDto;
import de.symeda.sormas.api.exposure.ExposureDto;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.symptoms.DisabilityGrading;
import de.symeda.sormas.api.symptoms.LeprosyResult;
import de.symeda.sormas.api.utils.*;
import de.symeda.sormas.api.utils.pseudonymization.PseudonymizableDto;

@DependingOnFeatureType(featureType = {
	FeatureType.CASE_SURVEILANCE,
	FeatureType.CONTACT_TRACING })
public class EpiDataDto extends PseudonymizableDto {

	private static final long serialVersionUID = 6292411396563549093L;

	public static final String I18N_PREFIX = "EpiData";

	public static final String EXPOSURE_DETAILS_KNOWN = "exposureDetailsKnown";
	public static final String ACTIVITY_AS_CASE_DETAILS_KNOWN = "activityAsCaseDetailsKnown";
	public static final String CONTACT_WITH_SOURCE_CASE_KNOWN = "contactWithSourceCaseKnown";
	public static final String EXPOSURES = "exposures";
	public static final String ACTIVITIES_AS_CASE = "activitiesAsCase";
	public static final String AREA_INFECTED_ANIMALS = "areaInfectedAnimals";
	public static final String HIGH_TRANSMISSION_RISK_AREA = "highTransmissionRiskArea";
	public static final String LARGE_OUTBREAKS_AREA = "largeOutbreaksArea";
	public static final String CASE_DETECTION_METHOD = "caseDetectionMethod";
	public static final String CASE_DETECTION_METHOD_GROUP = "caseDetectionMethodGroup";
	public static final String FAMILY_HISTORY_OF_LEPROSY = "familyHistoryOfLeprosy";
	public static final String CONTACT_EXAMINATION_DONE = "contactExaminationDone";
	public static final String NO_OF_FAMILY_CONTACT = "noOfFamilyContact";
	public static final String NO_OF_NEIGHBOUR_CONTACT = "noOfNeighbourContact";
	public static final String NO_OF_SOCIAL_CONTACT = "noOfSocialContact";
	public static final String SKIN_TEST_POSITIVE = "skinSmearTestPositive";
	public static final String SKIN_TEST_LEPROSY_RESULT = "leprosyResult";

	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private YesNoUnknown exposureDetailsKnown;
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private YesNoUnknown activityAsCaseDetailsKnown;
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private YesNoUnknown contactWithSourceCaseKnown;
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private YesNoUnknown highTransmissionRiskArea;
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private YesNoUnknown largeOutbreaksArea;
	private CaseDetectionMethodGroup caseDetectionMethodGroup;
	private CaseDetectionMethod caseDetectionMethod;
	@Diseases({
		Disease.AFP,
		Disease.GUINEA_WORM,
		Disease.NEW_INFLUENZA,
		Disease.ANTHRAX,
		Disease.POLIO,
		Disease.UNDEFINED,
		Disease.OTHER })
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private YesNoUnknown areaInfectedAnimals;

	@Valid
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private List<ExposureDto> exposures = new ArrayList<>();

	@Valid
	@HideForCountries(countries = {CountryHelper.COUNTRY_CODE_NEPAL})
	private List<ActivityAsCaseDto> activitiesAsCase = new ArrayList<>();
	private MalariaEpiDataDto malariaEpiData;

	@Diseases({Disease.LEPROSY})
	private Boolean familyHistoryOfLeprosy;
	@Diseases({Disease.LEPROSY})
	private Boolean contactExaminationDone ;
	@Diseases({Disease.LEPROSY})
	private Integer noOfFamilyContact;
	@Diseases({Disease.LEPROSY})
	private Integer noOfNeighbourContact;
	@Diseases({Disease.LEPROSY})
	private Integer noOfSocialContact;
	@Diseases({Disease.LEPROSY})
	private Boolean skinSmearTestPositive;
	@Diseases({Disease.LEPROSY})
	private LeprosyResult leprosyResult;

	public YesNoUnknown getExposureDetailsKnown() {
		return exposureDetailsKnown;
	}

	public void setExposureDetailsKnown(YesNoUnknown exposureDetailsKnown) {
		this.exposureDetailsKnown = exposureDetailsKnown;
	}

	public YesNoUnknown getActivityAsCaseDetailsKnown() {
		return activityAsCaseDetailsKnown;
	}

	public void setActivityAsCaseDetailsKnown(YesNoUnknown activityAsCaseDetailsKnown) {
		this.activityAsCaseDetailsKnown = activityAsCaseDetailsKnown;
	}

	@ImportIgnore
	public List<ExposureDto> getExposures() {
		return exposures;
	}

	public void setExposures(List<ExposureDto> exposures) {
		this.exposures = exposures;
	}

	@ImportIgnore
	public List<ActivityAsCaseDto> getActivitiesAsCase() {
		return activitiesAsCase;
	}

	public void setActivitiesAsCase(List<ActivityAsCaseDto> activitiesAsCase) {
		this.activitiesAsCase = activitiesAsCase;
	}

	public YesNoUnknown getContactWithSourceCaseKnown() {
		return contactWithSourceCaseKnown;
	}

	public void setContactWithSourceCaseKnown(YesNoUnknown contactWithSourceCaseKnown) {
		this.contactWithSourceCaseKnown = contactWithSourceCaseKnown;
	}

	public YesNoUnknown getHighTransmissionRiskArea() {
		return highTransmissionRiskArea;
	}

	public void setHighTransmissionRiskArea(YesNoUnknown highTransmissionRiskArea) {
		this.highTransmissionRiskArea = highTransmissionRiskArea;
	}

	public YesNoUnknown getLargeOutbreaksArea() {
		return largeOutbreaksArea;
	}

	public void setLargeOutbreaksArea(YesNoUnknown largeOutbreaksArea) {
		this.largeOutbreaksArea = largeOutbreaksArea;
	}

	public YesNoUnknown getAreaInfectedAnimals() {
		return areaInfectedAnimals;
	}

	public void setAreaInfectedAnimals(YesNoUnknown areaInfectedAnimals) {
		this.areaInfectedAnimals = areaInfectedAnimals;
	}

	public static EpiDataDto build() {

		EpiDataDto epiData = new EpiDataDto();
		epiData.setUuid(DataHelper.createUuid());
		return epiData;
	}

	@Override
	public EpiDataDto clone() throws CloneNotSupportedException {
		EpiDataDto clone = (EpiDataDto) super.clone();
		List<ActivityAsCaseDto> activityAsCaseDtos = new ArrayList<>();
		for (ActivityAsCaseDto activityAsCase : getActivitiesAsCase()) {
			activityAsCaseDtos.add(activityAsCase.clone());
		}
		clone.getActivitiesAsCase().clear();
		clone.getActivitiesAsCase().addAll(activityAsCaseDtos);

		List<ExposureDto> exposureDtos = new ArrayList<>();
		for (ExposureDto exposure : getExposures()) {
			exposureDtos.add(exposure.clone());
		}
		clone.getExposures().clear();
		clone.getExposures().addAll(exposureDtos);

		return clone;
	}

	public MalariaEpiDataDto getMalariaEpiData() {
		return malariaEpiData;
	}

	public void setMalariaEpiData(MalariaEpiDataDto malariaEpiData) {
		this.malariaEpiData = malariaEpiData;
	}

	public CaseDetectionMethodGroup getCaseDetectionMethodGroup() {
		return caseDetectionMethodGroup;
	}

	public void setCaseDetectionMethodGroup(CaseDetectionMethodGroup caseDetectionMethodGroup) {
		this.caseDetectionMethodGroup = caseDetectionMethodGroup;
	}

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

	public void setLeprosyResult(LeprosyResult leprosyResult) {
		this.leprosyResult = leprosyResult;
	}
}
