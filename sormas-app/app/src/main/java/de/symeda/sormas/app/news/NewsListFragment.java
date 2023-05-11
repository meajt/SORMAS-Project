package de.symeda.sormas.app.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.symeda.sormas.app.PagedBaseListFragment;
import de.symeda.sormas.app.R;
import de.symeda.sormas.app.backend.news.News;
import de.symeda.sormas.app.core.adapter.databinding.OnListItemClickListener;

public class NewsListFragment extends PagedBaseListFragment<NewsListAdapter> implements OnListItemClickListener {
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerViewForList;

    public static NewsListFragment newInstance() {
        return newInstance(NewsListFragment.class, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewForList = view.findViewById(R.id.recyclerViewForList);

        return view;
    }

    @Override
    public NewsListAdapter getNewListAdapter() {
        return (NewsListAdapter) ((NewsListActivity) getActivity()).getAdapter();
    }

    @Override
    public void onListItemClick(View view, int position, Object item) {
        News news = (News) item;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getNewsLink()));
        startActivity(browserIntent);
        Log.d(this.getTag(), "starting browser");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerViewForList.setAdapter(getListAdapter());
        recyclerViewForList.setLayoutManager(linearLayoutManager);
    }
}
