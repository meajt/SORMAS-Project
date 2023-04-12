package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.ComboBox;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.sample.ncd.UrineRoutineExaminationSampleDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class UrineRoutineExaminationSampleForm extends AbstractEditForm<UrineRoutineExaminationSampleDto> {
    private static final String HTML_LAYOUT = fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.COLOR), fluidRowLocs(UrineRoutineExaminationSampleDto.TRANSPARENCY))
            +fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.PH), fluidRowLocs(UrineRoutineExaminationSampleDto.ALBUMIN))
            +fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.SUGAR), fluidRowLocs(UrineRoutineExaminationSampleDto.PUS_CELLS))
            +fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.RBC), fluidRowLocs(UrineRoutineExaminationSampleDto.EPITHELIAL_CELL))
            +fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.CRYSTALS), fluidRowLocs(UrineRoutineExaminationSampleDto.CASTS))
            +fluidRowLocs(UrineRoutineExaminationSampleDto.OTHER_URINE_TEST);

    public UrineRoutineExaminationSampleForm() {
        super(UrineRoutineExaminationSampleDto.class, UrineRoutineExaminationSampleDto.I18N_PREFIX, false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()));
        addFields();
    }

    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        addField(UrineRoutineExaminationSampleDto.COLOR, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.TRANSPARENCY, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.PH, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.ALBUMIN, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.SUGAR, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.PUS_CELLS, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.RBC, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.EPITHELIAL_CELL, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.CRYSTALS, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.CASTS, ComboBox.class);
        addField(UrineRoutineExaminationSampleDto.OTHER_URINE_TEST, ComboBox.class);
    }
}
