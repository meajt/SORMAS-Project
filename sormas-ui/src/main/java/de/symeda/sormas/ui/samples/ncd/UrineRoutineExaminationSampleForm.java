package de.symeda.sormas.ui.samples.ncd;

import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.logger.CustomLoggerFactory;
import de.symeda.sormas.api.logger.LoggerType;
import de.symeda.sormas.api.sample.ncd.HealthScreeningTestDto;
import de.symeda.sormas.api.sample.ncd.UrineRoutineExaminationSampleDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import org.apache.commons.lang3.function.TriFunction;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class UrineRoutineExaminationSampleForm extends AbstractEditForm<UrineRoutineExaminationSampleDto> {
    private static final String HTML_LAYOUT = fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.COLOR), fluidRowLocs(UrineRoutineExaminationSampleDto.TRANSPARENCY))
            + fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.PH), fluidRowLocs(UrineRoutineExaminationSampleDto.ALBUMIN))
            + fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.SUGAR), fluidRowLocs(UrineRoutineExaminationSampleDto.PUS_CELLS))
            + fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.RBC), fluidRowLocs(UrineRoutineExaminationSampleDto.EPITHELIAL_CELL))
            + fluidRow(fluidRowLocs(UrineRoutineExaminationSampleDto.CRYSTALS), fluidRowLocs(UrineRoutineExaminationSampleDto.CASTS))
            + fluidRowLocs(UrineRoutineExaminationSampleDto.OTHER_URINE_TEST);
    private Boolean isSelfGeneratedEvent = false;
    private TriFunction<Object, String, Object, Boolean> eventSender;

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
        addComboBoxEvent(UrineRoutineExaminationSampleDto.COLOR);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.TRANSPARENCY);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.PH);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.ALBUMIN);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.SUGAR);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.PUS_CELLS);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.RBC);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.EPITHELIAL_CELL);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.CRYSTALS);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.CASTS);
        addComboBoxEvent(UrineRoutineExaminationSampleDto.OTHER_URINE_TEST);
    }

    private void addComboBoxEvent(String propertyId) {
        ComboBox comboBox =  addField(propertyId, ComboBox.class);
        comboBox.addValueChangeListener(e -> sentValueChangeEvent(propertyId, e.getProperty().getValue()));

    }

    private void sentValueChangeEvent(String propertyId, Object value) {
        if (eventSender != null) {
            isSelfGeneratedEvent = true;
            eventSender.apply(this, propertyId, value);
        }
    }

    public void applyEvent(String propertyId, Object value) {
        CustomLoggerFactory.getLogger(LoggerType.WEB)
                .logInfo(this.getClass().getSimpleName()+hashCode(), "@applyEvent " + isSelfGeneratedEvent + " " + propertyId + " " + value);
        if (!isSelfGeneratedEvent) {
            Field<?> field = getField(propertyId);
            if (field != null)
                ((ComboBox)getField(propertyId)).setValue(value);
        }
        isSelfGeneratedEvent = false;
    }

    public void setEventSender(TriFunction<Object, String, Object, Boolean> eventSender) {
        this.eventSender = eventSender;
    }

}
