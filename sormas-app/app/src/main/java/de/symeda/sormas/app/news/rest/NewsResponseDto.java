package de.symeda.sormas.app.news.rest;

import com.google.gson.annotations.SerializedName;

public class NewsResponseDto {
    @SerializedName("id")
    int id;

    @SerializedName("date")
    String date;

    @SerializedName("news_link")
    String newsLink;

    @SerializedName("title")
    String title;

    @SerializedName("summary")
    String summary;

    @SerializedName("categories")
    String categories;

    @SerializedName("area")
    String area;

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

    @SerializedName("news_source")
    String newsSource;

    @SerializedName("epidemiological_risk_level")
    String epidemiologicalRiskLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getEpidemiologicalRiskLevel() {
        return epidemiologicalRiskLevel;
    }

    public void setEpidemiologicalRiskLevel(String epidemiologicalRiskLevel) {
        this.epidemiologicalRiskLevel = epidemiologicalRiskLevel;
    }
}
