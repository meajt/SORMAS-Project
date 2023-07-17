package de.symeda.sormas.app.news;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.app.backend.news.News;
import de.symeda.sormas.app.news.rest.NewsBodyResponse;
import de.symeda.sormas.app.news.rest.NewsFacadeRetro;
import de.symeda.sormas.app.news.rest.NewsFilterCriteria;
import de.symeda.sormas.app.news.rest.NewsResponseDto;
import de.symeda.sormas.app.news.rest.NewsRetroProvider;
import de.symeda.sormas.app.util.Consumer;

public class NewsListViewModel extends ViewModel {
    private LiveData<PagedList<News>> newsList;
    private NewsDataFactory newsDataFactory;
    private static Context context;

    private static final Integer NEWS_LOAD_SIZE = 10;
    private final Integer NEWS_PAGE_SIZE = 16;

    public NewsListViewModel() {
        newsDataFactory = new NewsDataFactory();
        newsDataFactory.setCriteria(new NewsFilterCriteria());
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(NEWS_LOAD_SIZE)
                .setPageSize(NEWS_PAGE_SIZE)
                .build();
        LivePagedListBuilder newsPageListBuilder = new LivePagedListBuilder(newsDataFactory, config);
        newsList = newsPageListBuilder.build();
    }

    void notifyCriteriaUpdated() {
        if (newsList.getValue() != null) {
            newsList.getValue().getDataSource().invalidate();
            if (!newsList.getValue().isEmpty()) {
                newsList.getValue().loadAround(0);
            }
        }
    }

    public void setContext(Context context) {
        NewsListViewModel.context = context;
    }

    public NewsFilterCriteria getNewsFilterCriteria() {
        return newsDataFactory.getCriteria();
    }

    public LiveData<PagedList<News>> getNewsList() {
        return newsList;
    }

    public static class NewsDataSource extends PositionalDataSource<News> {
        NewsFilterCriteria criteria;
        public NewsDataSource(NewsFilterCriteria criteria) {
            this.criteria = criteria;
        }
        @Override
        public void loadInitial(@NonNull LoadInitialParams loadInitialParams, @NonNull LoadInitialCallback<News> loadInitialCallback) {
            pullData(criteria, newsResponse -> {
                List<News> news = newsResponse.getNewsList().stream().map(News::buildFromNewsResponse).collect(Collectors.toList());
                loadInitialCallback.onResult(news, 0, newsResponse.getNewsMetaResponse().getTotal());
            });
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams loadRangeParams, @NonNull LoadRangeCallback<News> loadRangeCallback) {
           criteria.setPage(loadRangeParams.startPosition % NEWS_LOAD_SIZE + 1);
           pullData(criteria, newsResponse -> {
               List<News> news = newsResponse.getNewsList().stream().map(News::buildFromNewsResponse).collect(Collectors.toList());
                loadRangeCallback.onResult(news);
           });
        }

        private void pullData(NewsFilterCriteria criteria, Consumer<NewsBodyResponse> callback) {
            new Thread( () -> {
                try {
                    if(!NewsRetroProvider.isConnected()) {
                        NewsRetroProvider.connect(context);
                    }
                    NewsBodyResponse newsResponse = NewsRetroProvider.getNewsFacadeRetro()
                            .getNews(criteria.toFilterMap())
                            .execute()
                            .body();
                    callback.accept(newsResponse);
                } catch (Exception exe) {
                    exe.printStackTrace();
                    Log.e(this.getClass().getSimpleName(), "Issue is ", exe);
                }
            }).start();
        }


    }

    public static class NewsDataFactory extends DataSource.Factory {
        private MutableLiveData<NewsDataSource> mutableLiveData;
        private NewsFilterCriteria criteria;
        public NewsDataFactory() {
            this.mutableLiveData = new MutableLiveData<>();
        }

        @NonNull
        @Override
        public DataSource create() {
            NewsDataSource dataSource = new NewsDataSource(criteria);
            mutableLiveData.postValue(dataSource);
            return dataSource;
        }

        public void setCriteria(NewsFilterCriteria criteria) {
            this.criteria = criteria;
        }

        public NewsFilterCriteria getCriteria() {
            return criteria;
        }
    }

}
