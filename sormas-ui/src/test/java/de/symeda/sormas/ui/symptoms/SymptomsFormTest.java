package de.symeda.sormas.ui.symptoms;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.caze.CaseDataDto;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.symptoms.SymptomsContext;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.api.user.PseudonymizableDataAccessLevel;
import de.symeda.sormas.api.utils.fieldaccess.UiFieldAccessCheckers;
import de.symeda.sormas.ui.AbstractUiBeanTest;
import de.symeda.sormas.ui.utils.ViewMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SymptomsFormTest extends AbstractUiBeanTest {

    @Test
    void creatFormTest() {
        createSymptomsForm(Disease.MALARIA);
    }

    @Test
    void testForDisease() {
        var leprosySymptomsForm = createSymptomsForm(Disease.LEPROSY);

    }

    @Test
    void testForSAPHUDisease() {
        SymptomsForm saphuSymForm = createSymptomsForm(Disease.SHAPU);
        Assertions.assertTrue(saphuSymForm.getField(SymptomsDto.CIRCUMCILIARY_CONGESTION).isVisible());
        Assertions.assertTrue(saphuSymForm.getField(SymptomsDto.PUNCTATE_KERATITIS).isVisible());
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