package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineRbc {
    PLENTY;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }

}
