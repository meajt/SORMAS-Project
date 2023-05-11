package de.symeda.sormas.api.news;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.EntityDto;

public class NewsDto extends EntityDto {
    private String newsLink;
    private String title;
    private String summary;
    private Disease diseaseCategory;
    private String newsSource;
}
