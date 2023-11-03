package de.symeda.sormas.ui.samples;

import static de.symeda.sormas.ui.utils.CssStyles.VSPACE_4;
import static de.symeda.sormas.ui.utils.CssStyles.VSPACE_TOP_1;
import static de.symeda.sormas.ui.utils.CssStyles.VSPACE_TOP_3;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocsCss;
import static de.symeda.sormas.ui.utils.LayoutUtil.locCss;

import java.util.Arrays;
import java.util.List;

import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.TextField;

import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Strings;
import de.symeda.sormas.api.i18n.Validations;
import de.symeda.sormas.api.sample.SampleBulkEditData;
import de.symeda.sormas.api.sample.SampleDto;
import de.symeda.sormas.api.sample.SpecimenCondition;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.DateComparisonValidator;
import de.symeda.sormas.ui.utils.FieldHelper;

public class BulkSampleDataForm extends AbstractEditForm<SampleBulkEditData> {

    private static final long serialVersionUID = 1L;
    private static final String SHIPPED_CHECKBOX = "shippedCheckBox";
    private static final String RECEIVED_CHECKBOX = "receivedCheckBox";
    //@formatter:off
    private static final String HTML_LAYOUT =
            fluidRowLocsCss(VSPACE_4, SHIPPED_CHECKBOX) +
            locCss(VSPACE_TOP_1, SampleBulkEditData.SHIPPED) +
            fluidRowLocs(SampleBulkEditData.SHIPMENT_DATE, SampleBulkEditData.SHIPMENT_DETAILS) +
            locCss(VSPACE_TOP_3, "")+
            fluidRowLocsCss(VSPACE_4, RECEIVED_CHECKBOX) +
            locCss(VSPACE_TOP_1, SampleDto.RECEIVED) +
            fluidRowLocs(SampleDto.RECEIVED_DATE, SampleDto.LAB_SAMPLE_ID) +
            fluidRowLocs(SampleDto.SPECIMEN_CONDITION, SampleDto.NO_TEST_POSSIBLE_REASON);
    //@formatter:off

    public CheckBox shippedCheckBox;
    public CheckBox recievedCheckBox;
    public BulkSampleDataForm() {
        super(SampleBulkEditData.class, SampleDto.I18N_PREFIX);
    }

    @Override
    protected void addFields() {
        shippedCheckBox = new CheckBox(I18nProperties.getString(Strings.bulkEditSampleShipped));
        getContent().addComponent(shippedCheckBox, SHIPPED_CHECKBOX);
        recievedCheckBox = new CheckBox(I18nProperties.getString(Strings.bulkEditSampleReceived));
        getContent().addComponent(recievedCheckBox, RECEIVED_CHECKBOX);

        addField(SampleBulkEditData.SHIPPED, CheckBox.class);
        DateField shipmentDate = addDateField(SampleBulkEditData.SHIPMENT_DATE, DateField.class, 7);
        addField(SampleBulkEditData.SHIPMENT_DETAILS, TextField.class);
        addField(SampleBulkEditData.RECEIVED, CheckBox.class);
        DateField receivedDate = addField(SampleBulkEditData.RECEIVED_DATE, DateField.class);
        addField(SampleBulkEditData.LAB_SAMPLE_ID, TextField.class);
        addField(SampleBulkEditData.SPECIMEN_CONDITION, ComboBox.class);
        addField(SampleBulkEditData.NO_TEST_POSSIBLE_REASON, TextField.class);
       
        setEnabled(false,
                SampleBulkEditData.SHIPPED,
                SampleBulkEditData.SHIPMENT_DATE,
                SampleBulkEditData.SHIPMENT_DETAILS);
        setEnabled(false, 
                SampleBulkEditData.RECEIVED,
                SampleBulkEditData.RECEIVED_DATE,
                SampleBulkEditData.LAB_SAMPLE_ID,
                SampleBulkEditData.SPECIMEN_CONDITION,
                SampleBulkEditData.NO_TEST_POSSIBLE_REASON);
        
        shippedCheckBox.addValueChangeListener( e ->
                setEnabled((boolean) e.getProperty().getValue(),
                        SampleBulkEditData.SHIPPED,
                        SampleBulkEditData.SHIPMENT_DATE,
                        SampleBulkEditData.SHIPMENT_DETAILS));

        recievedCheckBox.addValueChangeListener(e ->
                setEnabled((boolean) e.getProperty().getValue(),
                SampleBulkEditData.RECEIVED,
                SampleBulkEditData.RECEIVED_DATE,
                SampleBulkEditData.LAB_SAMPLE_ID,
                SampleBulkEditData.SPECIMEN_CONDITION,
                SampleBulkEditData.NO_TEST_POSSIBLE_REASON));

        FieldHelper.setVisibleWhen(
                getFieldGroup(),
                Arrays.asList(SampleBulkEditData.SHIPMENT_DATE, SampleBulkEditData.SHIPMENT_DETAILS),
                SampleBulkEditData.SHIPPED,
                List.of(true),
                true);
        FieldHelper.setVisibleWhen(
                getFieldGroup(),
                Arrays.asList(SampleBulkEditData.RECEIVED_DATE, SampleBulkEditData.LAB_SAMPLE_ID, SampleBulkEditData.SPECIMEN_CONDITION),
                SampleDto.RECEIVED,
                List.of(true),
                true);
        FieldHelper.setVisibleWhen(
                getFieldGroup(),
                SampleBulkEditData.NO_TEST_POSSIBLE_REASON,
                SampleBulkEditData.SPECIMEN_CONDITION,
                List.of(SpecimenCondition.NOT_ADEQUATE),
                true);

        receivedDate.addValidator(
                new DateComparisonValidator(
                        receivedDate,
                        shipmentDate,
                        false,
                        false,
                        I18nProperties.getValidationError(Validations.afterDate, receivedDate.getCaption(), shipmentDate.getCaption())));
    }

    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

}
