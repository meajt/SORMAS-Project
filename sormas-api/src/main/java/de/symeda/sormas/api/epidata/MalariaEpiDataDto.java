package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.infrastructure.community.CommunityReferenceDto;
import de.symeda.sormas.api.infrastructure.district.DistrictReferenceDto;
import de.symeda.sormas.api.infrastructure.facility.FacilityReferenceDto;
import de.symeda.sormas.api.infrastructure.region.RegionReferenceDto;
import de.symeda.sormas.api.symptoms.PlasmodiumSpecies;
import de.symeda.sormas.api.utils.*;
import de.symeda.sormas.api.utils.pseudonymization.PseudonymizableDto;

import java.util.Date;

public class MalariaEpiDataDto extends PseudonymizableDto {
    public static final String I18N_PREFIX = "MalariaEpiData";
    public static final String LENGTH_OF_RESIDENCE_Y_Y = "lengthOfResidenceYY";
    public static final String LENGTH_OF_RESIDENCE_M_M = "lengthOfResidenceMM";
    public static final String RESIDENT_REGION = "residentRegion";
    public static final String RESIDENT_DISTRICT = "residentDistrict";
    public static final String RESIDENT_COMMUNITY = "residentCommunity";
    public static final String RESIDENT_WARD_NO = "residentWardNo";
    public static final String TRAVEL_ADDRESS_WITH_IN_COUNTY = "travelAddressWithInCounty";
    public static final String TRAVEL_DATE_WITH_IN_COUNTY = "travelDateWithInCounty";
    public static final String TRAVEL_ADDRESS_OUTSIDE_COUNTY = "travelAddressOutsideCounty";
    public static final String TRAVEL_DATE_OUTSIDE_COUNTY = "travelDateOutsideCounty";
    public static final String TYPE_OF_PREVENTING_MEASURES = "typeOfPreventingMeasures";
    public static final String DRUG_NAME = "drugName";
    public static final String DOSE_NAME = "doseName";
    public static final String DOSE_START_DATE = "doseStartDate";
    public static final String DOSE_END_DATE = "doseEndDate";
    public static final String CONTACT_WITH_MALARIA_CASES_DETAIL = "contactWithMalariaCasesDetail";
    public static final String BLOOD_TRANSFUSION = "bloodTransfusion";
    public static final String HISTORY_OF_CONFIRMED = "historyOfConfirmed";
    public static final String HAS_PREVIOUS_MALARIA = "hasPreviousMalaria";
    public static final String PAST_REGION = "pastRegion";
    public static final String PAST_DATE = "pastDate";
    public static final String PAST_DISTRICT = "pastDistrict";
    public static final String PAST_COMMUNITY = "pastCommunity";
    public static final String PAST_WARD_NO = "pastWardNo";
    public static final String FACILITY = "facility";
    public static final String PLASMODIUM_SPECIES = "plasmodiumSpecies";
    public static final String HAS_TREATED_N_M_T_P = "hasTreatedNMTP";
    public static final String CASE_DETECTION_METHOD = "caseDetectionMethod";
    public static final String ACTIVE_CASE_DETECTION = "activeCaseDetection";

    private CaseDetectionMethodGroup caseDetectionMethodGroup;
    private CaseDetectionMethod caseDetectionMethod;

    @PersonalData
    private Integer lengthOfResidenceYY;
    @PersonalData
    private Integer lengthOfResidenceMM;
    @PersonalData
    private RegionReferenceDto residentRegion;
    @PersonalData
    private DistrictReferenceDto residentDistrict;
    @PersonalData
    private CommunityReferenceDto residentCommunity;

    private Integer residentWardNo;
    private String travelAddressWithInCounty;
    @PersonalData
    private Date travelDateWithInCounty;

    private String travelAddressOutsideCounty;
    @PersonalData
    private Date travelDateOutsideCounty;
    @PersonalData
    private PreventiveMeasures typeOfPreventingMeasures;
    @PersonalData
    private String drugName;
    private String doseName;
    @PersonalData
    private Date doseStartDate;
    private Date doseEndDate;
    @PersonalData
    private String contactWithMalariaCasesDetail;
    @PersonalData
    private Boolean bloodTransfusion;
    @PersonalData
    private Boolean historyOfConfirmed;
    @PersonalData
    private Boolean hasPreviousMalaria;
    private RegionReferenceDto pastRegion;
    private Date pastDate;
    @PersonalData
    private DistrictReferenceDto pastDistrict;
    @PersonalData
    private CommunityReferenceDto pastCommunity;

    private Integer pastWardNo;
    @PersonalData
    private FacilityReferenceDto facility;
    @PersonalData
    private PlasmodiumSpecies plasmodiumSpecies;
    @PersonalData
    private YesNoUnknown hasTreatedNMTP;

    public CaseDetectionMethodGroup getCaseDetectionMethod() {
        return caseDetectionMethodGroup;
    }

    public void setCaseDetectionMethod(CaseDetectionMethodGroup caseDetectionMethodGroup) {
        this.caseDetectionMethodGroup = caseDetectionMethodGroup;
    }

    public CaseDetectionMethod getActiveCaseDetection() {
        return caseDetectionMethod;
    }

    public void setActiveCaseDetection(CaseDetectionMethod caseDetectionMethod) {
        this.caseDetectionMethod = caseDetectionMethod;
    }

    public Date getPastDate() {
        return pastDate;
    }

    public void setPastDate(Date pastDate) {
        this.pastDate = pastDate;
    }

    public Integer getLengthOfResidenceYY() {
        return lengthOfResidenceYY;
    }

    public void setLengthOfResidenceYY(Integer lengthOfResidenceYY) {
        this.lengthOfResidenceYY = lengthOfResidenceYY;
    }

    public Integer getLengthOfResidenceMM() {
        return lengthOfResidenceMM;
    }

    public void setLengthOfResidenceMM(Integer lengthOfResidenceMM) {
        this.lengthOfResidenceMM = lengthOfResidenceMM;
    }

    public RegionReferenceDto getResidentRegion() {
        return residentRegion;
    }

    public void setResidentRegion(RegionReferenceDto residentRegion) {
        this.residentRegion = residentRegion;
    }

    public DistrictReferenceDto getResidentDistrict() {
        return residentDistrict;
    }

    public void setResidentDistrict(DistrictReferenceDto residentDistrict) {
        this.residentDistrict = residentDistrict;
    }

    public CommunityReferenceDto getResidentCommunity() {
        return residentCommunity;
    }

    public void setResidentCommunity(CommunityReferenceDto residentCommunity) {
        this.residentCommunity = residentCommunity;
    }

    public String getTravelAddressWithInCounty() {
        return travelAddressWithInCounty;
    }

    public void setTravelAddressWithInCounty(String travelAddressWithInCounty) {
        this.travelAddressWithInCounty = travelAddressWithInCounty;
    }

    public Date getTravelDateWithInCounty() {
        return travelDateWithInCounty;
    }

    public void setTravelDateWithInCounty(Date travelDateWithInCounty) {
        this.travelDateWithInCounty = travelDateWithInCounty;
    }

    public String getTravelAddressOutsideCounty() {
        return travelAddressOutsideCounty;
    }

    public void setTravelAddressOutsideCounty(String travelAddressOutsideCounty) {
        this.travelAddressOutsideCounty = travelAddressOutsideCounty;
    }

    public Date getTravelDateOutsideCounty() {
        return travelDateOutsideCounty;
    }

    public void setTravelDateOutsideCounty(Date travelDateOutsideCounty) {
        this.travelDateOutsideCounty = travelDateOutsideCounty;
    }

    public PreventiveMeasures getTypeOfPreventingMeasures() {
        return typeOfPreventingMeasures;
    }

    public void setTypeOfPreventingMeasures(PreventiveMeasures typeOfPreventingMeasures) {
        this.typeOfPreventingMeasures = typeOfPreventingMeasures;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

    public String getContactWithMalariaCasesDetail() {
        return contactWithMalariaCasesDetail;
    }

    public void setContactWithMalariaCasesDetail(String contactWithMalariaCasesDetail) {
        this.contactWithMalariaCasesDetail = contactWithMalariaCasesDetail;
    }

    public Boolean getBloodTransfusion() {
        return bloodTransfusion;
    }

    public void setBloodTransfusion(Boolean bloodTransfusion) {
        this.bloodTransfusion = bloodTransfusion;
    }

    public Boolean getHistoryOfConfirmed() {
        return historyOfConfirmed;
    }

    public void setHistoryOfConfirmed(Boolean historyOfConfirmed) {
        this.historyOfConfirmed = historyOfConfirmed;
    }

    public Boolean getHasPreviousMalaria() {
        return hasPreviousMalaria;
    }

    public void setHasPreviousMalaria(Boolean hasPreviousMalaria) {
        this.hasPreviousMalaria = hasPreviousMalaria;
    }

    public FacilityReferenceDto getFacility() {
        return facility;
    }

    public void setFacility(FacilityReferenceDto facility) {
        this.facility = facility;
    }

    public PlasmodiumSpecies getPlasmodiumSpecies() {
        return plasmodiumSpecies;
    }

    public void setPlasmodiumSpecies(PlasmodiumSpecies plasmodiumSpecies) {
        this.plasmodiumSpecies = plasmodiumSpecies;
    }

    public YesNoUnknown getHasTreatedNMTP() {
        return hasTreatedNMTP;
    }

    public void setHasTreatedNMTP(YesNoUnknown hasTreatedNMTP) {
        this.hasTreatedNMTP = hasTreatedNMTP;
    }

    public Integer getResidentWardNo() {
        return residentWardNo;
    }

    public void setResidentWardNo(Integer residentWardNo) {
        this.residentWardNo = residentWardNo;
    }

    public Date getDoseStartDate() {
        return doseStartDate;
    }

    public void setDoseStartDate(Date doseStartDate) {
        this.doseStartDate = doseStartDate;
    }

    public Date getDoseEndDate() {
        return doseEndDate;
    }

    public void setDoseEndDate(Date doseEndDate) {
        this.doseEndDate = doseEndDate;
    }

    public RegionReferenceDto getPastRegion() {
        return pastRegion;
    }

    public void setPastRegion(RegionReferenceDto pastRegion) {
        this.pastRegion = pastRegion;
    }

    public DistrictReferenceDto getPastDistrict() {
        return pastDistrict;
    }

    public void setPastDistrict(DistrictReferenceDto pastDistrict) {
        this.pastDistrict = pastDistrict;
    }

    public CommunityReferenceDto getPastCommunity() {
        return pastCommunity;
    }

    public void setPastCommunity(CommunityReferenceDto pastCommunity) {
        this.pastCommunity = pastCommunity;
    }

    public Integer getPastWardNo() {
        return pastWardNo;
    }

    public void setPastWardNo(Integer pastWardNo) {
        this.pastWardNo = pastWardNo;
    }
}
