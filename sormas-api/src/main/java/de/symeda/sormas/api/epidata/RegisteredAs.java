package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum RegisteredAs {
    NEW_CASE,
    TRANSFERED_ID,
    RE_STARTED,
    RELAPSED,
    OTHER;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
