package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineTransparency {
    CLEAR,SLIGHTLY_TURBID,TURBID;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }

}
