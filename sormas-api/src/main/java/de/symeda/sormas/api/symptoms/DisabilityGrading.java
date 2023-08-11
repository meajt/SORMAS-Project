package de.symeda.sormas.api.symptoms;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum DisabilityGrading {
    ZERO,
    ONE,
    TWO;

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
