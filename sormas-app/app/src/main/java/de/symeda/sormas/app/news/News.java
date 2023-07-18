package de.symeda.sormas.app.news;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.symeda.sormas.api.Disease;
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
    private  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
    public static News buildFromNewsResponse(NewsResponseDto responseDto) {
        News news = new News();
        news.setUuid(responseDto.getId()+"");
        news.setTitle(responseDto.getTitle());
        news.setNewsLink(responseDto.getNewsLink());
        news.setDate(responseDto.getDate().split(" ")[0]);
        news.setSummary(responseDto.getSummary());
        news.setRegion(responseDto.getProvince());
        try {
            Date linkDate = dateFormat.parse(responseDto.getDate());
            news.setCreationDate(linkDate);
            news.setLocalChangeDate(linkDate);
        }catch (Exception exe) {
            exe.printStackTrace();
        }
        return news;
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
}
