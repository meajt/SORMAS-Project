package de.symeda.sormas.app.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import de.symeda.sormas.app.PagedBaseListActivity;
import de.symeda.sormas.app.PagedBaseListFragment;
import de.symeda.sormas.app.R;
import de.symeda.sormas.app.component.menu.PageMenuItem;

public class NewsListActivity extends PagedBaseListActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        adapter = new NewsListAdapter();

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