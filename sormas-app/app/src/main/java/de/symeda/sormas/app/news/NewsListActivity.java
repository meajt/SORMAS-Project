package de.symeda.sormas.app.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

import de.symeda.sormas.app.PagedBaseListActivity;
import de.symeda.sormas.app.PagedBaseListFragment;
import de.symeda.sormas.app.R;
import de.symeda.sormas.app.backend.news.News;
import de.symeda.sormas.app.component.menu.PageMenuItem;
import de.symeda.sormas.app.news.rest.NewsBodyResponse;
import de.symeda.sormas.app.news.rest.NewsRetroProvider;
import de.symeda.sormas.app.rest.ApiVersionException;
import de.symeda.sormas.app.rest.ServerCommunicationException;
import de.symeda.sormas.app.rest.ServerConnectionException;

public class NewsListActivity extends PagedBaseListActivity {

    NewsListViewModel viewModel;
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        adapter = new NewsListAdapter();
        viewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        viewModel.setContext(this);
        viewModel.getNewsList().observe( this, news ->{
            Log.d(this.getClass().getSimpleName(), "news size is "+news.size());
            adapter.submitList(news);});
    }


    @Override
    protected int getActivityTitle() {
        return R.string.heading_activityNews;
    }

    @Override
    public void addFiltersToPageMenu() {

    }

    @Override
    protected PagedBaseListFragment buildListFragment(PageMenuItem menuItem) {
        return NewsListFragment.newInstance();
    }
}