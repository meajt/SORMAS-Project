package de.symeda.sormas.app.news.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsBodyResponse {
    @SerializedName("data")
    private List<NewsResponseDto> newsList;
    @SerializedName("meta")
    private NewsMetaResponseDto newsMetaResponse;

    public List<NewsResponseDto> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsResponseDto> newsList) {
        this.newsList = newsList;
    }

    public NewsMetaResponseDto getNewsMetaResponse() {
        return newsMetaResponse;
    }

    public void setNewsMetaResponse(NewsMetaResponseDto newsMetaResponse) {
        this.newsMetaResponse = newsMetaResponse;
    }
}
