package de.symeda.sormas.app.backend.news;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.app.backend.common.PseudonymizableAdo;

public class News extends PseudonymizableAdo {
    private String newsLink;
    private String title;
    private String summary;
    private Disease diseaseCategory;
    private String newsSource;

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
}
