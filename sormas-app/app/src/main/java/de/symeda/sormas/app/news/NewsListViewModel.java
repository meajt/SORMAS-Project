package de.symeda.sormas.app.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.app.backend.news.News;

public class NewsListViewModel extends ViewModel {
    private LiveData<PagedList<News>> newsList;
    private NewsDataFactory newsDataFactory;

    public NewsListViewModel() {
        newsDataFactory = new NewsDataFactory();
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(32).setPageSize(16).build();
        LivePagedListBuilder newsPageListBuilder = new LivePagedListBuilder(newsDataFactory, config);
        newsList = newsPageListBuilder.build();
    }

    public LiveData<PagedList<News>> getNewsList() {
        return newsList;
    }

    public static class NewsDataSource extends PositionalDataSource<News> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams loadInitialParams, @NonNull LoadInitialCallback<News> loadInitialCallback) {
            List<News> dummyData = getDummyData();
            loadInitialCallback.onResult(dummyData, 0, dummyData.size());
        }


        @Override
        public void loadRange(@NonNull LoadRangeParams loadRangeParams, @NonNull LoadRangeCallback<News> loadRangeCallback) {
            loadRangeCallback.onResult(getDummyData());
        }
        @NonNull
        private List<News> getDummyData() {
            List<News> dummyData = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY HH:mm");
            News news = new News();
            news.setTitle("सुदूरपश्चिममा कोभिड संक्रमित बढे");
            news.setSummary("धनगढीः सुदूरपश्चिम प्रदेशभित्र पछिल्लो समय कोराना संक्रमितका बिरामी बढ्दै गएको पाइएको छ। प्रदेशभित्र पछिल्लो २४ घण्टामा १५ जनामा कोरोना संक्रमण पुष्टि भएको छ। पछिल्लो २४ घण्टामा २०६ नमूनाको कोरोना परिक्षण गरिएकोमा १५ जनामा संक्रमण पुष्टि भएको हो। स्वास्थ निर्देशनालय डोटीका अनुसार एन्टिजेन बिधिबाट....\n");
            news.setNewsLink("https://dineshkhabar.com/article/98036");
            news.setDiseaseCategory(Disease.CORONAVIRUS);
            news.setNewsSource("Dinesh Khabar Nepal");
            dummyData.add(news);
            News news1 = new News();
            news1.setTitle("औलोको सबैभन्दा जोखिममा सुदूरपश्चिम");
            news1.setSummary("\"काठमाडौँ नेपालमा पछिल्लो समय औलोको आयातित केसहरु बढिदेखिएको देखिएको छ । ईपिडिमियोलोजी तथा रोग नियन्त्रण महाशाखाका(ईडिसिडी) का अनुसार सबैभन्दा धेरै आयातित केसहरुको स्रोत भारत हो । विशेषगरी यसको असर सुदूरपश्चिम प्रदेशमा बढी परेको देखिएको छ ।\n" +
                    "\n" +
                    "सुदूरपश्चिम प्रदेशका विभिन्न स्थानमा विशेषगरी भारतबाट....");
            news1.setNewsLink("https://ekantipur.com/health/2023/04/25/168242825993166187.html");
            news1.setDiseaseCategory(Disease.MALARIA);
            news1.setNewsSource("Dinesh Khabar Nepal");
            dummyData.add(news1);
            try {
                news.setCreationDate(dateFormat.parse("4/25/2023 21:01"));
                news1.setCreationDate(dateFormat.parse("4/25/2023 17:01"));
            }catch (Exception exe) {exe.printStackTrace();}
            return dummyData;
        }

    }

    public static class NewsDataFactory extends DataSource.Factory {
        private MutableLiveData<NewsDataSource> mutableLiveData;

        public NewsDataFactory() {
            this.mutableLiveData = new MutableLiveData<>();
        }

        @NonNull
        @Override
        public DataSource create() {
            NewsDataSource dataSource = new NewsDataSource();
            mutableLiveData.postValue(dataSource);
            return dataSource;
        }
    }

}
