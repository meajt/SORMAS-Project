package de.symeda.sormas.app.news.rest;

import android.util.Log;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Map;

import de.symeda.sormas.api.NewsDateFilter;
import de.symeda.sormas.api.event.RiskLevel;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.app.backend.region.Community;
import de.symeda.sormas.app.backend.region.District;
import de.symeda.sormas.app.backend.region.Region;
import de.symeda.sormas.app.news.News;

public class NewsFilterCriteria {
    @Expose
    @SerializedName("categories")
    String categories;

    @Expose
    @SerializedName("province")
    String provinceName;

    @Expose
    @SerializedName("district")
    String districtName;

    @Expose
    @SerializedName("palika")
    String palikaName;

    @Expose
    @SerializedName("ward")
    String ward;

    @Expose
    @SerializedName("village")
    String village;

    @Expose
    @SerializedName("page")
    Integer page;

    @Expose
    @SerializedName("search")
    String textFilter;
    @Expose
    @SerializedName("epidemiological_risk_level")
    String epidemiologicalRiskLevel;

    @Expose
    @SerializedName("start_date")
    String fromDateQuery;

    @Expose
    @SerializedName("end_date")
    String toDateQuery;

    Region region;
    District district;
    Community community;
    RiskLevel riskLevel;
    NewsDateFilter newsDateFilter;


    public Map<String, Object> toFilterMap() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        this.setProvinceName(region == null? null: region.getName());
        this.setDistrictName(district == null? null: district.getName());
        this.setPalikaName(community == null? null: community.getName());
        this.setEpidemiologicalRiskLevel(riskLevel == null? null: I18nProperties.getEnumCaption(riskLevel));
        if (newsDateFilter != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
           LocalDate localDate = LocalDate.now();
           toDateQuery = formatter.format(localDate);
           localDate = localDate.minusDays(newsDateFilter.getValue());
           fromDateQuery = formatter.format(localDate);
        }
        Map<String, Object> queryMap = gson.fromJson(gson.toJson(this), new TypeToken<Map<String, Object>>() {
        }.getType());
        queryMap.put("page", getPage());
        return queryMap;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getPalikaName() {
        return palikaName;
    }

    public void setPalikaName(String palikaName) {
        this.palikaName = palikaName;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getTextFilter() {
        return textFilter;
    }

    public void setTextFilter(String textFilter) {
        this.textFilter = textFilter;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getEpidemiologicalRiskLevel() {
        return epidemiologicalRiskLevel;
    }

    public void setEpidemiologicalRiskLevel(String epidemiologicalRiskLevel) {
        this.epidemiologicalRiskLevel = epidemiologicalRiskLevel;
    }

    public NewsDateFilter getNewsDateFilter() {
        return newsDateFilter;
    }

    public void setNewsDateFilter(NewsDateFilter newsDateFilter) {
        this.newsDateFilter = newsDateFilter;
    }

    public String getFromDateQuery() {
        return fromDateQuery;
    }

    public void setFromDateQuery(String fromDateQuery) {
        this.fromDateQuery = fromDateQuery;
    }

    public String getToDateQuery() {
        return toDateQuery;
    }

    public void setToDateQuery(String toDateQuery) {
        this.toDateQuery = toDateQuery;
    }
}
