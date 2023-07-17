package de.symeda.sormas.app.news;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.symeda.sormas.app.R;
import de.symeda.sormas.app.backend.news.News;
import de.symeda.sormas.app.core.adapter.databinding.BindingPagedListAdapter;
import de.symeda.sormas.app.core.adapter.databinding.BindingViewHolder;
import de.symeda.sormas.app.databinding.RowNewsListItemLayoutBinding;

public class NewsListAdapter extends BindingPagedListAdapter<News, RowNewsListItemLayoutBinding> {
    public NewsListAdapter() {
        super(R.layout.row_news_list_item_layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof BindingViewHolder) {
            BindingViewHolder<News, RowNewsListItemLayoutBinding> pageHolder = (BindingViewHolder<News, RowNewsListItemLayoutBinding>) holder;
            pageHolder.setOnListItemClickListener(this.mOnListItemClickListener);
        }
    }
}
