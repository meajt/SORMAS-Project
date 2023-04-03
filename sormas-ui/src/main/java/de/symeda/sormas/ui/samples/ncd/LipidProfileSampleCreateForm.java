package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.sample.ncd.LipidProfileSampleDto;
import de.symeda.sormas.api.utils.fieldaccess.UiFieldAccessCheckers;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;


import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class LipidProfileSampleCreateForm extends AbstractEditForm<LipidProfileSampleDto> {
    private static final String HTML_LAYOUT =
            fluidRow(fluidRowLocs(LipidProfileSampleDto.CHOLESTROL_METHOD), fluidRowLocs(LipidProfileSampleDto.HDL_METHOD))
                    + fluidRow(fluidRowLocs(LipidProfileSampleDto.LDL_METHOD), fluidRowLocs(LipidProfileSampleDto.TRIGLYCERIDE_METHOD))
                    + fluidRow(fluidRowLocs(LipidProfileSampleDto.URIC_ACID_METHOD), fluidRowLocs(LipidProfileSampleDto.TOTAL_PROTEIN_METHOD))
                    + fluidRow(fluidRowLocs(LipidProfileSampleDto.ALBUMIN_METHOD), fluidRowLocs(LipidProfileSampleDto.CALCIUM_METHOD));

    public LipidProfileSampleCreateForm() {
        super(LipidProfileSampleDto.class, LipidProfileSampleDto.I18N_PREFIX, false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()),
                UiFieldAccessCheckers.getNoop());
        addFields();
    }

    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        addField(LipidProfileSampleDto.CHOLESTROL_METHOD, TextField.class);
        addField(LipidProfileSampleDto.HDL_METHOD, TextField.class);
        addField(LipidProfileSampleDto.LDL_METHOD, TextField.class);
        addField(LipidProfileSampleDto.TRIGLYCERIDE_METHOD, TextField.class);
        addField(LipidProfileSampleDto.URIC_ACID_METHOD, TextField.class);
        addField(LipidProfileSampleDto.TOTAL_PROTEIN_METHOD, TextField.class);
        addField(LipidProfileSampleDto.ALBUMIN_METHOD, TextField.class);
        addField(LipidProfileSampleDto.CALCIUM_METHOD, TextField.class);
    }
}
