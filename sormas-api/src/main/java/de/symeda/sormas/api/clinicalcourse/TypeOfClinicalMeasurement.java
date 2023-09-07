package de.symeda.sormas.api.clinicalcourse;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum TypeOfClinicalMeasurement {
    DIAGNOSIS,
    CONTINUATION,
    RFT;

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
