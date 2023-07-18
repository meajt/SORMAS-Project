package de.symeda.sormas.app.news;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.symeda.sormas.api.action.ActionPriority;
import de.symeda.sormas.app.R;
import de.symeda.sormas.app.core.adapter.databinding.BindingPagedListAdapter;
import de.symeda.sormas.app.core.adapter.databinding.BindingViewHolder;
import de.symeda.sormas.app.databinding.RowNewsListItemLayoutBinding;

public class NewsListAdapter extends BindingPagedListAdapter<News, RowNewsListItemLayoutBinding> {
    private final Context context;
    public NewsListAdapter(Context context) {
        super(R.layout.row_news_list_item_layout);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof BindingViewHolder) {
            BindingViewHolder<News, RowNewsListItemLayoutBinding> pageHolder = (BindingViewHolder<News, RowNewsListItemLayoutBinding>) holder;
            pageHolder.setOnListItemClickListener(this.mOnListItemClickListener);
            News data = pageHolder.getData();
            setColorInPriorityButton(pageHolder.binding.newsDataActionPriority, data);
        }
    }

    private void setColorInPriorityButton(View pageHolder, News data) {
        if (context != null) {
            if (data.getPriority() == ActionPriority.HIGH) {
                pageHolder.setBackground(context.getDrawable(R.drawable.background_legend_high_priority));
            } else if (data.getPriority() == ActionPriority.NORMAL) {
                pageHolder.setBackground(context.getDrawable(R.drawable.background_legend_normal_priority));
            }
        }
    }
}
