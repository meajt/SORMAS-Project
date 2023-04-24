package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum UrineCrystals {
    NOT_SEEN,URIC_ACID_CRYSTAL,CALCIUM_OXALATE_CRYSTAL,CALCIUM_CARBONATE,AMORPHOUS_URATES,TRIPLE_PHOSPHATE;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }

}
