package de.symeda.sormas.ui.symptoms;

import com.vaadin.v7.ui.Field;
import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.caze.CaseDataDto;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.symptoms.SymptomsContext;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.api.symptoms.TypeOfLeprosy;
import de.symeda.sormas.api.user.PseudonymizableDataAccessLevel;
import de.symeda.sormas.api.utils.fieldaccess.UiFieldAccessCheckers;
import de.symeda.sormas.ui.AbstractBeanTest;
import de.symeda.sormas.ui.utils.ViewMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymptomsFormTest extends AbstractBeanTest {

    @Test
    void creatFormTest() {
        createSymptomsForm(Disease.MALARIA);
    }

    @Test
    void testForDisease() {
        var leprosySymptomsForm = createSymptomsForm(Disease.LEPROSY);
        Assertions.assertTrue(leprosySymptomsForm.getField(SymptomsDto.TYPE_OF_LEPROSY).isVisible());
        Assertions.assertFalse(leprosySymptomsForm.getField(SymptomsDto.TIME_OF_RFT).isVisible());

        var maleriaSymptomsForm = createSymptomsForm(Disease.MALARIA);
        Assertions.assertFalse(maleriaSymptomsForm.getField(SymptomsDto.TYPE_OF_LEPROSY).isVisible());
    }

    private SymptomsForm createSymptomsForm(Disease disease) {
        var caseDto = new CaseDataDto();
        var personDto = new PersonDto();
      return  new SymptomsForm(caseDto,
                disease,
                personDto,
                SymptomsContext.CASE,
                ViewMode.NORMAL,
                UiFieldAccessCheckers.forDataAccessLevel(PseudonymizableDataAccessLevel.NONE, false)
        );
    }

}