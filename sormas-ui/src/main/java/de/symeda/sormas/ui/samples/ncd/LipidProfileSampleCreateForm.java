package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.logger.CustomLoggerFactory;
import de.symeda.sormas.api.logger.LoggerType;
import de.symeda.sormas.api.sample.ncd.HealthScreeningTestDto;
import de.symeda.sormas.api.sample.ncd.LipidProfileSampleDto;
import de.symeda.sormas.api.utils.fieldaccess.UiFieldAccessCheckers;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import org.apache.commons.lang3.function.TriFunction;


import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class LipidProfileSampleCreateForm extends AbstractEditForm<LipidProfileSampleDto> {
    private static final String HTML_LAYOUT =
            fluidRow(fluidRowLocs(LipidProfileSampleDto.CHOLESTROL_METHOD), fluidRowLocs(LipidProfileSampleDto.HDL_METHOD))
                    + fluidRow(fluidRowLocs(LipidProfileSampleDto.LDL_METHOD), fluidRowLocs(LipidProfileSampleDto.TRIGLYCERIDE_METHOD))
                    + fluidRow(fluidRowLocs(LipidProfileSampleDto.URIC_ACID_METHOD), fluidRowLocs(LipidProfileSampleDto.TOTAL_PROTEIN_METHOD))
                    + fluidRow(fluidRowLocs(LipidProfileSampleDto.ALBUMIN_METHOD), fluidRowLocs(LipidProfileSampleDto.CALCIUM_METHOD));
    private TriFunction<Object, String, Object, Boolean> mediator;
    private Boolean isSelfGeneratedEvent = false;
    TextField triglyCrideField;
    TextField cholestrolField;
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
        cholestrolField = addTextFieldWithEventListener(LipidProfileSampleDto.CHOLESTROL_METHOD);
        addField(LipidProfileSampleDto.HDL_METHOD, TextField.class);
        addField(LipidProfileSampleDto.LDL_METHOD, TextField.class);
        triglyCrideField = addTextFieldWithEventListener(LipidProfileSampleDto.TRIGLYCERIDE_METHOD);
  /*     triField.addValueChangeListener( l-> {
           sentValueChangeEvent(triField.getId(), triField.getValue());
       });*/
        addField(LipidProfileSampleDto.URIC_ACID_METHOD, TextField.class);
        addField(LipidProfileSampleDto.TOTAL_PROTEIN_METHOD, TextField.class);
        addField(LipidProfileSampleDto.ALBUMIN_METHOD, TextField.class);
        addField(LipidProfileSampleDto.CALCIUM_METHOD, TextField.class);
    }

    private TextField addTextFieldWithEventListener(String propertyId) {
        TextField textField = addField(propertyId, TextField.class);
        textField.addValueChangeListener(e -> sentValueChangeEvent(propertyId, e.getProperty().getValue()));
        return textField;
    }
    private void sentValueChangeEvent(String propertyId, Object value) {
        if (mediator != null) {
            isSelfGeneratedEvent = true;
            mediator.apply(this, propertyId, value);
        }
    }

    public void applyEvent(String propertyId, Object value) {
        CustomLoggerFactory.getLogger(LoggerType.WEB)
                .logInfo(this.getClass().getSimpleName(), "@applyEvent "+isSelfGeneratedEvent+" "+propertyId+" "+value);
       if (!isSelfGeneratedEvent) {
           switch (propertyId) {
               case HealthScreeningTestDto.TRIGLYCERIDE:
                   triglyCrideField.setValue((String) value);
                   break;
               case HealthScreeningTestDto.TOTAL_CHOLESTEROL:
                   cholestrolField.setValue((String) value);
                   break;
               case HealthScreeningTestDto.POST_PARANDIAL:
                   getValue().setPostParandial(parseInt((String) value, null));
                   break;
               case HealthScreeningTestDto.FASTING:
                   getValue().setFasting(parseInt((String) value, null));
                   break;
           }
       }
       isSelfGeneratedEvent = false;
    }

    public Integer parseInt(String intStr, Integer defValue) {
        try{
            return Integer.parseInt(intStr);
        }catch (Exception e)
        {
            return defValue;
        }
    }

    public void setMediator(TriFunction<Object, String, Object, Boolean> mediator) {
        this.mediator = mediator;
    }
}
