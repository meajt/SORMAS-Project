package de.symeda.sormas.api.hospitalization;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum RegistrationType {
    OPD, IPD, EMERGENCY;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
