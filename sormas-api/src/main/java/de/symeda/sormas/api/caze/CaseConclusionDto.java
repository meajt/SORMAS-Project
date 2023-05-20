package de.symeda.sormas.api.caze;

import de.symeda.sormas.api.infrastructure.community.CommunityReferenceDto;
import de.symeda.sormas.api.infrastructure.district.DistrictReferenceDto;
import de.symeda.sormas.api.infrastructure.region.RegionReferenceDto;
import de.symeda.sormas.api.symptoms.PlasmodiumSpecies;

public class CaseConclusionDto {
    public static final String I18N_PREFIX = "CaseConclusion";
    public static final String REGION_REFERENCE = "regionReference";
    public static final String DISTRICT_REFERENCE = "districtReference";
    public static final String COMMUNITY_REFERENCE_D = "communityReferenceD";
    public static final String WARD_NO = "wardNo";
    public static final String SPECIES = "species";
    public static final String CASE_CLASSIFICATION = "caseClassification";
    private RegionReferenceDto regionReference;
    private DistrictReferenceDto districtReference;
    private CommunityReferenceDto communityReferenceD;
    private Integer wardNo;
    private PlasmodiumSpecies species;
    private MalariaCaseClassification caseClassification;

    public RegionReferenceDto getRegionReference() {
        return regionReference;
    }

    public void setRegionReference(RegionReferenceDto regionReference) {
        this.regionReference = regionReference;
    }

    public DistrictReferenceDto getDistrictReference() {
        return districtReference;
    }

    public void setDistrictReference(DistrictReferenceDto districtReference) {
        this.districtReference = districtReference;
    }

    public CommunityReferenceDto getCommunityReferenceD() {
        return communityReferenceD;
    }

    public void setCommunityReferenceD(CommunityReferenceDto communityReferenceD) {
        this.communityReferenceD = communityReferenceD;
    }

    public Integer getWardNo() {
        return wardNo;
    }

    public void setWardNo(Integer wardNo) {
        this.wardNo = wardNo;
    }

    public PlasmodiumSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PlasmodiumSpecies species) {
        this.species = species;
    }

    public MalariaCaseClassification getCaseClassification() {
        return caseClassification;
    }

    public void setCaseClassification(MalariaCaseClassification caseClassification) {
        this.caseClassification = caseClassification;
    }
}
