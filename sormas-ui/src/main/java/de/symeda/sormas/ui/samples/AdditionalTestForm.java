package de.symeda.sormas.ui.samples;

import static de.symeda.sormas.ui.utils.CssStyles.H4;
import static de.symeda.sormas.ui.utils.CssStyles.VSPACE_TOP_3;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocsCss;
import static de.symeda.sormas.ui.utils.LayoutUtil.loc;
import static de.symeda.sormas.ui.utils.LayoutUtil.locCss;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;

import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Validations;
import de.symeda.sormas.api.sample.AdditionalTestDto;
import de.symeda.sormas.api.sample.SampleDto;
import de.symeda.sormas.api.sample.ncd.*;
import de.symeda.sormas.ui.samples.ncd.*;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.DateComparisonValidator;
import de.symeda.sormas.ui.utils.DateTimeField;


public class AdditionalTestForm extends AbstractEditForm<AdditionalTestDto> {

	private static final long serialVersionUID = 7231529309871021783L;

	private static final String BLOOD_GAS_HEADING_LOC = "bloodGasHeadingLoc";

	//@formatter:off
	private static final String HTML_LAYOUT =
			fluidRowLocs(AdditionalTestDto.TEST_DATE_TIME, "") +
			fluidRowLocs(AdditionalTestDto.HAEMOGLOBINURIA, AdditionalTestDto.PROTEINURIA) +
			fluidRowLocs(AdditionalTestDto.HEMATURIA, "") +

			loc(BLOOD_GAS_HEADING_LOC) +
			fluidRowLocs(AdditionalTestDto.ARTERIAL_VENOUS_GAS_PH, AdditionalTestDto.ARTERIAL_VENOUS_GAS_PCO2,
					AdditionalTestDto.ARTERIAL_VENOUS_GAS_PAO2, AdditionalTestDto.ARTERIAL_VENOUS_GAS_HCO3) +
			fluidRowLocs(AdditionalTestDto.GAS_OXYGEN_THERAPY, "") +
			fluidRowLocsCss(VSPACE_TOP_3, AdditionalTestDto.ALT_SGPT, AdditionalTestDto.TOTAL_BILIRUBIN) +
			fluidRowLocs(AdditionalTestDto.AST_SGOT, AdditionalTestDto.CONJ_BILIRUBIN) +
			fluidRowLocs(AdditionalTestDto.CREATININE, AdditionalTestDto.WBC_COUNT) +
			fluidRowLocs(AdditionalTestDto.POTASSIUM, AdditionalTestDto.PLATELETS) +
			fluidRowLocs(AdditionalTestDto.UREA, AdditionalTestDto.PROTHROMBIN_TIME) +
			fluidRowLocs(AdditionalTestDto.HAEMOGLOBIN, "") +
			loc(AdditionalTestDto.OTHER_TEST_RESULTS) +

			/*locCss(VSPACE_TOP_3, AdditionalTestDto.HAS_HEALTH_SCREENING_TEST) +
			fluidRowLocs(HealthScreeningTestDto.HEALTH_SCREENING_TEST)+*/
			locCss(VSPACE_TOP_3, AdditionalTestDto.HAS_RFT) +
			fluidRowLocs(RftSampleDto.RFT_SAMPLE) +
			locCss(VSPACE_TOP_3, AdditionalTestDto.HAS_LIPID_PROFILE) +
			fluidRowLocs(LipidProfileSampleDto.LIPID_PROFILE_SAMPLE) +
			locCss(VSPACE_TOP_3, AdditionalTestDto.HAS_LFT) +
			fluidRowLocs(LftSampleDto.LFT_SAMPLE) +
			locCss(VSPACE_TOP_3, AdditionalTestDto.HAS_URINE_RE) +
			fluidRowLocs(UrineRoutineExaminationSampleDto.URINE_ROUTINE_EXAMINATION) +
			locCss(VSPACE_TOP_3, AdditionalTestDto.HAS_COMPLETE_BLOOD_COUNT) +
			fluidRowLocs(CompleteBloodCountSampleDto.COMPLETE_BLOOD_COUNT_SAMPLE) ;

	//@formatter:on

	private LipidProfileSampleCreateForm lipidProfileSampleCreateForm;
	private RftSampleCreateForm rftSampleCreateForm;
	private LftSampleCreateForm lftSampleCreateForm;
	private CompleteBloodCountSampleCreateForm completeBloodCountSampleCreateForm;

	//private HealthScreeningTestForm healthScreeningTestForm;
	private UrineRoutineExaminationSampleForm urineRoutineExaminationForm;
	private final SampleDto sample;

	public AdditionalTestForm(SampleDto sample, boolean create) {
		super(AdditionalTestDto.class, AdditionalTestDto.I18N_PREFIX);

		this.sample = sample;
		setWidth(600, Unit.PIXELS);

		addFields();
		if (create) {
			hideValidationUntilNextCommit();
		}
	}

	@Override
	protected void addFields() {
		if (sample == null) {
			return;
		}

		Label bloodGasHeadingLabel =
			new Label(I18nProperties.getPrefixCaption(AdditionalTestDto.I18N_PREFIX, AdditionalTestDto.ARTERIAL_VENOUS_BLOOD_GAS));
		bloodGasHeadingLabel.addStyleName(H4);
		getContent().addComponent(bloodGasHeadingLabel, BLOOD_GAS_HEADING_LOC);

		DateTimeField testDateTimeField = addField(AdditionalTestDto.TEST_DATE_TIME, DateTimeField.class);
		testDateTimeField.setRequired(true);
		testDateTimeField.addValidator(
			new DateComparisonValidator(
				testDateTimeField,
				sample.getSampleDateTime(),
				false,
				false,
				I18nProperties.getValidationError(
					Validations.afterDate,
					testDateTimeField.getCaption(),
					I18nProperties.getPrefixCaption(SampleDto.I18N_PREFIX, SampleDto.SAMPLE_DATE_TIME))));

		addField(AdditionalTestDto.HAEMOGLOBINURIA, ComboBox.class);
		addField(AdditionalTestDto.PROTEINURIA, ComboBox.class);
		addField(AdditionalTestDto.HEMATURIA, ComboBox.class);

		TextField bloodGasPHField = addField(AdditionalTestDto.ARTERIAL_VENOUS_GAS_PH, TextField.class);
		bloodGasPHField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, bloodGasPHField.getCaption()));
		TextField bloodGasPco2Field = addField(AdditionalTestDto.ARTERIAL_VENOUS_GAS_PCO2, TextField.class);
		bloodGasPco2Field.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, bloodGasPco2Field.getCaption()));
		TextField bloodGasPao2Field = addField(AdditionalTestDto.ARTERIAL_VENOUS_GAS_PAO2, TextField.class);
		bloodGasPao2Field.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, bloodGasPao2Field.getCaption()));
		TextField bloodGasHco3Field = addField(AdditionalTestDto.ARTERIAL_VENOUS_GAS_HCO3, TextField.class);
		bloodGasHco3Field.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, bloodGasHco3Field.getCaption()));
		TextField gasOxygenTherapyField = addField(AdditionalTestDto.GAS_OXYGEN_THERAPY, TextField.class);
		gasOxygenTherapyField
			.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, gasOxygenTherapyField.getCaption()));
		TextField altSgptField = addField(AdditionalTestDto.ALT_SGPT, TextField.class);
		altSgptField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, altSgptField.getCaption()));
		TextField astSgotField = addField(AdditionalTestDto.AST_SGOT, TextField.class);
		astSgotField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, astSgotField.getCaption()));
		TextField creatinineField = addField(AdditionalTestDto.CREATININE, TextField.class);
		creatinineField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, creatinineField.getCaption()));
		TextField potassiumField = addField(AdditionalTestDto.POTASSIUM, TextField.class);
		potassiumField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, potassiumField.getCaption()));
		TextField ureaField = addField(AdditionalTestDto.UREA, TextField.class);
		ureaField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, ureaField.getCaption()));
		TextField haemoglobinField = addField(AdditionalTestDto.HAEMOGLOBIN, TextField.class);
		haemoglobinField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, haemoglobinField.getCaption()));
		TextField totalBilirubinField = addField(AdditionalTestDto.TOTAL_BILIRUBIN, TextField.class);
		totalBilirubinField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, totalBilirubinField.getCaption()));
		TextField conjBilirubinField = addField(AdditionalTestDto.CONJ_BILIRUBIN, TextField.class);
		conjBilirubinField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, conjBilirubinField.getCaption()));
		TextField wbcCountField = addField(AdditionalTestDto.WBC_COUNT, TextField.class);
		wbcCountField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, wbcCountField.getCaption()));
		TextField plateletsField = addField(AdditionalTestDto.PLATELETS, TextField.class);
		plateletsField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, plateletsField.getCaption()));
		TextField prothrombinTimeField = addField(AdditionalTestDto.PROTHROMBIN_TIME, TextField.class);
		prothrombinTimeField.setConversionError(I18nProperties.getValidationError(Validations.onlyNumbersAllowed, prothrombinTimeField.getCaption()));
		addField(AdditionalTestDto.OTHER_TEST_RESULTS, TextArea.class).setRows(6);
		addFieldsRelatedToNcdDisease();
	}

	private void addFieldsRelatedToNcdDisease() {
		//addField(SampleDto.HAS_PREMIUM_HEALTH_PACKAGE, CheckBox.class);
		boolean isNcdDisease = true;
		if (!isNcdDisease)
			return;

		/*healthScreeningTestForm = new HealthScreeningTestForm();
		addNcdFieldForm(AdditionalTestDto.HAS_HEALTH_SCREENING_TEST, HealthScreeningTestDto.HEALTH_SCREENING_TEST, healthScreeningTestForm);
*/
		lipidProfileSampleCreateForm = new LipidProfileSampleCreateForm();
		addNcdFieldForm(AdditionalTestDto.HAS_LIPID_PROFILE, LipidProfileSampleDto.LIPID_PROFILE_SAMPLE, lipidProfileSampleCreateForm);

		rftSampleCreateForm = new RftSampleCreateForm();
		CheckBox hadRftCheckBox = addNcdFieldForm(AdditionalTestDto.HAS_RFT, RftSampleDto.RFT_SAMPLE, rftSampleCreateForm);

		lftSampleCreateForm = new LftSampleCreateForm();
		CheckBox hadLftCheckBox = addNcdFieldForm(AdditionalTestDto.HAS_LFT, LftSampleDto.LFT_SAMPLE, lftSampleCreateForm);

		completeBloodCountSampleCreateForm = new CompleteBloodCountSampleCreateForm();
		CheckBox hadCDCCheckBox = addNcdFieldForm(AdditionalTestDto.HAS_COMPLETE_BLOOD_COUNT, CompleteBloodCountSampleDto.COMPLETE_BLOOD_COUNT_SAMPLE, completeBloodCountSampleCreateForm);

		urineRoutineExaminationForm = new UrineRoutineExaminationSampleForm();
		addNcdFieldForm(AdditionalTestDto.HAS_URINE_RE, UrineRoutineExaminationSampleDto.URINE_ROUTINE_EXAMINATION, urineRoutineExaminationForm);
		urineRoutineExaminationForm.setWidth(urineRoutineExaminationForm.getWidth() * 7 / 12, Unit.PIXELS);
		setVisible(false, LipidProfileSampleDto.LIPID_PROFILE_SAMPLE,
				LftSampleDto.LFT_SAMPLE,
				RftSampleDto.RFT_SAMPLE,
				CompleteBloodCountSampleDto.COMPLETE_BLOOD_COUNT_SAMPLE,
				//HealthScreeningTestDto.HEALTH_SCREENING_TEST,
				UrineRoutineExaminationSampleDto.URINE_ROUTINE_EXAMINATION);
	}

	private CheckBox addNcdFieldForm(String checkBoxId, String fromId, Component form) {
		CheckBox checkBox = addField(checkBoxId, CheckBox.class);
		getContent().addComponent(form, fromId);
		checkBox.addValueChangeListener(e -> setVisible(checkBox.getValue(), fromId));
		form.setWidth(form.getWidth() * 9 / 12, Unit.PIXELS);
		return checkBox;
	}


	public void updateNcdSampleFormValue() {
		AdditionalTestDto value = getValue();
			if (value.getRftSampleDto() != null) {
				rftSampleCreateForm.setValue(getValue().getRftSampleDto());
			} else  {
				rftSampleCreateForm.setValue(new RftSampleDto());
			}
			if (value.getLftSampleDto() != null) {
				lftSampleCreateForm.setValue(getValue().getLftSampleDto());
			} else  {
				lftSampleCreateForm.setValue(new LftSampleDto());
			}
			if (value.getLipidProfileSampleDto() != null) {
				lipidProfileSampleCreateForm.setValue(value.getLipidProfileSampleDto());
			} else {
				lipidProfileSampleCreateForm.setValue(new LipidProfileSampleDto());
			}
			if (value.getCompleteBloodCountSampleDto() != null) {
				completeBloodCountSampleCreateForm.setValue(value.getCompleteBloodCountSampleDto());
			} else {
				completeBloodCountSampleCreateForm.setValue(new CompleteBloodCountSampleDto());
			}
			if (value.getUrineRoutineExaminationSampleDto() != null) {
				urineRoutineExaminationForm.setValue(value.getUrineRoutineExaminationSampleDto());
			} else {
				urineRoutineExaminationForm.setValue(new UrineRoutineExaminationSampleDto());
			}


	}

	public void updateNcdSampleDtoValue() {
		rftSampleCreateForm.commit();
		getValue().setRftSampleDto(rftSampleCreateForm.getValue());
		lftSampleCreateForm.commit();
		getValue().setLftSampleDto(lftSampleCreateForm.getValue());
		lipidProfileSampleCreateForm.commit();
		getValue().setLipidProfileSampleDto(lipidProfileSampleCreateForm.getValue());
		completeBloodCountSampleCreateForm.commit();
		getValue().setCompleteBloodCountSampleDto(completeBloodCountSampleCreateForm.getValue());
		urineRoutineExaminationForm.commit();
		getValue().setUrineRoutineExaminationSampleDto(urineRoutineExaminationForm.getValue());
	}



	@Override
	protected String createHtmlLayout() {
		return HTML_LAYOUT;
	}
}
