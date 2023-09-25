package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineCasts {
    NOT_SEEN,PUS_CELL_CAST,EPITHELIAL_CAST,GRANULAR_CAST,HYALINE_CAST;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
