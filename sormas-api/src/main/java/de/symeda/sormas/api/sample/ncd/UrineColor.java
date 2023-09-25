package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineColor {
    LIGHT_YELLOW,DARK_YELLOW,MILKY_WHITE,REDDISH;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }

}
