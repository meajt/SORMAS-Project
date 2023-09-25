package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum PreventiveMeasures {
    NOT_TAKEN,
    NORMAL_NET,
    LLIN,
    CHEMOPROPHYLAXIS,
    OTHER;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
