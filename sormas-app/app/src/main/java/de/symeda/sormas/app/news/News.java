package de.symeda.sormas.app.news;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.event.RiskLevel;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.app.backend.common.PseudonymizableAdo;
import de.symeda.sormas.app.news.rest.NewsResponseDto;

public class News extends PseudonymizableAdo {
    private String newsLink;
    private String title;
    private String summary;
    private Disease diseaseCategory;
    private String newsSource;
    private String date;
    private String region;
    private RiskLevel riskLevel;
    private  static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
    public static News buildFromNewsResponse(NewsResponseDto responseDto) {
        News news = new News();
        news.setUuid(responseDto.getId()+"");
        news.setTitle(responseDto.getTitle());
        news.setNewsLink(responseDto.getNewsLink());
        news.setDate(responseDto.getDate().split(" ")[0]);
        news.setSummary(responseDto.getSummary());
        news.setRegion(responseDto.getProvince());
        news.setRiskLevel(actionPriorityFromCaption(responseDto.getEpidemiologicalRiskLevel()));
        news.setNewsSource(responseDto.getNewsSource());
        try {
            Date linkDate = dateFormat.parse(responseDto.getDate());
            news.setCreationDate(linkDate);
            news.setLocalChangeDate(linkDate);
        }catch (Exception exe) {
            exe.printStackTrace();
        }
        return news;
    }

    private static RiskLevel actionPriorityFromCaption(String priorityCaption) {
        if (I18nProperties.getEnumCaption(RiskLevel.HIGH).equals(priorityCaption))
            return RiskLevel.HIGH;
        if (I18nProperties.getEnumCaption(RiskLevel.MODERATE).equals(priorityCaption))
            return RiskLevel.MODERATE.MODERATE;
        if (I18nProperties.getEnumCaption(RiskLevel.LOW).equals(priorityCaption))
            return RiskLevel.LOW;
        return RiskLevel.UNKNOWN;
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

    public Disease getDiseaseCategory() {
        return diseaseCategory;
    }

    public void setDiseaseCategory(Disease diseaseCategory) {
        this.diseaseCategory = diseaseCategory;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
}
