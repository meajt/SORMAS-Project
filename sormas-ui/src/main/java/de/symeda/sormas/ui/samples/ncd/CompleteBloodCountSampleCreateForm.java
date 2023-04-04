package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.sample.ncd.CompleteBloodCountSampleDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class CompleteBloodCountSampleCreateForm extends AbstractEditForm<CompleteBloodCountSampleDto> {
    private static final String HTML_LAYOUT = fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.TLC), fluidRowLocs(CompleteBloodCountSampleDto.NEUTROPHILS))
            +fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.LYMPHOCYTES), fluidRowLocs(CompleteBloodCountSampleDto.MONOCYTES))
            +fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.EOSINOPHILS), fluidRowLocs(CompleteBloodCountSampleDto.BASOPHILS))
            +fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.PLATETET_COUNT), fluidRowLocs(CompleteBloodCountSampleDto.RBC))
            +fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.HB), fluidRowLocs(CompleteBloodCountSampleDto.PCV))
            +fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.MCV), fluidRowLocs(CompleteBloodCountSampleDto.HCH))
            +fluidRow(fluidRowLocs(CompleteBloodCountSampleDto.MCHC), fluidRowLocs(CompleteBloodCountSampleDto.RDW_CV))
            +fluidRowLocs(CompleteBloodCountSampleDto.HB_A1C);

    public CompleteBloodCountSampleCreateForm() {
        super(CompleteBloodCountSampleDto.class, CompleteBloodCountSampleDto.I18N_PREFIX, false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()));
        addFields();
    }
    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        addField(CompleteBloodCountSampleDto.TLC, TextField.class);
        addField(CompleteBloodCountSampleDto.NEUTROPHILS, TextField.class);
        addField(CompleteBloodCountSampleDto.LYMPHOCYTES, TextField.class);
        addField(CompleteBloodCountSampleDto.MONOCYTES, TextField.class);
        addField(CompleteBloodCountSampleDto.EOSINOPHILS, TextField.class);
        addField(CompleteBloodCountSampleDto.BASOPHILS, TextField.class);
        addField(CompleteBloodCountSampleDto.PLATETET_COUNT, TextField.class);
        addField(CompleteBloodCountSampleDto.RBC, TextField.class);
        addField(CompleteBloodCountSampleDto.HB, TextField.class);
        addField(CompleteBloodCountSampleDto.PCV, TextField.class);
        addField(CompleteBloodCountSampleDto.MCV, TextField.class);
        addField(CompleteBloodCountSampleDto.HCH, TextField.class);
        addField(CompleteBloodCountSampleDto.MCHC, TextField.class);
        addField(CompleteBloodCountSampleDto.RDW_CV, TextField.class);
        addField(CompleteBloodCountSampleDto.HB_A1C, TextField.class);
    }
}
