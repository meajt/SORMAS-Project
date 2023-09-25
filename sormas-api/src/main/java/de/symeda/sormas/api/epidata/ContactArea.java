package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum ContactArea {
    EYE,
    FACE,
    HAND,
    OTHER;

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
