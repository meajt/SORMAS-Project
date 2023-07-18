package de.symeda.sormas.app.news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import de.symeda.sormas.api.action.ActionPriority;
import de.symeda.sormas.app.PagedBaseListActivity;
import de.symeda.sormas.app.PagedBaseListFragment;
import de.symeda.sormas.app.R;
import de.symeda.sormas.app.component.Item;
import de.symeda.sormas.app.component.menu.PageMenuItem;
import de.symeda.sormas.app.databinding.FilterNewsListLayoutBinding;
import de.symeda.sormas.app.util.DataUtils;
import de.symeda.sormas.app.util.InfrastructureDaoHelper;
import de.symeda.sormas.app.util.InfrastructureFieldsDependencyHandler;

public class NewsListActivity extends PagedBaseListActivity {

    NewsListViewModel viewModel;
    FilterNewsListLayoutBinding filterBinding;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        showPreloader();
        adapter = new NewsListAdapter(getContext());
        viewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        viewModel.setContext(this);
        viewModel.getNewsList().observe(this, news -> {
            Log.d(this.getClass().getSimpleName(), "news size is " + news.size());
            adapter.submitList(news);
            hidePreloader();
        });
        filterBinding.setCriteria(viewModel.getNewsFilterCriteria());
    }


    @Override
    protected int getActivityTitle() {
        return R.string.heading_activityNews;
    }

    @Override
    public void addFiltersToPageMenu() {
        View newsListFilterView = getLayoutInflater().inflate(R.layout.filter_news_list_layout, null);
        filterBinding = DataBindingUtil.bind(newsListFilterView);
        pageMenu.addFilter(newsListFilterView);
        runOnUiThread(() -> {
            List<Item> initialRegions = InfrastructureDaoHelper.loadRegionsByServerCountry();
            InfrastructureFieldsDependencyHandler.instance
                    .initializeRegionFields(filterBinding.regionFilter, initialRegions, null,
                            filterBinding.districtFilter, List.of(), null,
                            filterBinding.communityFilter, List.of(), null);
            filterBinding.priorityFilter.initializeSpinner(DataUtils.getEnumItems(ActionPriority.class));
        });
        filterBinding.applyFilters.setOnClickListener(e -> {
            showPreloader();
            pageMenu.hideAll();
            viewModel.notifyCriteriaUpdated();
        });
        filterBinding.resetFilters.setOnClickListener(e -> {
            showPreloader();
            pageMenu.hideAll();
            viewModel.getNewsFilterCriteria().setTextFilter(null);
            viewModel.getNewsFilterCriteria().setRegion(null);
            viewModel.getNewsFilterCriteria().setDistrict(null);
            viewModel.getNewsFilterCriteria().setCommunity(null);
            viewModel.getNewsFilterCriteria().setPriority(null);
            filterBinding.invalidateAll();
            viewModel.notifyCriteriaUpdated();
        });
    }

    @Override
    protected PagedBaseListFragment buildListFragment(PageMenuItem menuItem) {
        return NewsListFragment.newInstance();
    }
}