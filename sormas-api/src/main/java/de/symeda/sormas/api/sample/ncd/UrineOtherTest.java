package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineOtherTest {
    NOT_SEEN,BACTERIA_SEEN,TRICHOMONAS_VAGINALIS_SEEN;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }

}
