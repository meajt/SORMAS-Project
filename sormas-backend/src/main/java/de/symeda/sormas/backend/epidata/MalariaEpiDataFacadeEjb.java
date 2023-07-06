package de.symeda.sormas.backend.epidata;

import de.symeda.sormas.api.epidata.MalariaEpiDataDto;
import de.symeda.sormas.backend.infrastructure.community.CommunityFacadeEjb;
import de.symeda.sormas.backend.infrastructure.community.CommunityService;
import de.symeda.sormas.backend.infrastructure.district.DistrictFacadeEjb;
import de.symeda.sormas.backend.infrastructure.district.DistrictService;
import de.symeda.sormas.backend.infrastructure.facility.FacilityFacadeEjb;
import de.symeda.sormas.backend.infrastructure.facility.FacilityService;
import de.symeda.sormas.backend.infrastructure.region.RegionFacadeEjb;
import de.symeda.sormas.backend.infrastructure.region.RegionService;
import de.symeda.sormas.backend.util.DtoHelper;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "MalariaEpiDataFacade")
public class MalariaEpiDataFacadeEjb {

    @EJB
    RegionService regionService;
    @EJB
    DistrictService districtService;
    @EJB
    CommunityService communityService;
    @EJB
    FacilityService facilityService;


    public MalariaEpiData fillOrBuildEntity(MalariaEpiDataDto source, MalariaEpiData target, boolean checkChangeDate) {
        if (source == null) {
            return null;
        }

        target = DtoHelper.fillOrBuildEntity(source, target, MalariaEpiData::new, checkChangeDate);
        target.setCaseDetectionMethod(source.getCaseDetectionMethod());
        target.setActiveCaseDetection(source.getActiveCaseDetection());
        target.setLengthOfResidenceYY(source.getLengthOfResidenceYY());
        target.setLengthOfResidenceMM(source.getLengthOfResidenceMM());
        target.setResidentRegion(regionService.getByReferenceDto(source.getResidentRegion()));
        target.setResidentDistrict(districtService.getByReferenceDto(source.getResidentDistrict()));
        target.setResidentCommunity(communityService.getByReferenceDto(source.getResidentCommunity()));
        target.setResidentWardNo(source.getResidentWardNo());
        target.setTravelAddressWithInCounty(source.getTravelAddressWithInCounty());
        target.setTravelDateWithInCounty(source.getTravelDateWithInCounty());
        target.setTravelAddressOutsideCounty(source.getTravelAddressOutsideCounty());
        target.setTravelDateOutsideCounty(source.getTravelDateOutsideCounty());
        target.setTypeOfPreventingMeasures(source.getTypeOfPreventingMeasures());
        target.setDrugName(source.getDrugName());
        target.setDoseName(source.getDoseName());
        target.setDoseStartDate(source.getDoseStartDate());
        target.setDoseEndDate(source.getDoseEndDate());
        target.setContactWithMalariaCasesDetail(source.getContactWithMalariaCasesDetail());
        target.setBloodTransfusion(source.getBloodTransfusion());
        target.setHistoryOfConfirmed(source.getHistoryOfConfirmed());
        target.setHasPreviousMalaria(source.getHasPreviousMalaria());
        target.setPastRegion(regionService.getByReferenceDto(source.getPastRegion()));
        target.setPastDistrict(districtService.getByReferenceDto(source.getPastDistrict()));
        target.setPastCommunity(communityService.getByReferenceDto(source.getPastCommunity()));
        target.setPastWardNo(source.getPastWardNo());
        target.setFacility(facilityService.getByReferenceDto(source.getFacility()));
        target.setPlasmodiumSpecies(source.getPlasmodiumSpecies());
        target.setHasTreatedNMTP(source.getHasTreatedNMTP());
        return target;
    }

    public static MalariaEpiDataDto toDto(MalariaEpiData source) {
        if (source == null) {
            return null;
        }
        MalariaEpiDataDto target = new MalariaEpiDataDto();
        DtoHelper.fillDto(target, source);
        target.setCaseDetectionMethod(source.getCaseDetectionMethod());
        target.setActiveCaseDetection(source.getActiveCaseDetection());
        target.setLengthOfResidenceYY(source.getLengthOfResidenceYY());
        target.setLengthOfResidenceMM(source.getLengthOfResidenceMM());
        target.setResidentRegion(RegionFacadeEjb.toReferenceDto(source.getResidentRegion()));
        target.setResidentDistrict(DistrictFacadeEjb.toReferenceDto(source.getResidentDistrict()));
        target.setResidentCommunity(CommunityFacadeEjb.toReferenceDto(source.getResidentCommunity()));
        target.setResidentWardNo(source.getResidentWardNo());
        target.setTravelAddressWithInCounty(source.getTravelAddressWithInCounty());
        target.setTravelDateWithInCounty(source.getTravelDateWithInCounty());
        target.setTravelAddressOutsideCounty(source.getTravelAddressOutsideCounty());
        target.setTravelDateOutsideCounty(source.getTravelDateOutsideCounty());
        target.setTypeOfPreventingMeasures(source.getTypeOfPreventingMeasures());
        target.setDrugName(source.getDrugName());
        target.setDoseName(source.getDoseName());
        target.setDoseStartDate(source.getDoseStartDate());
        target.setDoseEndDate(source.getDoseEndDate());
        target.setContactWithMalariaCasesDetail(source.getContactWithMalariaCasesDetail());
        target.setBloodTransfusion(source.getBloodTransfusion());
        target.setHistoryOfConfirmed(source.getHistoryOfConfirmed());
        target.setHasPreviousMalaria(source.getHasPreviousMalaria());
        target.setPastRegion(RegionFacadeEjb.toReferenceDto(source.getPastRegion()));
        target.setPastDistrict(DistrictFacadeEjb.toReferenceDto(source.getPastDistrict()));
        target.setPastCommunity(CommunityFacadeEjb.toReferenceDto(source.getPastCommunity()));
        target.setPastWardNo(source.getPastWardNo());
        target.setFacility(FacilityFacadeEjb.toReferenceDto(source.getFacility()));
        target.setPlasmodiumSpecies(source.getPlasmodiumSpecies());
        target.setHasTreatedNMTP(source.getHasTreatedNMTP());
        return target;
    }

    @LocalBean
    @Stateless
    public static class MalariaEpiDataFacadeEjbLocal extends MalariaEpiDataFacadeEjb{}
}
