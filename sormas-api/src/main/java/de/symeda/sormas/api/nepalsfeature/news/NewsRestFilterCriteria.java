package de.symeda.sormas.api.nepalsfeature.news;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import de.symeda.sormas.api.NewsDateFilter;
import de.symeda.sormas.api.event.RiskLevel;
import de.symeda.sormas.api.i18n.I18nProperties;

public class NewsRestFilterCriteria {
    @Expose
    @SerializedName("categories")
    private String categories;

    @Expose
    @SerializedName("province")
    private  String provinceName;

    @Expose
    @SerializedName("district")
    private String districtName;

    @Expose
    @SerializedName("palika")
    private String palikaName;

    @Expose
    @SerializedName("ward")
    private String ward;

    @Expose
    @SerializedName("village")
    private String village;

    @Expose
    @SerializedName("page")
    private Integer page;

    @Expose
    @SerializedName("search")
    private String textFilter;
    @Expose
    @SerializedName("epidemiological_risk_level")
    private String epidemiologicalRiskLevel;

    @Expose
    @SerializedName("start_date")
    private String fromDateQuery;

    @Expose
    @SerializedName("offset")
    private Integer offset;
    @Expose
    @SerializedName("limit")
    private Integer limit;
    @Expose
    @SerializedName("end_date")
    private String toDateQuery;
    private RiskLevel riskLevel;
    private NewsDateFilter newsDateFilter;

    public Map<String, Object> toFilterMap() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        this.setEpidemiologicalRiskLevel(riskLevel == null? null: I18nProperties.getEnumCaption(riskLevel));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (newsDateFilter != null) {
           LocalDate localDate = LocalDate.now();
           toDateQuery = formatter.format(localDate);
           localDate = localDate.minusDays(newsDateFilter.getValue());
           fromDateQuery = formatter.format(localDate);
        }
        Map<String, Object> queryMap = gson.fromJson(gson.toJson(this), new TypeToken<Map<String, Object>>() {
        }.getType());
        queryMap.put("limit", limit);
        queryMap.put("offset", offset);
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
