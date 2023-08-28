package de.symeda.sormas.api.nepalsfeature.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsBodyResponse {
    @SerializedName("data")
    private List<NewsDto> newsList;
    @SerializedName("meta")
    private NewsMetaResponseDto newsMetaResponse;

    public List<NewsDto> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsDto> newsList) {
        this.newsList = newsList;
    }

    public NewsMetaResponseDto getNewsMetaResponse() {
        return newsMetaResponse;
    }

    public void setNewsMetaResponse(NewsMetaResponseDto newsMetaResponse) {
        this.newsMetaResponse = newsMetaResponse;
    }
}
