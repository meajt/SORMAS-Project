package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.i18n.I18nProperties;

public enum CaseDetectionMethodGroup {
    ACTIVE_CASE_DETECTION,
    PASSIVE_CASE_DETECTION;
    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
