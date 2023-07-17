package de.symeda.sormas.app.news.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class NewsFilterCriteria {
    @SerializedName("categories")
    String categories;

    @SerializedName("province")
    String province;

    @SerializedName("district")
    String district;

    @SerializedName("palika")
    String palika;

    @SerializedName("ward")
    String ward;

    @SerializedName("village")
    String village;

    @SerializedName("page")
    Integer page;

    public Map<String, Object> toFilterMap() {
      Gson gson = new Gson();
      return gson.fromJson(gson.toJson(this), new TypeToken<Map<String, Object>>(){}.getType());
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPalika() {
        return palika;
    }

    public void setPalika(String palika) {
        this.palika = palika;
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
}
