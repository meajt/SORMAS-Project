package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.sample.ncd.RftSampleDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class RftSampleCreateForm extends AbstractEditForm<RftSampleDto> {

    private static final String HTML_LAYOUT = fluidRow(fluidRowLocs(RftSampleDto.UREA), fluidRowLocs(RftSampleDto.CREATININE))
            +fluidRow(fluidRowLocs(RftSampleDto.SODIUM), fluidRowLocs(RftSampleDto.POTASSIUM));

    public RftSampleCreateForm() {
        super(RftSampleDto.class, RftSampleDto.I18N_PREFIX, false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()));
        addFields();
    }

    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        addField(RftSampleDto.UREA, TextField.class);
        addField(RftSampleDto.CREATININE, TextField.class);
        addField(RftSampleDto.SODIUM, TextField.class);
        addField(RftSampleDto.POTASSIUM, TextField.class);
    }
}
