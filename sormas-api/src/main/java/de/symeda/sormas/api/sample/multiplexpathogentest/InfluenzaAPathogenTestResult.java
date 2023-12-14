package de.symeda.sormas.api.sample.multiplexpathogentest;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum InfluenzaAPathogenTestResult {
    INFLUENZA_A_PDM_09,
    INFLUENZA_A_H3,
    INFLUENZA_A_H5,
    INFLUENZA_A_H7,
    OTHERS;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
