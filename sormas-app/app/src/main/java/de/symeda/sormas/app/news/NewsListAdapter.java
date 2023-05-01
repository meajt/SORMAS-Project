package de.symeda.sormas.app.news;

import de.symeda.sormas.app.R;
import de.symeda.sormas.app.backend.news.News;
import de.symeda.sormas.app.core.adapter.databinding.BindingPagedListAdapter;
import de.symeda.sormas.app.databinding.RowNewsListItemLayoutBinding;

public class NewsListAdapter extends BindingPagedListAdapter<News, RowNewsListItemLayoutBinding> {
    public NewsListAdapter() {
        super(R.layout.row_news_list_item_layout);
    }
}
