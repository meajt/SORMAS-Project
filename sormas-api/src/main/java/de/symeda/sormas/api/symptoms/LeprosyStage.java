package de.symeda.sormas.api.symptoms;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum LeprosyStage {
    TYPE_I,
    TYPE_II,
    NEURITIS;

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
