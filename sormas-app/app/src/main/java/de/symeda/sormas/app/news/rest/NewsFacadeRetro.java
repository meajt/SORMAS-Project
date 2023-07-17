package de.symeda.sormas.app.news.rest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsFacadeRetro {
    @GET("api/news")
    Call<NewsBodyResponse> getNews(@QueryMap Map<String, Object> queryMap);
}
