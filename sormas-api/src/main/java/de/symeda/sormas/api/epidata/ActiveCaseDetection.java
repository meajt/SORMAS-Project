package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum ActiveCaseDetection {
    HOUSE_TO_HOUSE_VISIT,
    MOBILE_MALARIA_CLINIC,
    CONTACT_SURVEY,
    FEVER_SURVEY,
    POPULATION_BASE_SURVEY;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
