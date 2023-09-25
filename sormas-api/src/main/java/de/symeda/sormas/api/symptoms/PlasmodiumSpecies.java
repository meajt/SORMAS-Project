package de.symeda.sormas.api.symptoms;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum PlasmodiumSpecies {
    VIVAX,
    FALCIPARUM,
    OVALE,
    MALARIEA,
    KNOWLESI,
    MIXED_INFECTION,
    UNKNOWN;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
