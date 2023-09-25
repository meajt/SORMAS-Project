package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.sample.ncd.LftSampleDto;
import de.symeda.sormas.api.sample.ncd.RftSampleDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class LftSampleCreateForm extends AbstractEditForm<LftSampleDto> {

    private static final String HTML_LAYOUT = fluidRow(fluidRowLocs(LftSampleDto.BILIRUBIN_T), fluidRowLocs(LftSampleDto.BILIRUBIN_D))
            +fluidRow(fluidRowLocs(LftSampleDto.ALKALINE_PHOSPATASE), fluidRowLocs(LftSampleDto.KINETIC))
            +fluidRow(fluidRowLocs(LftSampleDto.SGPT), fluidRowLocs(LftSampleDto.SGOT))
            +fluidRowLocs(LftSampleDto.VLDL);

    public LftSampleCreateForm() {
        super(LftSampleDto.class, LftSampleDto.I18N_PREFIX, false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()));
        addFields();
    }
    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        addField(LftSampleDto.BILIRUBIN_T, TextField.class);
        addField(LftSampleDto.BILIRUBIN_D, TextField.class);
        addField(LftSampleDto.ALKALINE_PHOSPATASE, TextField.class);
        addField(LftSampleDto.KINETIC, TextField.class);
        addField(LftSampleDto.SGPT, TextField.class);
        addField(LftSampleDto.SGOT, TextField.class);
        addField(LftSampleDto.VLDL, TextField.class);
    }
}
