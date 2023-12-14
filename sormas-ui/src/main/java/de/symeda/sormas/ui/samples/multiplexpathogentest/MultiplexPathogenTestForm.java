package de.symeda.sormas.ui.samples.multiplexpathogentest;

import com.vaadin.v7.data.Validator;
import com.vaadin.v7.ui.VerticalLayout;
import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.sample.PathogenTestType;
import de.symeda.sormas.api.sample.multiplexpathogentest.MultiplexPathogenTestDiseaseDto;

import java.util.ArrayList;
import java.util.List;

public  class MultiplexPathogenTestForm extends VerticalLayout {
    public MultiplexPathogenTestForm() {

    }

    public void addMultiplexRow() {
        removeAllComponents();

        var infAForm = new MultiplexPathogenTestDiseaseForm(Disease.INFLUENZA_A);
        infAForm.setValue(buildMultiplexPathogenTest(Disease.INFLUENZA_A));
        addComponent(infAForm);

        var infBForm = new MultiplexPathogenTestDiseaseForm(Disease.INFLUENZA_B);
        infBForm.setValue(buildMultiplexPathogenTest(Disease.INFLUENZA_B));
        addComponent(infBForm);

        var sarCovForm = new MultiplexPathogenTestDiseaseForm(Disease.CORONAVIRUS);
        sarCovForm.setValue(buildMultiplexPathogenTest(Disease.CORONAVIRUS));
        addComponent(sarCovForm);
    }

    private MultiplexPathogenTestDiseaseDto buildMultiplexPathogenTest(Disease disease) {
        var dto = new MultiplexPathogenTestDiseaseDto();
        dto.setTestedDisease(disease);
        return dto;
    }

    public void validate() throws Validator.InvalidValueException {
        var totalChild = getComponentCount();
        for (int i = 0; i < totalChild; i++) {
            var form = (MultiplexPathogenTestDiseaseForm) getComponent(i);
            form.validate();
        }
    }

    public List<MultiplexPathogenTestDiseaseDto> multiplexValues() {
        List<MultiplexPathogenTestDiseaseDto> result = new ArrayList<>();
        var totalChild = getComponentCount();
        for (int i = 0; i < totalChild; i++) {
            var form = (MultiplexPathogenTestDiseaseForm) getComponent(i);
            form.commit();
            result.add(form.getValue());
        }
        return result;
    }

}


