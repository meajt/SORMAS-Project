package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineSugar {
    NIL,PLUS,PLUS_PLUS,PLUS_PLUS_PLUS,PLUS_PLUS_PLUS_PLUS;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }

}
