package de.symeda.sormas.app.news.rest;

import com.google.gson.annotations.SerializedName;

public class NewsMetaResponseDto {
    @SerializedName("path")
    String path;

    @SerializedName("per_page")
    int perPage;

    @SerializedName("to")
    int to;

    @SerializedName("total")
    int total;

    @SerializedName("current_page")
    int currentPage;

    @SerializedName("from")
    int from;

    @SerializedName("last_page")
    int lastPage;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
}
