package de.symeda.sormas.api.sample.multiplexpathogentest;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum InfluenzaBPathogenTestResult {
    INFLUENZA_B_VICTORIA,
    INFLUENZA_YAMAGATA,
    OTHERS;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
