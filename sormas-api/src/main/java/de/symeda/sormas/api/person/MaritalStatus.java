package de.symeda.sormas.api.person;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum MaritalStatus {
    NOT_MARRIED,
    MARRIED,
    DIVORCED,
    WIDOWED,
    SEPARATE,
    UNKNOWN;

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
