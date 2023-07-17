package de.symeda.sormas.app.news.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

import de.symeda.sormas.api.action.ActionPriority;
import de.symeda.sormas.app.backend.region.Community;
import de.symeda.sormas.app.backend.region.District;
import de.symeda.sormas.app.backend.region.Region;

public class NewsFilterCriteria {
    final String HIGH_RISK= "High risk";
    final String MODERATE_RISK= "Moderate risk";
    final String LOW_RISK= "Low risk";
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

    Region region;
    District district;
    Community community;
    ActionPriority priority;


    public Map<String, Object> toFilterMap() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        this.setProvinceName(region == null? null: region.getName());
        this.setDistrictName(district == null? null: district.getName());
        this.setPalikaName(community == null? null: community.getName());
        this.setEpidemiologicalRiskLevel(actionPriorityToFilterRiskString(priority));
        return gson.fromJson(gson.toJson(this), new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public String actionPriorityToFilterRiskString(ActionPriority priority) {
        if (priority == ActionPriority.HIGH)
            return HIGH_RISK;
        if (priority == ActionPriority.NORMAL)
            return MODERATE_RISK;
        if (priority == ActionPriority.LOW)
            return LOW_RISK;
        return null;
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

    public ActionPriority getPriority() {
        return priority;
    }

    public void setPriority(ActionPriority priority) {
        this.priority = priority;
    }

    public String getEpidemiologicalRiskLevel() {
        return epidemiologicalRiskLevel;
    }

    public void setEpidemiologicalRiskLevel(String epidemiologicalRiskLevel) {
        this.epidemiologicalRiskLevel = epidemiologicalRiskLevel;
    }
}
