package de.symeda.sormas.api.nepalsfeature.news;

import de.symeda.sormas.api.NewsDateFilter;
import de.symeda.sormas.api.event.RiskLevel;
import de.symeda.sormas.api.infrastructure.community.CommunityReferenceDto;
import de.symeda.sormas.api.infrastructure.district.DistrictReferenceDto;
import de.symeda.sormas.api.infrastructure.region.RegionReferenceDto;
import de.symeda.sormas.api.utils.criteria.BaseCriteria;

import java.util.Date;

public class NewsCriteria extends BaseCriteria {
    public static final String REGION = "region";
    public static final String DISTRICT = "district";
    public static final String COMMUNITY = "community";
    public final static String RISK_LEVE = "riskLevel";
    public final static String START_DATE = "startDate";
    public final static String END_DATE = "endDate";
    private RegionReferenceDto region;
    private DistrictReferenceDto district;
    private CommunityReferenceDto community;
    private RiskLevel riskLevel;
    private Date startDate;
    private Date endDate;

    public RegionReferenceDto getRegion() {
        return region;
    }

    public void setRegion(RegionReferenceDto region) {
        this.region = region;
    }

    public DistrictReferenceDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictReferenceDto district) {
        this.district = district;
    }

    public CommunityReferenceDto getCommunity() {
        return community;
    }

    public void setCommunity(CommunityReferenceDto community) {
        this.community = community;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
