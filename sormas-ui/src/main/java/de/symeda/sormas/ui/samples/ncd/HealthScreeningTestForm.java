package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.logger.CustomLoggerFactory;
import de.symeda.sormas.api.logger.LoggerType;
import de.symeda.sormas.api.sample.ncd.HealthScreeningTestDto;
import de.symeda.sormas.api.sample.ncd.LipidProfileSampleDto;
import de.symeda.sormas.api.sample.ncd.UrineRoutineExaminationSampleDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import org.apache.commons.lang3.function.TriFunction;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class HealthScreeningTestForm extends AbstractEditForm<HealthScreeningTestDto> {

    public static final String HTML_LAYOUT =
            fluidRow(fluidRowLocs(HealthScreeningTestDto.FASTING), fluidRowLocs(HealthScreeningTestDto.POST_PARANDIAL))
                    + fluidRow(fluidRowLocs(HealthScreeningTestDto.TRIGLYCERIDE), fluidRowLocs(HealthScreeningTestDto.TOTAL_CHOLESTEROL))
                    + fluidRowLocs(UrineRoutineExaminationSampleDto.URINE_ROUTINE_EXAMINATION);
    private UrineRoutineExaminationSampleForm urineRoutineExaminationForm;
    private TriFunction<Object, String, Object, Boolean> eventSender;
    private Boolean isSelfGeneratedEvent = false;

    TextField fastingField;
    TextField postParadialField;
    TextField triglycrideFiled;
    TextField totalCholestrolField;

    public HealthScreeningTestForm() {
        super(HealthScreeningTestDto.class, HealthScreeningTestDto.I18N_PREFIX, false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()));
        addFields();
    }

    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        urineRoutineExaminationForm = new UrineRoutineExaminationSampleForm();
        urineRoutineExaminationForm.setValue(new UrineRoutineExaminationSampleDto());
        urineRoutineExaminationForm.setWidth(urineRoutineExaminationForm.getWidth() * 7 / 12, Unit.PIXELS);
        getContent().addComponent(urineRoutineExaminationForm, UrineRoutineExaminationSampleDto.URINE_ROUTINE_EXAMINATION);
        fastingField = addTextFieldWithEventListener(HealthScreeningTestDto.FASTING);
        postParadialField = addTextFieldWithEventListener(HealthScreeningTestDto.POST_PARANDIAL);
        totalCholestrolField = addTextFieldWithEventListener(HealthScreeningTestDto.TOTAL_CHOLESTEROL);
        triglycrideFiled = addTextFieldWithEventListener(HealthScreeningTestDto.TRIGLYCERIDE);

    }

    private TextField addTextFieldWithEventListener(String propertyId) {
        TextField textField = addField(propertyId, TextField.class);
        textField.addValueChangeListener(e -> sentValueChangeEvent(propertyId, e.getProperty().getValue()));
        return textField;
    }

    private void sentValueChangeEvent(String propertyId, Object value) {
        if (eventSender != null) {
            isSelfGeneratedEvent = true;
            eventSender.apply(this, propertyId, value);
        }
    }

    public void applyEvent(String propertyId, Object value) {
        CustomLoggerFactory.getLogger(LoggerType.WEB)
                .logInfo(this.getClass().getSimpleName(), "@applyEvent "+isSelfGeneratedEvent+" "+propertyId+" "+value);
        if (!isSelfGeneratedEvent) {
            switch (propertyId) {
                case LipidProfileSampleDto.TRIGLYCERIDE_METHOD:
                    triglycrideFiled.setValue((String) value);
                    break;
                case LipidProfileSampleDto.CHOLESTROL_METHOD:
                    totalCholestrolField.setValue((String) value);
                    break;
                case UrineRoutineExaminationSampleDto.URINE_ROUTINE_EXAMINATION:
                    urineRoutineExaminationForm.setValue((UrineRoutineExaminationSampleDto) value);
            }
        }
        isSelfGeneratedEvent = false;
    }

    public void setEventSender(TriFunction<Object, String, Object, Boolean> eventSender) {
        this.eventSender = eventSender;
        urineRoutineExaminationForm.setEventSender(eventSender);
    }

    public void setFasting(Integer value) {
        if (value != null)
            fastingField.setValue(value.toString());
    }

    public void setPostParaial(Integer value) {
        if (value != null)
            postParadialField.setValue(value.toString());
    }

    public UrineRoutineExaminationSampleForm getUrineRoutineExaminationForm() {
        return urineRoutineExaminationForm;
    }
}
