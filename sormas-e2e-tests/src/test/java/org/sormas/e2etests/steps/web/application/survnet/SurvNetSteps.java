package org.sormas.e2etests.steps.web.application.survnet;

import static org.sormas.e2etests.helpers.SchemaValidator.XMLSchemaValidator.validateXMLSchema;
import static org.sormas.e2etests.helpers.comparison.XMLComparison.compareXMLFiles;
import static org.sormas.e2etests.helpers.comparison.XMLComparison.extractDiffNodes;
import static org.sormas.e2etests.pages.application.AboutPage.SORMAS_VERSION_LINK;
import static org.sormas.e2etests.steps.web.application.cases.EditCaseSteps.externalUUID;
import static org.sormas.e2etests.steps.web.application.cases.SymptomsTabSteps.symptoms;
import static org.sormas.e2etests.steps.web.application.vaccination.CreateNewVaccinationSteps.vaccination;

import cucumber.api.java8.En;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.sormas.e2etests.helpers.WebDriverHelpers;
import org.sormas.e2etests.helpers.parsers.XMLParser;
import org.sormas.e2etests.pages.application.NavBarPage;
import org.sormas.e2etests.steps.web.application.cases.CreateNewCaseSteps;
import org.sormas.e2etests.steps.web.application.events.EditEventSteps;
import org.sormas.e2etests.steps.web.application.persons.EditPersonSteps;
import org.testng.asserts.SoftAssert;

@Slf4j
public class SurvNetSteps implements En {

  private final WebDriverHelpers webDriverHelpers;
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private String sormasActualVersion;
  private static Document singleXmlFile;
  private static Document bulkXmlFile;

  @Inject
  public SurvNetSteps(WebDriverHelpers webDriverHelpers, SoftAssert softly) {
    this.webDriverHelpers = webDriverHelpers;

    Then(
        "I check if {string} in SORMAS generated XML file is correct",
        (String typeOfDate) -> {
          LocalDate expectedDate = LocalDate.now();

          switch (typeOfDate) {
            case "date of report":
              LocalDate dateOfReport = getReportingDate(singleXmlFile, 0);
              softly.assertEquals(dateOfReport, expectedDate, "Date of report is incorrect!");
              softly.assertAll();
              break;
            case "change at date":
              LocalDate changeAtDate = getChangedAt(singleXmlFile, 0);
              softly.assertEquals(changeAtDate, expectedDate, "Change at date is incorrect!");
              softly.assertAll();
              break;
            case "created at date":
              LocalDate createdAt = getCreatedAt(singleXmlFile);
              softly.assertEquals(createdAt, expectedDate, "Created at date is incorrect!");
              softly.assertAll();
              break;
            case "tracked at date":
              LocalDate trackedAt = getTrackedAt(singleXmlFile, 0);
              softly.assertEquals(trackedAt, expectedDate, "Tracked at date is incorrect!");
              softly.assertAll();
              break;
            case "vaccination date":
              LocalDate vaccinationDate =
                      getDateValueFromSpecificChildrenFieldByName(singleXmlFile, "VaccinationDate");
              LocalDate expectedVaccinationDate = vaccination.getVaccinationDate();
              softly.assertEquals(
                  vaccinationDate, expectedVaccinationDate, "Vaccination date is incorrect!");
              softly.assertAll();
              break;
          }
        });

    Then(
        "I check if Vaccine name in SORMAS generated XML file is correct",
        () -> {
          String vaccineNamefromXml = getValueFromSpecificFieldByName(singleXmlFile, "Vaccine");
          String expectedVaccineName = vaccination.getVaccineName();
          String vaccineName = null;
          switch (vaccineNamefromXml) {
            case "201":
              vaccineName = "Comirnaty (COVID-19-mRNA Impfstoff)";
              break;
            case "211":
              vaccineName = "mRNA/bivalent BA.1 (BioNTech/Pfizer)";
              break;
            case "215":
              vaccineName = "mRNA/bivalent BA.4/5 (BioNTech/Pfizer)";
              break;
            case "202":
              vaccineName = "COVID-19 Impfstoff Moderna (mRNA-Impfstoff)";
              break;
            case "212":
              vaccineName = "mRNA/bivalent BA.1 (Moderna)";
              break;
            case "216":
              vaccineName = "mRNA/bivalent BA.4/5 (Moderna)";
              break;
            case "213":
              vaccineName = "inaktiviert (Valneva)";
              break;
            case "206":
              vaccineName = "NVX-CoV2373 COVID-19 Impfstoff (Novavax)";
              break;
            case "214":
              vaccineName = "proteinbasiert, rekombinant (Novavax)";
              break;
          }
          softly.assertEquals(vaccineName, expectedVaccineName, "Vaccine name is incorrect!");
          softly.assertAll();
        });

    And(
        "I check if sex in SORMAS generated single XML file is correct",
        () -> {
          String sex = getSexDE(singleXmlFile, 0);
          String expectedSex = CreateNewCaseSteps.survnetCase.getSex();
          softly.assertEquals(sex, expectedSex, "Sex is incorrect!");
          softly.assertAll();
        });

    And(
        "I check if \"([^\"]*)\" SYMPTOM in SORMAS generated single XML file is correct",
        (String symptom) -> {
          // ToDO -consider to change all expecrted... to mor global expectedValue !!!
          String expectedValue = null;
          switch (symptom) {
            case "Fever":
              String feverOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0088");
              String feverOptionFromUI = symptoms.getFever();

              if (feverOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  feverOptionFromXml, expectedValue, symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Shivering":
              String shiveringOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0101");
              String shiveringOptionFromUI = symptoms.getFever();

              if (shiveringOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  shiveringOptionFromXml, expectedValue, symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Headache":
              String headacheOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0101");
              String headacheOptionFromUI = symptoms.getHeadache();

              if (headacheOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  headacheOptionFromXml,
                  expectedValue,
                  symptom + " Headache checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Muscle Pain":
              String musclePaineOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0101");
              String musclePaineOptionFromUI = symptoms.getMusclePain();

              if (musclePaineOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  musclePaineOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Feeling Ill":
              String feelingIllOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0101");
              String feelingIllOptionFromUI = symptoms.getFeelingIll();

              if (feelingIllOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  feelingIllOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Acute Respiratory Distress Syndrome":
              String acuteRespiratoryDistressSyndromeOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0394");
              String acuteRespiratoryDistressSyndromeOptionFromUI =
                  symptoms.getAcuteRespiratoryDistressSyndrome();

              if (acuteRespiratoryDistressSyndromeOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  acuteRespiratoryDistressSyndromeOptionFromXml,
                  expectedValue,
                  symptom + "AcuteRespiratoryDistressSyndrome checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Sore Throat":
              String soreThroatOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0103");
              String soreThroatOptionFromUI = symptoms.getSoreThroat();

              if (soreThroatOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  soreThroatOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Cough":
              String coughOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0130");
              String coughOptionFromUI = symptoms.getCough();

              if (coughOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  coughOptionFromXml, expectedValue, symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Runny Nose":
              String runnyNoseOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0225");
              String runnyNoseOptionFromUI = symptoms.getRunnyNose();

              if (runnyNoseOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  runnyNoseOptionFromXml, expectedValue, symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Pneumonia Clinical Or Radiologic":
              String pneumoniaClinicalOrRadiologicOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0158");
              String pneumoniaClinicalOrRadiologicOptionFromUI =
                  symptoms.getPneumoniaClinicalOrRadiologic();

              if (pneumoniaClinicalOrRadiologicOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  pneumoniaClinicalOrRadiologicOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Respiratory Disease Ventilation":
              String respiratoryDiseaseVentilationOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0393");
              String respiratoryDiseaseVentilationOptionFromUI =
                  symptoms.getRespiratoryDiseaseVentilation();

              if (respiratoryDiseaseVentilationOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  respiratoryDiseaseVentilationOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Rapid Breathing":
              String rapidBreathingOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0418");
              String rapidBreathingOptionFromUI = symptoms.getRapidBreathing();

              if (rapidBreathingOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  rapidBreathingOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Difficulty Breathing":
              String difficultyBreathingOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0064");
              String difficultyBreathingOptionFromUI = symptoms.getDifficultyBreathing();

              if (difficultyBreathingOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  difficultyBreathingOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Fast Heart Rate":
              String fastHeartRateOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0245");
              String fastHeartRateOptionFromUI = symptoms.getFastHeartRate();

              if (fastHeartRateOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  fastHeartRateOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Diarrhea":
              String diarrheaOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0062");
              String diarrheaOptionFromUI = symptoms.getDiarrhea();

              if (diarrheaOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  diarrheaOptionFromXml, expectedValue, symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Nausea":
              String nauseaOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0101");
              String nauseaOptionFromUI = symptoms.getNausea();

              if (nauseaOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  nauseaOptionFromXml, expectedValue, symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Loss Of Smell":
              String lossOfSmellOptionfromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0416");
              String lossOfSmellOptionfromUI = symptoms.getLossOfSmell();

              if (lossOfSmellOptionfromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  lossOfSmellOptionfromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Loss OfTaste":
              String lossOfTasteOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0417");
              String lossOfTasteOptionFromUI = symptoms.getLossOfTaste();

              if (lossOfTasteOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  lossOfTasteOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Other Non Hemorrhagic Symptoms":
              String otherNonHemorrhagicSymptomsOptionFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "Symptom0101");
              String otherNonHemorrhagicSymptomsOptionFromUI =
                  symptoms.getOtherNonHemorrhagicSymptoms();

              if (otherNonHemorrhagicSymptomsOptionFromUI == "JA") expectedValue = "true";
              else expectedValue = "false";

              softly.assertEquals(
                  otherNonHemorrhagicSymptomsOptionFromXml,
                  expectedValue,
                  symptom + " checkbox value is incorrect!");
              softly.assertAll();
              break;
            case "Onset Of Disease":
              String onsetOfDiseaseOptionDateFromXml =
                  getValueFromSpecificFieldByName(singleXmlFile, "OnsetOfDisease").substring(0, 10);
              String expectedOnsetOfDiseaseDateFromUI = symptoms.getDateOfSymptom().toString();

              softly.assertEquals(
                  onsetOfDiseaseOptionDateFromXml,
                  expectedOnsetOfDiseaseDateFromUI,
                  symptom + " date value is incorrect!");
              softly.assertAll();
              break;
          }
        });

    And(
        "I check if sex for all {int} cases in SORMAS generated bulk XML file is correct",
        (Integer caseNumber) -> {
          for (int i = 0; i < caseNumber; i++) {
            String sex = getSexDE(bulkXmlFile, i);
            String expectedSex = EditPersonSteps.personSex.get(i);
            softly.assertEquals(sex, expectedSex, "Sex is incorrect!");
            softly.assertAll();
          }
        });

    And(
        "^I compare the SORMAS generated XML file with the example one$",
        () -> {
          List<String> diffs =
              compareXMLFiles(
                  "src/main/resources/survnetXMLTemplates/controlXml.xml",
                  "/srv/dockerdata/jenkins_new/sormas-files/case_"
                      + externalUUID.get(0).substring(1, 37)
                      + ".xml");
          List<String> nodes = extractDiffNodes(diffs, "/[Transport][^\\s]+");

          softly.assertTrue(nodes.size() < 12, "Number of differences is incorrect!");
          softly.assertAll();

          List<String> expectedList = new ArrayList<>();
          expectedList.add("/Transport[1]/@CreatedAt");
          expectedList.add("/Transport[1]/@GuidTransport");
          expectedList.add("/Transport[1]/@TransportNumber");
          expectedList.add("/Transport[1]/CVD[1]/@ChangedAt");
          expectedList.add("/Transport[1]/CVD[1]/@GuidRecord");
          expectedList.add("/Transport[1]/CVD[1]/@ReportingDate");
          expectedList.add("/Transport[1]/CVD[1]/@Token");
          expectedList.add("/Transport[1]/CVD[1]/Field[1]/@Value");
          expectedList.add("/Transport[1]/CVD[1]/Track[1]/@GuidTrack");
          expectedList.add("/Transport[1]/CVD[1]/Track[1]/@TrackedAt");

          if (!expectedList.containsAll(nodes)) {
            expectedList.add("/Transport[1]/CVD[1]/Track[1]/@Software");
            softly.assertTrue(
                expectedList.containsAll(nodes),
                "The expected differences in the XML files are different");
            softly.assertAll();
          }
        });

    And(
        "^I check the SORMAS generated XML file structure with XSD Schema file$",
        () -> {
          softly.assertTrue(
              validateXMLSchema(
                  "src/main/resources/survnetXMLTemplates/xmlSchema.xsd",
                  "/srv/dockerdata/jenkins_new/sormas-files/case_"
                      + externalUUID.get(0).substring(1, 37)
                      + ".xml"),
              "Generated XML file does not match an example XSD schema");
          softly.assertAll();
        });

    And(
        "^I check if software info in SORMAS generated XML file is correct$",
        () -> {
          String softwareInfo = getSoftwareInfo(singleXmlFile).substring(15, 30);
          String expectedSoftwareInfo = sormasActualVersion;
          softly.assertEquals(softwareInfo, expectedSoftwareInfo, "Software info is incorrect!");
          softly.assertAll();
        });

    And(
        "^I collect SORMAS VERSION from About page$",
        () -> {
          webDriverHelpers.clickOnWebElementBySelector(NavBarPage.ABOUT_BUTTON);
          webDriverHelpers.waitUntilIdentifiedElementIsPresent(SORMAS_VERSION_LINK);
          sormasActualVersion =
              webDriverHelpers.getTextFromWebElement(SORMAS_VERSION_LINK).substring(9, 24);
        });

    And(
        "^I check if external person uuid in SORMAS generated XML file is correct$",
        () -> {
          String externalUUID = getGuidPatient(singleXmlFile, 0);
          String expectedExternalUUID = EditPersonSteps.externalPersonUUID.get(0).substring(1, 37);
          softly.assertEquals(
              externalUUID, expectedExternalUUID, "Person external UUID is incorrect!");
          softly.assertAll();
        });

    And(
        "^I open SORMAS generated XML file for single case message$",
        () -> {
          singleXmlFile =
              XMLParser.getDocument(
                  "/srv/dockerdata/jenkins_new/sormas-files/case_"
                      + externalUUID.get(0).substring(1, 37)
                      + ".xml");
        });

    And(
        "^I open SORMAS generated XML file for bulk case message$",
        () -> {
          bulkXmlFile =
              XMLParser.getDocument(
                  "/srv/dockerdata/jenkins_new/sormas-files/bulk_case_"
                      + externalUUID.get(0).substring(1, 37)
                      + ".xml");
        });

    And(
        "^I check if external person uuid for all (\\d+) cases in SORMAS generated bult XML file is correct$",
        (Integer caseNumber) -> {
          for (int i = 0; i < caseNumber; i++) {
            String externalUUID = getGuidPatient(bulkXmlFile, i);
            String expectedExternalUUID =
                EditPersonSteps.externalPersonUUID.get(i).substring(1, 37);
            softly.assertEquals(
                externalUUID, expectedExternalUUID, "Person external UUID is incorrect!");
            softly.assertAll();
          }
        });

    And(
        "^I check if \"([^\"]*)\" for all (\\d+) cases in SORMAS generated bulk XML file is correct$",
        (String typeOfDate, Integer caseNumber) -> {
          LocalDate expectedDate = CreateNewCaseSteps.survnetCase.getDateOfReport();

          switch (typeOfDate) {
            case "date of report":
              for (int i = 0; i < caseNumber; i++) {
                LocalDate dateOfReport = getReportingDate(bulkXmlFile, i);
                softly.assertEquals(dateOfReport, expectedDate, "Date of report is incorrect!");
                softly.assertAll();
              }
              break;
            case "change at date":
              for (int i = 0; i < caseNumber; i++) {
                LocalDate changeAtDate = getChangedAt(bulkXmlFile, i);
                softly.assertEquals(changeAtDate, expectedDate, "Change at date is incorrect!");
                softly.assertAll();
              }
              break;
            case "tracked at date":
              for (int i = 0; i < caseNumber; i++) {
                LocalDate trackedAt = getTrackedAt(bulkXmlFile, i);
                softly.assertEquals(trackedAt, expectedDate, "Tracked at date is incorrect!");
                softly.assertAll();
              }
              break;
          }
        });

    And(
        "^I check if age computed field in SORMAS generated XML file is correct$",
        () -> {
          int computedAge = getAgeComputed(singleXmlFile);
          int expectedComputedAge =
              Period.between(CreateNewCaseSteps.survnetCase.getDateOfBirth(), LocalDate.now())
                  .getYears();
          softly.assertEquals(computedAge, expectedComputedAge, "Computed age is incorrect!");
          softly.assertAll();
        });

    And(
        "^I open SORMAS generated XML file for event single message$",
        () -> {
          singleXmlFile =
              XMLParser.getDocument(
                  "/srv/dockerdata/jenkins_new/sormas-files/event_"
                      + EditEventSteps.externalEventUUID.get(0).substring(1, 37)
                      + ".xml");
        });

    And(
        "^I check if event external UUID in SORMAS generated XML file is correct$",
        () -> {
          softly.assertEquals(
              getGuidRecord(singleXmlFile, 0),
              EditEventSteps.externalEventUUID.get(0).substring(1, 37),
              "External event UUID is incorrect!");
          softly.assertAll();
        });

    And(
        "^I check if case external UUID in SORMAS generated XML file is correct$",
        () -> {
          softly.assertEquals(
              getGuidRecord(singleXmlFile, 0),
              externalUUID.get(0).substring(1, 37),
              "External case UUID is incorrect!");
          softly.assertAll();
        });
  }

  private LocalDate getReportingDate(Document xmlFile, int caseNumber) {
    String reportingDate =
        xmlFile
            .getRootElement()
            .getChildren()
            .get(caseNumber)
            .getAttribute("ReportingDate")
            .getValue()
            .substring(0, 10);
    return LocalDate.parse(reportingDate, DATE_FORMATTER);
  }

  private LocalDate getChangedAt(Document xmlFile, int caseNumber) {
    String reportingDate =
        xmlFile
            .getRootElement()
            .getChildren()
            .get(caseNumber)
            .getAttribute("ChangedAt")
            .getValue()
            .substring(0, 10);
    return LocalDate.parse(reportingDate, DATE_FORMATTER);
  }

  private LocalDate getCreatedAt(Document xmlFile) {
    String createdAt =
        xmlFile.getRootElement().getAttribute("CreatedAt").getValue().substring(0, 10);
    return LocalDate.parse(createdAt, DATE_FORMATTER);
  }

  private String getGuidRecord(Document xmlFile, int caseNumber) {
    return xmlFile
        .getRootElement()
        .getChildren()
        .get(caseNumber)
        .getAttribute("GuidRecord")
        .getValue()
        .substring(1, 37);
  }

  private String getGuidPatient(Document xmlFile, int caseNumber) {
    return xmlFile
        .getRootElement()
        .getChildren()
        .get(caseNumber)
        .getChildren()
        .get(0)
        .getAttribute("Value")
        .getValue()
        .substring(1, 37);
  }

  private LocalDate getTrackedAt(Document xmlFile, int caseNumber) {
    String trackedAt =
        xmlFile
            .getRootElement()
            .getChildren()
            .get(caseNumber)
            .getChildren()
            .get(78)
            .getAttribute("TrackedAt")
            .getValue()
            .substring(0, 10);
    return LocalDate.parse(trackedAt, DATE_FORMATTER);
  }

  private String getSoftwareInfo(Document xmlFile) {
    String softwareInfo =
        xmlFile
            .getRootElement()
            .getChildren()
            .get(0)
            .getChildren()
            .get(78)
            .getAttribute("Software")
            .getValue();
    return softwareInfo;
  }

  private String getSexDE(Document xmlFile, int caseNumber) {
    String sexDE = null;
    String sexIndex =
        xmlFile
            .getRootElement()
            .getChildren()
            .get(caseNumber)
            .getChildren()
            .get(2)
            .getAttribute("Value")
            .getValue();

    switch (sexIndex) {
      case ("1"):
        sexDE = "Männlich";
        break;
      case ("2"):
        sexDE = "Weiblich";
        break;
      case ("3"):
        sexDE = "Divers";
        break;
      case ("4"):
        sexDE = "Unbekannt";
        break;
    }
    return sexDE;
  }

  private int getAgeComputed(Document xmlFile) {
    int ageComputed = 0;
    try {
      ageComputed =
          xmlFile
              .getRootElement()
              .getChildren()
              .get(0)
              .getChildren()
              .get(81)
              .getAttribute("Value")
              .getIntValue();
    } catch (DataConversionException e) {
      e.printStackTrace();
    }
    return ageComputed;
  }

  private String getValueFromSpecificFieldByName(Document xmlFile, String name) {
    Element rootElement = xmlFile.getRootElement();
    Namespace ns = rootElement.getNamespace();
    String value = null;

    Element field =
        xmlFile.getRootElement().getChildren().get(0).getChildren("Field", ns).stream()
            .filter(e -> e.getAttributeValue("Name").equals(name))
            .findFirst()
            .orElse(null);

    if (field != null) {
      Attribute valueAttribute = field.getAttribute("Value");
      if (valueAttribute != null) {
        value = valueAttribute.getValue();
      }
    }
    return value;
  }

  private LocalDate getDateValueFromSpecificChildrenFieldByName(Document xmlFile, String name) {
    Element rootElement = xmlFile.getRootElement();
    Namespace ns = rootElement.getNamespace();
    String value = null;

    Element field =
        xmlFile.getRootElement().getChildren().get(0).getChildren("Field", ns).stream()
            .filter(e -> e.getAttributeValue("Name").equals(name))
            .findFirst()
            .orElse(null);

    if (field != null) {
      Attribute valueAttribute = field.getAttribute("Value");
      if (valueAttribute != null) {
        value = valueAttribute.getValue().substring(0, 10);
        ;
      }
    }
    return LocalDate.parse(value, DATE_FORMATTER);
  }
}
