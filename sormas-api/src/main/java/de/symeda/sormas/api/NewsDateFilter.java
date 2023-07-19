package de.symeda.sormas.api;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum NewsDateFilter {
    LESS_THAN_THREE_DAYS(3),
    LESS_THAN_TEN_DAYS(10),
    LESS_THAN_MONTH(30);

    private final Integer value;
    NewsDateFilter(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
