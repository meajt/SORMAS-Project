package de.symeda.sormas.api.nepalsfeature.news;

import de.symeda.sormas.api.utils.SortProperty;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface NewsFacade {
    long count(NewsCriteria criteria);
    List<NewsDto> getNewList(NewsCriteria criteria, Integer first, Integer max, List<SortProperty> sortProperties);
}
