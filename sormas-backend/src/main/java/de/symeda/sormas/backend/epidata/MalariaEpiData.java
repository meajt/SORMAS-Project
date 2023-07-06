package de.symeda.sormas.backend.epidata;

import de.symeda.auditlog.api.Audited;
import de.symeda.sormas.api.epidata.ActiveCaseDetection;
import de.symeda.sormas.api.epidata.CaseDetectionMethod;
import de.symeda.sormas.api.epidata.PreventiveMeasures;
import de.symeda.sormas.api.symptoms.PlasmodiumSpecies;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.backend.common.AbstractDomainObject;
import de.symeda.sormas.backend.infrastructure.community.Community;
import de.symeda.sormas.backend.infrastructure.district.District;
import de.symeda.sormas.backend.infrastructure.facility.Facility;
import de.symeda.sormas.backend.infrastructure.region.Region;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Audited
public class MalariaEpiData extends AbstractDomainObject {
    private CaseDetectionMethod caseDetectionMethod;
    private ActiveCaseDetection activeCaseDetection;
    private Integer lengthOfResidenceYY;
    private Integer lengthOfResidenceMM;
    private Region residentRegion;
    private District residentDistrict;
    private Community residentCommunity;
    private Integer residentWardNo;
    private String travelAddressWithInCounty;
    private Date travelDateWithInCounty;
    private String travelAddressOutsideCounty;
    private Date travelDateOutsideCounty;
    private PreventiveMeasures typeOfPreventingMeasures;
    private String drugName;
    private String doseName;
    private Date doseStartDate;
    private Date doseEndDate;
    private String contactWithMalariaCasesDetail;
    private Boolean bloodTransfusion;
    private Boolean historyOfConfirmed;
    private Boolean hasPreviousMalaria;
    private Region pastRegion;
    private Date pastDate;
    private District pastDistrict;
    private Community pastCommunity;
    private Integer pastWardNo;
    private Facility facility;
    private PlasmodiumSpecies plasmodiumSpecies;
    private YesNoUnknown hasTreatedNMTP;

    @Enumerated(EnumType.STRING)
    public CaseDetectionMethod getCaseDetectionMethod() {
        return caseDetectionMethod;
    }
    public void setCaseDetectionMethod(CaseDetectionMethod caseDetectionMethod) {
        this.caseDetectionMethod = caseDetectionMethod;
    }

    @Enumerated(EnumType.STRING)
    public ActiveCaseDetection getActiveCaseDetection() {
        return activeCaseDetection;
    }

    public void setActiveCaseDetection(ActiveCaseDetection activeCaseDetection) {
        this.activeCaseDetection = activeCaseDetection;
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

    @OneToOne
    public Region getResidentRegion() {
        return residentRegion;
    }

    public void setResidentRegion(Region residentRegion) {
        this.residentRegion = residentRegion;
    }
    @OneToOne

    public District getResidentDistrict() {
        return residentDistrict;
    }

    public void setResidentDistrict(District residentDistrict) {
        this.residentDistrict = residentDistrict;
    }
    @OneToOne

    public Community getResidentCommunity() {
        return residentCommunity;
    }

    public void setResidentCommunity(Community residentCommunity) {
        this.residentCommunity = residentCommunity;
    }

    public Integer getResidentWardNo() {
        return residentWardNo;
    }

    public void setResidentWardNo(Integer residentWardNo) {
        this.residentWardNo = residentWardNo;
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

    @OneToOne
    public Region getPastRegion() {
        return pastRegion;
    }

    public void setPastRegion(Region pastRegion) {
        this.pastRegion = pastRegion;
    }

    public Date getPastDate() {
        return pastDate;
    }

    public void setPastDate(Date pastDate) {
        this.pastDate = pastDate;
    }

    @OneToOne
    public District getPastDistrict() {
        return pastDistrict;
    }

    public void setPastDistrict(District pastDistrict) {
        this.pastDistrict = pastDistrict;
    }

    @OneToOne
    public Community getPastCommunity() {
        return pastCommunity;
    }

    public void setPastCommunity(Community pastCommunity) {
        this.pastCommunity = pastCommunity;
    }

    public Integer getPastWardNo() {
        return pastWardNo;
    }

    public void setPastWardNo(Integer pastWardNo) {
        this.pastWardNo = pastWardNo;
    }

    @OneToOne
    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
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
}
