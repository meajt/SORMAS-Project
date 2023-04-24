package de.symeda.sormas.ui.samples.ncd;

import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;

public abstract class AbstractNcdSampleCollegueEditForm<DTO> extends AbstractEditForm<DTO> {
    protected AbstractNcdSampleCollegueEditForm(Class<DTO> type, String propertyI18nPrefix, FieldVisibilityCheckers fieldVisibilityCheckers) {
        super(type, propertyI18nPrefix, fieldVisibilityCheckers);
    }
}
