package de.symeda.sormas.ui.importer;

import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.ui.UI;
import de.symeda.sormas.api.importexport.InvalidColumnException;
import de.symeda.sormas.api.importexport.ValueSeparator;
import de.symeda.sormas.api.user.UserDto;
import de.symeda.sormas.ui.AbstractBeanTest;
import de.symeda.sormas.ui.caze.importer.CaseImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

public class ValidateCaseImportDataTest extends AbstractBeanTest {
    @Test
    void validateTest() throws IOException, CsvValidationException, InvalidColumnException, InterruptedException {
        var file = new File("C:\\aj\\project\\SORMAS-Project\\sormas-ui\\src\\test\\resources\\sormas_case_import_test_vaccinations.csv");
        var userDto = new UserDto();
        var caseImport = new CaseImporter(file, true, userDto, ValueSeparator.COMMA);
        var currenUi = Mockito.mock(UI.class);
        ImportResultStatus importResultStatus = caseImport.runImport();
        Assertions.assertEquals(importResultStatus, ImportResultStatus.COMPLETED);
    }
}
