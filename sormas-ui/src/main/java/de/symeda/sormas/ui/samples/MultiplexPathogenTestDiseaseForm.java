package de.symeda.sormas.ui.samples;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

import java.util.List;

import com.vaadin.v7.ui.ComboBox;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.sample.PathogenTestResultType;
import de.symeda.sormas.api.sample.multiplexpathogentest.InfluenzaAPathogenTestResult;
import de.symeda.sormas.api.sample.multiplexpathogentest.InfluenzaBPathogenTestResult;
import de.symeda.sormas.api.sample.multiplexpathogentest.MultiplexPathogenTestDiseaseDto;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.FieldHelper;

public class MultiplexPathogenTestDiseaseForm extends AbstractEditForm<MultiplexPathogenTestDiseaseDto> {

	//@formatter:off
    private static final String  HTML_LAYOUT = fluidRowLocs(MultiplexPathogenTestDiseaseDto.TESTED_DISEASE, MultiplexPathogenTestDiseaseDto.TEST_RESULT)
                         + fluidRowLocs( MultiplexPathogenTestDiseaseDto.CQ_VALUE)
						 + fluidRowLocs(MultiplexPathogenTestDiseaseDto.INFLUENZA_A_TEST_RESULT, MultiplexPathogenTestDiseaseDto.INFLUENZA_A_OTHER_TEST_RESULT)
						 + fluidRowLocs(MultiplexPathogenTestDiseaseDto.INFLUENZA_B_TEST_RESULT, MultiplexPathogenTestDiseaseDto.INFLUENZA_B_OTHER_TEST_RESULT);
    //@formatter:on
	private Disease disease;

	public MultiplexPathogenTestDiseaseForm(Disease disease) {
		super(MultiplexPathogenTestDiseaseDto.class, MultiplexPathogenTestDiseaseDto.I18N_PREFIX, false);
		this.disease = disease;
		addFields();
	}

	@Override
	protected String createHtmlLayout() {
		return HTML_LAYOUT;
	}

	@Override
	protected void addFields() {
		ComboBox diseaseCombox = addField(MultiplexPathogenTestDiseaseDto.TESTED_DISEASE, ComboBox.class);
		addField(MultiplexPathogenTestDiseaseDto.TEST_RESULT, ComboBox.class);
		addField(MultiplexPathogenTestDiseaseDto.INFLUENZA_A_TEST_RESULT, ComboBox.class);
		addField(MultiplexPathogenTestDiseaseDto.INFLUENZA_B_TEST_RESULT, ComboBox.class);
		addFields(
			MultiplexPathogenTestDiseaseDto.INFLUENZA_A_OTHER_TEST_RESULT,
			MultiplexPathogenTestDiseaseDto.INFLUENZA_B_OTHER_TEST_RESULT,
			MultiplexPathogenTestDiseaseDto.CQ_VALUE);
		diseaseCombox.setEnabled(false);
		setVisible(
			false,
			MultiplexPathogenTestDiseaseDto.INFLUENZA_A_TEST_RESULT,
			MultiplexPathogenTestDiseaseDto.INFLUENZA_B_TEST_RESULT,
			MultiplexPathogenTestDiseaseDto.CQ_VALUE);
		setRequired(true, MultiplexPathogenTestDiseaseDto.TESTED_DISEASE, MultiplexPathogenTestDiseaseDto.TEST_RESULT);
		if (List.of(Disease.INFLUENZA_A, Disease.INFLUENZA_B, Disease.CORONAVIRUS).contains(disease)) {
			FieldHelper.setVisibleWhen(
				getFieldGroup(),
				List.of(MultiplexPathogenTestDiseaseDto.CQ_VALUE),
				MultiplexPathogenTestDiseaseDto.TEST_RESULT,
				PathogenTestResultType.POSITIVE,
				true);
		}
		if (disease == Disease.INFLUENZA_A) {
			FieldHelper.setVisibleWhen(
					getFieldGroup(),
					List.of(MultiplexPathogenTestDiseaseDto.INFLUENZA_A_TEST_RESULT),
					MultiplexPathogenTestDiseaseDto.TEST_RESULT,
					PathogenTestResultType.POSITIVE,
					true);
		}
		if (disease == Disease.INFLUENZA_B) {
			FieldHelper.setVisibleWhen(
					getFieldGroup(),
					List.of(MultiplexPathogenTestDiseaseDto.INFLUENZA_B_TEST_RESULT),
					MultiplexPathogenTestDiseaseDto.TEST_RESULT,
					PathogenTestResultType.POSITIVE,
					true);
		}
		FieldHelper.setVisibleWhen(
			getFieldGroup(),
			MultiplexPathogenTestDiseaseDto.INFLUENZA_A_OTHER_TEST_RESULT,
			MultiplexPathogenTestDiseaseDto.INFLUENZA_A_TEST_RESULT,
			InfluenzaAPathogenTestResult.OTHERS,
			true);
		FieldHelper.setVisibleWhen(
			getFieldGroup(),
			MultiplexPathogenTestDiseaseDto.INFLUENZA_B_OTHER_TEST_RESULT,
			MultiplexPathogenTestDiseaseDto.INFLUENZA_B_TEST_RESULT,
			InfluenzaBPathogenTestResult.OTHERS,
			true);
	}
}
