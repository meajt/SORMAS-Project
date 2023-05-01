package de.symeda.sormas.app.news;

import android.view.View;

import de.symeda.sormas.app.PagedBaseListFragment;
import de.symeda.sormas.app.core.adapter.databinding.OnListItemClickListener;

public class NewsListFragment extends PagedBaseListFragment<NewsListAdapter> implements OnListItemClickListener {

    public static NewsListFragment newInstance() {
        return newInstance(NewsListFragment.class, null);
    }


    @Override
    public NewsListAdapter getNewListAdapter() {
        return (NewsListAdapter) ((NewsListActivity) getActivity()).getAdapter();
    }

    @Override
    public void onListItemClick(View view, int position, Object item) {

    }
}
