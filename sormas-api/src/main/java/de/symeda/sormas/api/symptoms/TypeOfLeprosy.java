package de.symeda.sormas.api.symptoms;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum TypeOfLeprosy {
    MB,
    PB;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
