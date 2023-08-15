/*******************************************************************************
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2018 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.symeda.sormas.api.symptoms;

import static de.symeda.sormas.api.CountryHelper.COUNTRY_CODE_GERMANY;
import static de.symeda.sormas.api.CountryHelper.COUNTRY_CODE_SWITZERLAND;
import static de.symeda.sormas.api.Disease.*;

import java.util.Date;

import javax.validation.constraints.Size;

import de.symeda.sormas.api.CountryHelper;
import de.symeda.sormas.api.ImportIgnore;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.i18n.Validations;
import de.symeda.sormas.api.utils.Complication;
import de.symeda.sormas.api.utils.DataHelper;
import de.symeda.sormas.api.utils.DependantOn;
import de.symeda.sormas.api.utils.DependingOnFeatureType;
import de.symeda.sormas.api.utils.Diseases;
import de.symeda.sormas.api.utils.FieldConstraints;
import de.symeda.sormas.api.utils.HideForCountries;
import de.symeda.sormas.api.utils.HideForCountriesExcept;
import de.symeda.sormas.api.utils.Order;
import de.symeda.sormas.api.utils.Outbreaks;
import de.symeda.sormas.api.utils.SensitiveData;
import de.symeda.sormas.api.utils.SymptomGroup;
import de.symeda.sormas.api.utils.SymptomGrouping;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.api.utils.pseudonymization.PseudonymizableDto;

@DependingOnFeatureType(featureType = {
        FeatureType.CASE_SURVEILANCE,
        FeatureType.CONTACT_TRACING})
public class SymptomsDto extends PseudonymizableDto {

    private static final long serialVersionUID = 4146526547904182448L;

    public static final String I18N_PREFIX = "Symptoms";

    //Symptoms
    public static final String ABDOMINAL_PAIN = "abdominalPain";
    public static final String EAR_PAIN = "earPain";
    public static final String ABNORMAL_LUNG_XRAY_FINDINGS = "abnormalLungXrayFindings";
    public static final String ACUTE_RESPIRATORY_DISTRESS_SYNDROME = "acuteRespiratoryDistressSyndrome";
    public static final String AEROPHOBIA = "aerophobia";
    public static final String AGITATION = "agitation";
    public static final String ANOREXIA_APPETITE_LOSS = "anorexiaAppetiteLoss";
    public static final String ANXIETY_STATES = "anxietyStates";
    public static final String ASCENDING_FLACCID_PARALYSIS = "ascendingFlaccidParalysis";
    public static final String BACKACHE = "backache";
    public static final String BEDRIDDEN = "bedridden";
    public static final String BILATERAL_CATARACTS = "bilateralCataracts";
    public static final String BLACKENING_DEATH_OF_TISSUE = "blackeningDeathOfTissue";
    public static final String BLEEDING_VAGINA = "bleedingVagina";
    public static final String BLOODY_BLACK_STOOL = "bloodyBlackStool";
    public static final String BLOOD_IN_STOOL = "bloodInStool";
    public static final String BLOOD_PRESSURE_DIASTOLIC = "bloodPressureDiastolic";
    public static final String BLOOD_PRESSURE_SYSTOLIC = "bloodPressureSystolic";
    public static final String BLOOD_URINE = "bloodUrine";
    public static final String BUBOES_GROIN_ARMPIT_NECK = "buboesGroinArmpitNeck";
    public static final String BULGING_FONTANELLE = "bulgingFontanelle";
    public static final String CHEST_PAIN = "chestPain";
    public static final String CHILLS_SWEATS = "chillsSweats";
    public static final String COMA = "coma";
    public static final String CONGENITAL_GLAUCOMA = "congenitalGlaucoma";
    public static final String CONGENITAL_HEART_DISEASE = "congenitalHeartDisease";
    public static final String CONGENITAL_HEART_DISEASE_DETAILS = "congenitalHeartDiseaseDetails";
    public static final String CONGENITAL_HEART_DISEASE_TYPE = "congenitalHeartDiseaseType";
    public static final String CONJUNCTIVAL_INJECTION = "conjunctivalInjection";
    public static final String CONJUNCTIVITIS = "conjunctivitis";
    public static final String CONVULSION = "convulsion";
    public static final String COUGH = "cough";
    public static final String COUGHING_BLOOD = "coughingBlood";
    public static final String COUGH_WITH_HEAMOPTYSIS = "coughWithHeamoptysis";
    public static final String COUGH_WITH_SPUTUM = "coughWithSputum";
    public static final String DARK_URINE = "darkUrine";
    public static final String DEHYDRATION = "dehydration";
    public static final String DELIRIUM = "delirium";
    public static final String DEVELOPMENTAL_DELAY = "developmentalDelay";
    public static final String DIARRHEA = "diarrhea";
    public static final String DIFFICULTY_BREATHING = "difficultyBreathing";
    public static final String DIGESTED_BLOOD_VOMIT = "digestedBloodVomit";
    public static final String DYSPHAGIA = "dysphagia";
    public static final String ERRATIC_BEHAVIOUR = "erraticBehaviour";
    public static final String EXCESS_SALIVATION = "excessSalivation";
    public static final String EXCITATION = "excitation";
    public static final String EYES_BLEEDING = "eyesBleeding";
    public static final String EYE_PAIN_LIGHT_SENSITIVE = "eyePainLightSensitive";
    public static final String FATIGUE_WEAKNESS = "fatigueWeakness";
    public static final String FEVER = "fever";
    public static final String FLUID_IN_LUNG_CAVITY = "fluidInLungCavity";
    public static final String FLUID_IN_LUNG_CAVITY_AUSCULTATION = "fluidInLungCavityAuscultation";
    public static final String FLUID_IN_LUNG_CAVITY_XRAY = "fluidInLungCavityXray";
    public static final String GLASGOW_COMA_SCALE = "glasgowComaScale";
    public static final String GUMS_BLEEDING = "gumsBleeding";
    public static final String HEADACHE = "headache";
    public static final String HEARINGLOSS = "hearingloss";
    public static final String HEART_RATE = "heartRate";
    public static final String HEIGHT = "height";
    public static final String HICCUPS = "hiccups";
    public static final String HYDROPHOBIA = "hydrophobia";
    public static final String HYPERACTIVITY = "hyperactivity";
    public static final String INABILITY_TO_WALK = "inabilityToWalk";
    public static final String INJECTION_SITE_BLEEDING = "injectionSiteBleeding";
    public static final String INSOMNIA = "insomnia";
    public static final String IN_DRAWING_OF_CHEST_WALL = "inDrawingOfChestWall";
    public static final String JAUNDICE = "jaundice";
    public static final String JAUNDICE_WITHIN_24_HOURS_OF_BIRTH = "jaundiceWithin24HoursOfBirth";
    public static final String JOINT_PAIN = "jointPain";
    public static final String KOPLIKS_SPOTS = "kopliksSpots";
    public static final String LESIONS = "lesions";
    public static final String LESIONS_ALL_OVER_BODY = "lesionsAllOverBody";
    public static final String LESIONS_ARMS = "lesionsArms";
    public static final String LESIONS_DEEP_PROFOUND = "lesionsDeepProfound";
    public static final String LESIONS_FACE = "lesionsFace";
    public static final String LESIONS_GENITALS = "lesionsGenitals";
    public static final String LESIONS_LEGS = "lesionsLegs";
    public static final String LESIONS_ONSET_DATE = "lesionsOnsetDate";
    public static final String LESIONS_PALMS_HANDS = "lesionsPalmsHands";
    public static final String LESIONS_RESEMBLE_IMG1 = "lesionsResembleImg1";
    public static final String LESIONS_RESEMBLE_IMG2 = "lesionsResembleImg2";
    public static final String LESIONS_RESEMBLE_IMG3 = "lesionsResembleImg3";
    public static final String LESIONS_RESEMBLE_IMG4 = "lesionsResembleImg4";
    public static final String LESIONS_SAME_SIZE = "lesionsSameSize";
    public static final String LESIONS_SAME_STATE = "lesionsSameState";
    public static final String LESIONS_SOLES_FEET = "lesionsSolesFeet";
    public static final String LESIONS_THAT_ITCH = "lesionsThatItch";
    public static final String LESIONS_THORAX = "lesionsThorax";
    public static final String LOSS_OF_SMELL = "lossOfSmell";
    public static final String LOSS_OF_TASTE = "lossOfTaste";
    public static final String LOSS_SKIN_TURGOR = "lossSkinTurgor";
    public static final String LYMPHADENOPATHY = "lymphadenopathy";
    public static final String LYMPHADENOPATHY_AXILLARY = "lymphadenopathyAxillary";
    public static final String LYMPHADENOPATHY_CERVICAL = "lymphadenopathyCervical";
    public static final String LYMPHADENOPATHY_INGUINAL = "lymphadenopathyInguinal";
    public static final String MALAISE = "malaise";
    public static final String MENINGOENCEPHALITIS = "meningoencephalitis";
    public static final String MICROCEPHALY = "microcephaly";
    public static final String MID_UPPER_ARM_CIRCUMFERENCE = "midUpperArmCircumference";
    public static final String MUSCLE_PAIN = "musclePain";
    public static final String NAUSEA = "nausea";
    public static final String NECK_STIFFNESS = "neckStiffness";
    public static final String NOSE_BLEEDING = "noseBleeding";
    public static final String OEDEMA_FACE_NECK = "oedemaFaceNeck";
    public static final String OEDEMA_LOWER_EXTREMITY = "oedemaLowerExtremity";
    public static final String ONSET_DATE = "onsetDate";
    public static final String ONSET_SYMPTOM = "onsetSymptom";
    public static final String OPISTHOTONUS = "opisthotonus";
    public static final String ORAL_ULCERS = "oralUlcers";
    public static final String OTHER_HEMORRHAGIC_SYMPTOMS = "otherHemorrhagicSymptoms";
    public static final String OTHER_HEMORRHAGIC_SYMPTOMS_TEXT = "otherHemorrhagicSymptomsText";
    public static final String OTHER_NON_HEMORRHAGIC_SYMPTOMS = "otherNonHemorrhagicSymptoms";
    public static final String OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT = "otherNonHemorrhagicSymptomsText";
    public static final String OTITIS_MEDIA = "otitisMedia";
    public static final String PAINFUL_LYMPHADENITIS = "painfulLymphadenitis";
    public static final String PALPABLE_LIVER = "palpableLiver";
    public static final String PALPABLE_SPLEEN = "palpableSpleen";
    public static final String PARALYSIS = "paralysis";
    public static final String PARASTHESIA_AROUND_WOUND = "paresthesiaAroundWound";
    public static final String PARESIS = "paresis";
    public static final String PATIENT_ILL_LOCATION = "patientIllLocation";
    public static final String PHARYNGEAL_ERYTHEMA = "pharyngealErythema";
    public static final String PHARYNGEAL_EXUDATE = "pharyngealExudate";
    public static final String PIGMENTARY_RETINOPATHY = "pigmentaryRetinopathy";
    public static final String PNEUMONIA_CLINICAL_OR_RADIOLOGIC = "pneumoniaClinicalOrRadiologic";
    public static final String PURPURIC_RASH = "purpuricRash";
    public static final String RADIOLUCENT_BONE_DISEASE = "radiolucentBoneDisease";
    public static final String RAPID_BREATHING = "rapidBreathing";
    public static final String RED_BLOOD_VOMIT = "redBloodVomit";
    public static final String REFUSAL_FEEDOR_DRINK = "refusalFeedorDrink";
    public static final String RESPIRATORY_RATE = "respiratoryRate";
    public static final String RUNNY_NOSE = "runnyNose";
    public static final String SIDE_PAIN = "sidePain";
    public static final String SKIN_BRUISING = "skinBruising";
    public static final String SKIN_RASH = "skinRash";
    public static final String SKIN_ULCERS = "skinUlcers";
    public static final String SORE_THROAT = "soreThroat";
    public static final String SPLENOMEGALY = "splenomegaly";
    public static final String STOMACH_BLEEDING = "stomachBleeding";
    public static final String SUNKEN_EYES_FONTANELLE = "sunkenEyesFontanelle";
    public static final String SWOLLEN_GLANDS = "swollenGlands";
    public static final String SYMPTOMATIC = "symptomatic";
    public static final String SYMPTOMS_COMMENTS = "symptomsComments";
    public static final String TEMPERATURE = "temperature";
    public static final String TEMPERATURE_SOURCE = "temperatureSource";
    public static final String THROBOCYTOPENIA = "throbocytopenia";
    public static final String TREMOR = "tremor";
    public static final String UNEXPLAINED_BLEEDING = "unexplainedBleeding";
    public static final String UNILATERAL_CATARACTS = "unilateralCataracts";
    public static final String UPROARIOUSNESS = "uproariousness";
    public static final String VOMITING = "vomiting";
    public static final String WHEEZING = "wheezing";
    public static final String RESPIRATORY_DISEASE_VENTILATION = "respiratoryDiseaseVentilation";
    public static final String FEELING_ILL = "feelingIll";
    public static final String SHIVERING = "shivering";
    public static final String FAST_HEART_RATE = "fastHeartRate";
    public static final String OXYGEN_SATURATION_LOWER_94 = "oxygenSaturationLower94";

    public static final String WEIGHT = "weight";

    public static final String FEVERISHFEELING = "feverishFeeling";
    public static final String WEAKNESS = "weakness";
    public static final String FATIGUE = "fatigue";
    public static final String COUGH_WITHOUT_SPUTUM = "coughWithoutSputum";
    public static final String BREATHLESSNESS = "breathlessness";
    public static final String CHEST_PRESSURE = "chestPressure";
    public static final String BLUE_LIPS = "blueLips";
    public static final String BLOOD_CIRCULATION_PROBLEMS = "bloodCirculationProblems";
    public static final String PALPITATIONS = "palpitations";
    public static final String DIZZINESS_STANDING_UP = "dizzinessStandingUp";
    public static final String HIGH_OR_LOW_BLOOD_PRESSURE = "highOrLowBloodPressure";
    public static final String URINARY_RETENTION = "urinaryRetention";

    // Complications
    public static final String ALTERED_CONSCIOUSNESS = "alteredConsciousness";
    public static final String CONFUSED_DISORIENTED = "confusedDisoriented";
    public static final String HEMORRHAGIC_SYNDROME = "hemorrhagicSyndrome";
    public static final String HYPERGLYCEMIA = "hyperglycemia";
    public static final String HYPOGLYCEMIA = "hypoglycemia";
    public static final String MENINGEAL_SIGNS = "meningealSigns";
    public static final String OTHER_COMPLICATIONS = "otherComplications";
    public static final String OTHER_COMPLICATIONS_TEXT = "otherComplicationsText";
    public static final String SEIZURES = "seizures";
    public static final String SEPSIS = "sepsis";
    public static final String SHOCK = "shock";

    public static final String BODY_ACHE = "bodyAche";
    public static final String NUMBESS = "numbness";
    public static final String MUSCLE_TWITCHING = "muscleTwitching";
    public static final String PUNCTURE_MARK_AT_WOUND = "punctureMarkAtWound";
    public static final String BLEEDING_AROUND_BITE = "bleedingAroundBite";
    public static final String DISTURBED_VISION = "disturbedVision";
    public static final String DISCOLORED_SKIN = "discoloredSkin";
    public static final String GROWTH_NODULES_ON_SKIN = "growthNodulesOnSkin";
    public static final String PAINLESS_ULCER = "painlessUlcer";
    public static final String EYE_LASHES = "eyelashes";
    public static final String ENLARGES_NERVES = "enlargesNerves";
    public static final String MUSCLE_WEAKNESS = "muscleWeakness";
    public static final String CASE_CONDITION = "caseCondition";
    public static final String TYPE_OF_LEPROSY = "typeOfLeprosy";
    public static final String IS_LEPROSY_REACTION = "leprosyReaction";
    public static final String LEPROSY_STAGE = "leprosyStage";
    public static final String DATE_OF_DIAGNOSIS = "dateOfDiagnosis";
    public static final String TREATMENT_GIVE = "treatmentGiven";
    public static final String EHF_SCORE = "ehfScore";
    public static final String TIME_OF_DIAGNOSIS = "timeOfDiagnosis";
    public static final String TIME_OF_RFT = "timeOfRFT";
    public static final String RED_EYE_WITHOUT_DISCHARGE = "redEyeWithoutDischarge";
    public static final String LEUKOCORIA = "leukocoria";
    public static final String UNILATERAL_INVOLVEMENT = "unilateralInvolvement";
    public static final String DECREASE_VISION = "decreaseVision";
    public static final String CIRCUMCILIARY_CONGESTION = "circumciliaryCongestion";
    public static final String FIBRINOID_ANT_CHAMBER_RXN = "fibrinoidAntChamberRxn";
    public static final String HYPOPYON = "hypopyon";
    public static final String SHALLO_ANT_CHAMBER = "shalloAntChamber";
    public static final String DECREASE_INTRAOCU_PRESSURE = "decreaseIntraocuPressure";
    public static final String PHOTOPHOBIA = "photophobia";
    public static final String SUDDENANTOFRXN = "suddenantofrxn";
    public static final String REDANT_WHITERXN = "redantWhiterxn";
    public static final String LOSSANT_CORNEAL_RXN = "lossantCornealRxn";
    public static final String REDUCE_VIALEQUI_EQUITY = "reduceVialequiEquity";
    public static final String REDUC_EYE_O_P = "reducEyeOP";
    public static final String PORRED_GLOW = "porredGlow";
    public static final String RETINAL_DETACHMENT = "retinalDetachment";
    public static final String RETINAL_NECROSIS = "retinalNecrosis";
    public static final String HYOPTONY = "hyoptony";
    public static final String CATARACT = "cataract";
    public static final String PTHTHISIS_BULBI = "pththisisBulbi";
    // Fields are declared in the order they should appear in the import template

    public static SymptomsDto build() {
        SymptomsDto symptoms = new SymptomsDto();
        symptoms.setUuid(DataHelper.createUuid());
        return symptoms;
    }

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CHOLERA, DIARRHOEA,
            YELLOW_FEVER,
            DENGUE,
            PLAGUE,
            POLIO,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            UNSPECIFIED_VHF,
            ACUTE_VIRAL_HEPATITIS,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            MALARIA,
            UNDEFINED,

            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState abdominalPain;

    @Diseases({
            MUMPS,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.EAR)
    private SymptomState earPain;
    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            CSM,
            MUMPS,
            CHOLERA, DIARRHOEA,
            POLIO,
            YELLOW_FEVER,
            UNSPECIFIED_VHF,
            MALARIA,
            KALAZAR, SCRUB_TYPHUS,
            ACUTE_VIRAL_HEPATITIS,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState anorexiaAppetiteLoss;

    @Diseases({
            AFP,
            GUINEA_WORM,
            POLIO,
            YELLOW_FEVER,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState backache;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState bedridden;

    @Diseases({
            AFP,
            GUINEA_WORM,
            PLAGUE,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState blackeningDeathOfTissue;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState bleedingVagina;

    @Diseases({
            AFP,
            CHOLERA, DIARRHOEA,
            GUINEA_WORM,
            POLIO,
            YELLOW_FEVER,
            ANTHRAX,
            MALARIA,
            DIARRHEA_BLOOD,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState bloodInStool;

    private Integer bloodPressureDiastolic;

    private Integer bloodPressureSystolic;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.URINARY)
    private SymptomState bloodUrine;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            DIARRHEA_BLOOD,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState bloodyBlackStool;

    @Diseases({
            AFP,
            GUINEA_WORM,
            PLAGUE,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState buboesGroinArmpitNeck;

    @Diseases({
            AFP,
            CSM,
            GUINEA_WORM,
            POLIO,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState bulgingFontanelle;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            PLAGUE,
            POLIO,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries
    private SymptomState chestPain;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            PLAGUE,
            ANTHRAX,
            POLIO,
            CORONAVIRUS, MUMPS,
            MALARIA,
            ACUTE_VIRAL_HEPATITIS,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            SNAKE_BITE,
            UNDEFINED,
            SCRUB_TYPHUS,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState chillsSweats;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            MONKEYPOX,
            POLIO,
            UNSPECIFIED_VHF,
            SCRUB_TYPHUS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState conjunctivitis;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            ANTHRAX,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            UNSPECIFIED_VHF,
            CORONAVIRUS, MUMPS,
            SAPHU,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState cough;

    @Diseases({
            CORONAVIRUS})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState coughWithSputum;

    @Diseases({
            CORONAVIRUS})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState coughWithHeamoptysis;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState coughingBlood;

    @Diseases({
            AFP,
            GUINEA_WORM,
            POLIO,
            YELLOW_FEVER,
            UNSPECIFIED_VHF,
            ACUTE_VIRAL_HEPATITIS,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.URINARY)
    private SymptomState darkUrine;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            CHOLERA, DIARRHOEA,
            POLIO,
            DIARRHEA_DEHYDRATION,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState dehydration;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            PLAGUE,
            POLIO,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            SNAKE_BITE,
            MALARIA,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState diarrhea;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            PLAGUE,
            POLIO,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            UNSPECIFIED_VHF,
            RABIES,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            SAPHU,
            UNDEFINED,
            OTHER})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState difficultyBreathing;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState digestedBloodVomit;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            DENGUE,
            MONKEYPOX,
            POLIO,
            UNSPECIFIED_VHF,
            SAPHU,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState eyePainLightSensitive;

    @Diseases({
            AFP,
            GUINEA_WORM,
            POLIO,
            YELLOW_FEVER,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState eyesBleeding;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            KALAZAR, SCRUB_TYPHUS,
            MALARIA,
            DIARRHEA_DEHYDRATION,
            ACUTE_VIRAL_HEPATITIS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState fatigueWeakness;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            MALARIA,
            KALAZAR, SCRUB_TYPHUS,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            ACUTE_VIRAL_HEPATITIS,
            SAPHU,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState fever;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState fluidInLungCavity;

    @SymptomGrouping(SymptomGroup.OTHER)
    private Integer glasgowComaScale;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            YELLOW_FEVER,
            DENGUE,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState gumsBleeding;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            ANTHRAX,
            MALARIA,
            CORONAVIRUS, MUMPS,
            SCRUB_TYPHUS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState headache;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState hearingloss;

    private Integer heartRate;

    private Integer height;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState hiccups;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState injectionSiteBleeding;

    @Diseases({
            AFP,
            GUINEA_WORM,
            YELLOW_FEVER,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            MALARIA,
            ACUTE_VIRAL_HEPATITIS,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState jaundice;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private YesNoUnknown jaundiceWithin24HoursOfBirth;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            NEW_INFLUENZA,
            CSM,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            DENGUE,
            CORONAVIRUS, MUMPS,
            UNSPECIFIED_VHF,
            ACUTE_VIRAL_HEPATITIS,
            SAPHU,
            UNDEFINED,
            SCRUB_TYPHUS,
            OTHER})
    @Outbreaks
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState jointPain;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState kopliksSpots;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            ANTHRAX,
            POLIO,
            UNDEFINED,
            SCRUB_TYPHUS,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    /** Vesiculopustular rash */
    private SymptomState lesions;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            SCRUB_TYPHUS,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsAllOverBody;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            ANTHRAX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsArms;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            ANTHRAX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsDeepProfound;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsFace;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsGenitals;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsLegs;

    @Diseases({
            MONKEYPOX,
            ANTHRAX})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Date lesionsOnsetDate;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsPalmsHands;

    @Diseases({
            MONKEYPOX})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsResembleImg1;
    @Diseases({
            MONKEYPOX})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsResembleImg2;

    @Diseases({
            MONKEYPOX})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsResembleImg3;

    @Diseases({
            MONKEYPOX})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsResembleImg4;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsSameSize;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsSameState;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsSolesFeet;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState lesionsThatItch;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @DependantOn(LESIONS)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private Boolean lesionsThorax;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lossSkinTurgor;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lymphadenopathy;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            SNAKE_BITE,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lymphadenopathyAxillary;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lymphadenopathyCervical;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            SNAKE_BITE,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lymphadenopathyInguinal;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            MALARIA,
            MUMPS,
            ACUTE_VIRAL_HEPATITIS,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState malaise;

    private Integer midUpperArmCircumference;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            CORONAVIRUS, MUMPS,
            UNSPECIFIED_VHF,
            SCRUB_TYPHUS,
            SAPHU,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState musclePain;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            UNSPECIFIED_VHF,
            POLIO,
            RABIES,
            ANTHRAX,
            MALARIA,
            SNAKE_BITE,
            ACUTE_VIRAL_HEPATITIS,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState nausea;

    @Diseases({
            AFP,
            CSM,
            GUINEA_WORM,
            POLIO,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState neckStiffness;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            YELLOW_FEVER,
            UNSPECIFIED_VHF,
            LEPROSY,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState noseBleeding;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState oedemaFaceNeck;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState oedemaLowerExtremity;

    @Outbreaks
    private Date onsetDate;

    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String onsetSymptom;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState oralUlcers;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            PLAGUE,
            POLIO,
            SNAKE_BITE,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState otherHemorrhagicSymptoms;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(OTHER_HEMORRHAGIC_SYMPTOMS)
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SensitiveData
    @SymptomGrouping(SymptomGroup.OTHER)
    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String otherHemorrhagicSymptomsText;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            POLIO,
            RABIES,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState otherNonHemorrhagicSymptoms;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @DependantOn(OTHER_NON_HEMORRHAGIC_SYMPTOMS)
    @SensitiveData
    @SymptomGrouping(SymptomGroup.OTHER)
    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String otherNonHemorrhagicSymptomsText;

    @Diseases({
            AFP,
            GUINEA_WORM,
            NEW_INFLUENZA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState otitisMedia;

    @Diseases({
            AFP,
            GUINEA_WORM,
            PLAGUE,
            POLIO,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState painfulLymphadenitis;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            KALAZAR, SCRUB_TYPHUS,
            MALARIA,
            ACUTE_VIRAL_HEPATITIS,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState palpableLiver;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            KALAZAR, SCRUB_TYPHUS,
            MALARIA,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState palpableSpleen;

    @Diseases({
            AFP,
            GUINEA_WORM,
            MONKEYPOX,
            POLIO,
            UNDEFINED,
            OTHER})
    @SensitiveData
    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String patientIllLocation;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState pharyngealErythema;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState pharyngealExudate;

    @Diseases({
            AFP,
            CORONAVIRUS, MUMPS,
            DENGUE,
            GUINEA_WORM,
            POLIO,
            UNSPECIFIED_VHF,
            SNAKE_BITE,
            MALARIA,
            UNDEFINED,
            OTHER})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState rapidBreathing;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            DENGUE,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState redBloodVomit;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            CSM,
            CHOLERA, DIARRHOEA,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState refusalFeedorDrink;

    private Integer respiratoryRate;

    @Diseases({
            AFP,
            GUINEA_WORM,
            NEW_INFLUENZA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState runnyNose;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState sidePain;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            SNAKE_BITE,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    private SymptomState skinBruising;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            DENGUE,
            POLIO,
            UNSPECIFIED_VHF,
            KALAZAR, SCRUB_TYPHUS,
            UNDEFINED,
            OTHER,
            CORONAVIRUS})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    /** Maculopapular rash */
    private SymptomState skinRash;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            MONKEYPOX,
            ANTHRAX,
            POLIO,
            CORONAVIRUS, MUMPS,
            UNSPECIFIED_VHF,
            SAPHU,
            UNDEFINED,
            OTHER})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState soreThroat;

    @Diseases({
            AFP,
            GUINEA_WORM,
            POLIO,
            YELLOW_FEVER,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @DependantOn(UNEXPLAINED_BLEEDING)
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState stomachBleeding;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            DIARRHEA_DEHYDRATION,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState sunkenEyesFontanelle;

    @Diseases({
            AFP,
            DENGUE,
            GUINEA_WORM,
            POLIO,
            UNSPECIFIED_VHF,
            KALAZAR, SCRUB_TYPHUS,
            MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState swollenGlands;

    private Boolean symptomatic;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @SensitiveData
    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String symptomsComments;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            POLIO,
            RABIES,
            CORONAVIRUS, MUMPS,
            SAPHU,
            UNDEFINED,
            OTHER})
    @Outbreaks
    private Float temperature;

    @Diseases({
            MALARIA
    })
    @Outbreaks
    private CaseCondition caseCondition;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            UNSPECIFIED_VHF,
            CONGENITAL_RUBELLA,
            POLIO,
            CORONAVIRUS, MUMPS,
            SAPHU,
            UNDEFINED,
            OTHER})
    @Outbreaks
    private TemperatureSource temperatureSource;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState throbocytopenia;

    @Diseases({
            AFP,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @HideForCountries
    private SymptomState tremor;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState bilateralCataracts;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState unilateralCataracts;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState congenitalGlaucoma;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState pigmentaryRetinopathy;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState purpuricRash;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    private SymptomState microcephaly;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState developmentalDelay;

    @Diseases({
            KALAZAR, SCRUB_TYPHUS,
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState splenomegaly;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState meningoencephalitis;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState radiolucentBoneDisease;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState congenitalHeartDisease;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private CongenitalHeartDiseaseType congenitalHeartDiseaseType;

    @Diseases({
            CONGENITAL_RUBELLA})
    @HideForCountries
    @SensitiveData
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String congenitalHeartDiseaseDetails;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            YELLOW_FEVER,
            DENGUE,
            PLAGUE,
            UNSPECIFIED_VHF,
            UNDEFINED,
            POLIO,
            OTHER,
            CORONAVIRUS})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState unexplainedBleeding;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            YELLOW_FEVER,
            DENGUE,
            MONKEYPOX,
            PLAGUE,
            ANTHRAX,
            UNSPECIFIED_VHF,
            UNDEFINED,
            POLIO,
            SNAKE_BITE,
            MALARIA,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            ACUTE_VIRAL_HEPATITIS,
            CORONAVIRUS, MUMPS,
            OTHER})
    @Outbreaks
    @HideForCountries
    @SymptomGrouping(SymptomGroup.GASTROINTESTINAL)
    private SymptomState vomiting;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState hydrophobia;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState opisthotonus;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState anxietyStates;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState delirium;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState uproariousness;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState paresthesiaAroundWound;

    @Diseases({
            SNAKE_BITE,
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState excessSalivation;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState insomnia;

    @Diseases({
            RABIES})
    @HideForCountries
    private SymptomState paralysis;

    @Diseases({
            RABIES})
    @HideForCountries
    private SymptomState excitation;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState dysphagia;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState aerophobia;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState hyperactivity;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState paresis;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState agitation;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState ascendingFlaccidParalysis;

    @Diseases({
            RABIES})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState erraticBehaviour;

    @Diseases({
            RABIES,
            CORONAVIRUS})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState coma;

    @Diseases({
            ANTHRAX})
    @HideForCountries
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState convulsion;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState fluidInLungCavityAuscultation;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState fluidInLungCavityXray;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState abnormalLungXrayFindings;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    private SymptomState conjunctivalInjection;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState acuteRespiratoryDistressSyndrome;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState pneumoniaClinicalOrRadiologic;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lossOfTaste;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState lossOfSmell;

    @Diseases({
            CORONAVIRUS, MUMPS,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    private SymptomState wheezing;

    @Diseases({
            CORONAVIRUS, MUMPS,
            KALAZAR, SCRUB_TYPHUS,
            LEPROSY,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState skinUlcers;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState inabilityToWalk;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountries(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState inDrawingOfChestWall;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState respiratoryDiseaseVentilation;

    @Diseases({
            MALARIA,
            CORONAVIRUS, MUMPS,
            KALAZAR, SCRUB_TYPHUS,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = {
            COUNTRY_CODE_GERMANY,
            COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState feelingIll;

    @Diseases({
            CORONAVIRUS, MUMPS,
            DIARRHEA_DEHYDRATION,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = {
            CountryHelper.COUNTRY_CODE_GERMANY,
            CountryHelper.COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState fastHeartRate;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState oxygenSaturationLower94;

    private Integer weight;

    // complications

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNSPECIFIED_VHF,
            MALARIA,
            RABIES,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState alteredConsciousness;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            PLAGUE,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState confusedDisoriented;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            POLIO,
            YELLOW_FEVER,
            DENGUE,
            PLAGUE,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState hemorrhagicSyndrome;

    @Diseases({
            AFP,
            CSM,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState hyperglycemia;

    @Diseases({
            AFP,
            CSM,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState hypoglycemia;

    @Diseases({
            AFP,
            CSM,
            GUINEA_WORM,
            LASSA,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState meningealSigns;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNSPECIFIED_VHF,
            GUINEA_WORM,
            RABIES,
            YELLOW_FEVER,
            DENGUE,
            PLAGUE,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @Complication
    @HideForCountries
    private SymptomState otherComplications;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNSPECIFIED_VHF,
            GUINEA_WORM,
            RABIES,
            YELLOW_FEVER,
            DENGUE,
            PLAGUE,
            ANTHRAX,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @DependantOn(OTHER_COMPLICATIONS)
    @Complication
    @HideForCountries
    @SensitiveData
    @Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
    private String otherComplicationsText;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNSPECIFIED_VHF,
            RABIES,
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState seizures;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            POLIO,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState sepsis;

    @Diseases({
            AFP,
            EVD,
            GUINEA_WORM,
            LASSA,
            NEW_INFLUENZA,
            CSM,
            CHOLERA, DIARRHOEA,
            MEASLES, CHICKENPOX, FEVER_WITH_RASH,
            PLAGUE,
            ANTHRAX,
            POLIO,
            SNAKE_BITE,
            UNSPECIFIED_VHF,
            UNDEFINED,
            OTHER})
    @Outbreaks
    @Complication
    @HideForCountries
    private SymptomState shock;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState feverishFeeling;
    @Diseases({
            CORONAVIRUS, MUMPS,
            MALARIA,
            KALAZAR, SCRUB_TYPHUS,
            ACUTE_VIRAL_HEPATITIS,
            DIARRHEA_DEHYDRATION,
            DIARRHEA_BLOOD,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState weakness;
    @Diseases({
            CORONAVIRUS, MUMPS,
            MALARIA,
            KALAZAR, SCRUB_TYPHUS,
            ACUTE_VIRAL_HEPATITIS,
            DIARRHEA_BLOOD,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState fatigue;
    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState coughWithoutSputum;
    @Diseases({
            CORONAVIRUS, MUMPS,
            SNAKE_BITE,
            RESPIRATORY_SYNCYTIAL_VIRUS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState breathlessness;
    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState chestPressure;
    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.RESPIRATORY)
    private SymptomState blueLips;
    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState bloodCirculationProblems;
    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState palpitations;
    @Diseases({
            CORONAVIRUS, MUMPS,
            DIARRHEA_DEHYDRATION,
            MALARIA,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState dizzinessStandingUp;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.CARDIOVASCULAR)
    private SymptomState highOrLowBloodPressure;

    @Diseases({
            CORONAVIRUS, MUMPS,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
    @SymptomGrouping(SymptomGroup.URINARY)
    private SymptomState urinaryRetention;

    @Diseases({
            CORONAVIRUS, MUMPS,
            MALARIA,
            UNDEFINED,
            OTHER})
    @HideForCountriesExcept(countries = {
            COUNTRY_CODE_GERMANY,
            COUNTRY_CODE_SWITZERLAND})
    @SymptomGrouping(SymptomGroup.GENERAL)
    private SymptomState shivering;

    @Diseases({
            MALARIA,
            ACUTE_VIRAL_HEPATITIS
    })
    @SymptomGrouping(SymptomGroup.MUSCULAR)
    private SymptomState bodyAche;

    @Diseases({
            SNAKE_BITE,
            LEPROSY
    })
    @SymptomGrouping(SymptomGroup.MUSCULAR)
    private SymptomState numbness;

    @Diseases({
            SNAKE_BITE
    })
    @SymptomGrouping(SymptomGroup.MUSCULAR)
    private SymptomState muscleTwitching;

    @Diseases({
            SNAKE_BITE
    })
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState punctureMarkAtWound;

    @Diseases({
            SNAKE_BITE
    })
    @SymptomGrouping(SymptomGroup.OTHER)
    private SymptomState bleedingAroundBite;

    @Diseases({
        SNAKE_BITE,
        SAPHU
    })
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState disturbedVision;

    @Diseases({
            MALARIA
    })
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState discoloredSkin;

    @Diseases({
            MALARIA
    })
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState growthNodulesOnSkin;

    @Diseases({
            MALARIA
    })
    @SymptomGrouping(SymptomGroup.SKIN)
    private SymptomState painlessUlcer;

    @Diseases({
            MALARIA
    })
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState eyelashes;

    @Diseases({
            MALARIA
    })
    @SymptomGrouping(SymptomGroup.NERVOUS_SYSTEM)
    private SymptomState enlargesNerves;

    @Diseases({
            MALARIA
    })
    @SymptomGrouping(SymptomGroup.MUSCULAR)
    private SymptomState muscleWeakness;
    
    @Diseases({LEPROSY})
    private TypeOfLeprosy typeOfLeprosy;
    @Diseases({LEPROSY})
    private Boolean leprosyReaction;
    @Diseases({LEPROSY})
    private LeprosyStage leprosyStage;
    @Diseases({LEPROSY})
    private Date dateOfDiagnosis;
    @Diseases({LEPROSY})
    private String treatmentGiven;
    @Diseases({LEPROSY})
    private Integer ehfScore;
    @Diseases({LEPROSY})
    private DisabilityGrading timeOfDiagnosis;
    @Diseases({LEPROSY})
    private DisabilityGrading timeOfRFT;

    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState redEyeWithoutDischarge;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState leukocoria;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState unilateralInvolvement;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState decreaseVision;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState circumciliaryCongestion;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState fibrinoidAntChamberRxn;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState hypopyon;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState shalloAntChamber;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState decreaseIntraocuPressure;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState photophobia;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState suddenantofrxn;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState redantWhiterxn;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState lossantCornealRxn;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState reduceVialequiEquity;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState reducEyeOP;
    @Diseases({SAPHU})
    @SymptomGrouping(SymptomGroup.EYE)
    private SymptomState porredGlow;
    @Diseases({SAPHU})
    @Complication
    private SymptomState retinalDetachment;
    @Diseases({SAPHU})
    @Complication
    private SymptomState retinalNecrosis;
    @Diseases({SAPHU})
    @Complication
    private SymptomState hyoptony;
    @Diseases({SAPHU})
    @Complication
    private SymptomState cataract;
    @Diseases({SAPHU})
    @Complication
    private SymptomState pththisisBulbi;

    @Order(0)
    public Float getTemperature() {
        return temperature;
    }

    @Order(1)
    public TemperatureSource getTemperatureSource() {
        return temperatureSource;
    }

    @Order(20)
    @ImportIgnore
    public Boolean getSymptomatic() {
        return symptomatic;
    }

    @Order(21)
    public String getSymptomsComments() {
        return symptomsComments;
    }

    @Order(22)
    public Date getOnsetDate() {
        return onsetDate;
    }

    @Order(23)
    public String getOnsetSymptom() {
        return onsetSymptom;
    }

    @ImportIgnore
    public String getPatientIllLocation() {
        return patientIllLocation;
    }

    @Order(100)
    public SymptomState getAbdominalPain() {
        return abdominalPain;
    }

    @Order(101)
    public SymptomState getAlteredConsciousness() {
        return alteredConsciousness;
    }

    @Order(102)
    public SymptomState getAnorexiaAppetiteLoss() {
        return anorexiaAppetiteLoss;
    }

    @Order(103)
    public SymptomState getBackache() {
        return backache;
    }

    @Order(104)
    public SymptomState getBedridden() {
        return bedridden;
    }

    @Order(105)
    public SymptomState getBlackeningDeathOfTissue() {
        return blackeningDeathOfTissue;
    }

    @Order(110)
    public SymptomState getBleedingVagina() {
        return bleedingVagina;
    }

    @Order(111)
    public SymptomState getBloodInStool() {
        return bloodInStool;
    }

    public Integer getBloodPressureDiastolic() {
        return bloodPressureDiastolic;
    }

    public Integer getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }

    @Order(112)
    public SymptomState getBloodUrine() {
        return bloodUrine;
    }

    @Order(113)
    public SymptomState getBloodyBlackStool() {
        return bloodyBlackStool;
    }

    @Order(114)
    public SymptomState getBuboesGroinArmpitNeck() {
        return buboesGroinArmpitNeck;
    }

    @Order(115)
    public SymptomState getBulgingFontanelle() {
        return bulgingFontanelle;
    }

    @Order(116)
    public SymptomState getBilateralCataracts() {
        return bilateralCataracts;
    }

    @Order(117)
    public SymptomState getUnilateralCataracts() {
        return unilateralCataracts;
    }

    @Order(120)
    public SymptomState getChestPain() {
        return chestPain;
    }

    @Order(121)
    public SymptomState getChillsSweats() {
        return chillsSweats;
    }

    @Order(122)
    public SymptomState getConfusedDisoriented() {
        return confusedDisoriented;
    }

    @Order(123)
    public SymptomState getCongenitalGlaucoma() {
        return congenitalGlaucoma;
    }

    @Order(124)
    public SymptomState getCongenitalHeartDisease() {
        return congenitalHeartDisease;
    }

    @Order(125)
    public CongenitalHeartDiseaseType getCongenitalHeartDiseaseType() {
        return congenitalHeartDiseaseType;
    }

    @Order(126)
    public String getCongenitalHeartDiseaseDetails() {
        return congenitalHeartDiseaseDetails;
    }

    @Order(127)
    public SymptomState getConjunctivitis() {
        return conjunctivitis;
    }

    @Order(128)
    public SymptomState getCough() {
        return cough;
    }

    @Order(129)
    public SymptomState getCoughingBlood() {
        return coughingBlood;
    }

    @Order(130)
    public SymptomState getDarkUrine() {
        return darkUrine;
    }

    @Order(131)
    public SymptomState getDehydration() {
        return dehydration;
    }

    @Order(132)
    public SymptomState getDevelopmentalDelay() {
        return developmentalDelay;
    }

    @Order(133)
    public SymptomState getDiarrhea() {
        return diarrhea;
    }

    @Order(134)
    public SymptomState getDifficultyBreathing() {
        return difficultyBreathing;
    }

    @Order(135)
    public SymptomState getDigestedBloodVomit() {
        return digestedBloodVomit;
    }

    @Order(136)
    public SymptomState getEyePainLightSensitive() {
        return eyePainLightSensitive;
    }

    @Order(140)
    public SymptomState getEyesBleeding() {
        return eyesBleeding;
    }

    @Order(141)
    public SymptomState getFatigueWeakness() {
        return fatigueWeakness;
    }

    @Order(142)
    public SymptomState getFever() {
        return fever;
    }

    @Order(143)
    public SymptomState getFluidInLungCavity() {
        return fluidInLungCavity;
    }

    @Order(144)
    public SymptomState getFluidInLungCavityAuscultation() {
        return fluidInLungCavityAuscultation;
    }

    @Order(145)
    public SymptomState getFluidInLungCavityXray() {
        return fluidInLungCavityXray;
    }

    public Integer getGlasgowComaScale() {
        return glasgowComaScale;
    }

    @Order(146)
    public SymptomState getGumsBleeding() {
        return gumsBleeding;
    }

    @Order(147)
    public SymptomState getHeadache() {
        return headache;
    }

    @Order(150)
    public SymptomState getHearingloss() {
        return hearingloss;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Integer getHeight() {
        return height;
    }

    @Order(151)
    public SymptomState getHemorrhagicSyndrome() {
        return hemorrhagicSyndrome;
    }

    @Order(152)
    public SymptomState getHiccups() {
        return hiccups;
    }

    @Order(153)
    public SymptomState getHyperglycemia() {
        return hyperglycemia;
    }

    @Order(154)
    public SymptomState getHypoglycemia() {
        return hypoglycemia;
    }

    @Order(155)
    public SymptomState getInjectionSiteBleeding() {
        return injectionSiteBleeding;
    }

    @Order(160)
    public SymptomState getJaundice() {
        return jaundice;
    }

    @Order(161)
    public YesNoUnknown getJaundiceWithin24HoursOfBirth() {
        return jaundiceWithin24HoursOfBirth;
    }

    @Order(162)
    public SymptomState getJointPain() {
        return jointPain;
    }

    @Order(163)
    public SymptomState getKopliksSpots() {
        return kopliksSpots;
    }

    @Order(164)
    public SymptomState getLesions() {
        return lesions;
    }

    @Order(165)
    public Boolean getLesionsAllOverBody() {
        return lesionsAllOverBody;
    }

    @Order(166)
    public Boolean getLesionsArms() {
        return lesionsArms;
    }

    @Order(170)
    public SymptomState getLesionsDeepProfound() {
        return lesionsDeepProfound;
    }

    @Order(171)
    public Boolean getLesionsFace() {
        return lesionsFace;
    }

    @Order(172)
    public Boolean getLesionsGenitals() {
        return lesionsGenitals;
    }

    @Order(173)
    public Boolean getLesionsLegs() {
        return lesionsLegs;
    }

    @Order(174)
    public Date getLesionsOnsetDate() {
        return lesionsOnsetDate;
    }

    @Order(175)
    public Boolean getLesionsPalmsHands() {
        return lesionsPalmsHands;
    }

    public SymptomState getLesionsResembleImg1() {
        return lesionsResembleImg1;
    }

    public SymptomState getLesionsResembleImg2() {
        return lesionsResembleImg2;
    }

    public SymptomState getLesionsResembleImg3() {
        return lesionsResembleImg3;
    }

    public SymptomState getLesionsResembleImg4() {
        return lesionsResembleImg4;
    }

    @Order(180)
    public SymptomState getLesionsSameSize() {
        return lesionsSameSize;
    }

    @Order(181)
    public SymptomState getLesionsSameState() {
        return lesionsSameState;
    }

    @Order(182)
    public Boolean getLesionsSolesFeet() {
        return lesionsSolesFeet;
    }

    @Order(183)
    public SymptomState getLesionsThatItch() {
        return lesionsThatItch;
    }

    @Order(184)
    public Boolean getLesionsThorax() {
        return lesionsThorax;
    }

    @Order(185)
    public SymptomState getLossSkinTurgor() {
        return lossSkinTurgor;
    }

    @Order(190)
    public SymptomState getLymphadenopathyAxillary() {
        return lymphadenopathyAxillary;
    }

    @Order(191)
    public SymptomState getLymphadenopathyCervical() {
        return lymphadenopathyCervical;
    }

    @Order(192)
    public SymptomState getLymphadenopathyInguinal() {
        return lymphadenopathyInguinal;
    }

    @Order(193)
    public SymptomState getMalaise() {
        return malaise;
    }

    @Order(194)
    public SymptomState getMeningealSigns() {
        return meningealSigns;
    }

    @Order(195)
    public SymptomState getMeningoencephalitis() {
        return meningoencephalitis;
    }

    @Order(196)
    public SymptomState getMicrocephaly() {
        return microcephaly;
    }

    @Order(197)
    public Integer getMidUpperArmCircumference() {
        return midUpperArmCircumference;
    }

    @Order(200)
    public SymptomState getMusclePain() {
        return musclePain;
    }

    @Order(201)
    public SymptomState getNausea() {
        return nausea;
    }

    @Order(202)
    public SymptomState getNeckStiffness() {
        return neckStiffness;
    }

    @Order(203)
    public SymptomState getNoseBleeding() {
        return noseBleeding;
    }

    @Order(204)
    public SymptomState getOedemaFaceNeck() {
        return oedemaFaceNeck;
    }

    @Order(205)
    public SymptomState getOedemaLowerExtremity() {
        return oedemaLowerExtremity;
    }

    @Order(210)
    public SymptomState getOralUlcers() {
        return oralUlcers;
    }

    @Order(211)
    public SymptomState getOtherHemorrhagicSymptoms() {
        return otherHemorrhagicSymptoms;
    }

    @Order(212)
    public String getOtherHemorrhagicSymptomsText() {
        return otherHemorrhagicSymptomsText;
    }

    @Order(213)
    public SymptomState getOtherNonHemorrhagicSymptoms() {
        return otherNonHemorrhagicSymptoms;
    }

    @Order(214)
    public String getOtherNonHemorrhagicSymptomsText() {
        return otherNonHemorrhagicSymptomsText;
    }

    @Order(215)
    public SymptomState getOtitisMedia() {
        return otitisMedia;
    }

    @Order(220)
    public SymptomState getPainfulLymphadenitis() {
        return painfulLymphadenitis;
    }

    @Order(221)
    public SymptomState getPalpableLiver() {
        return palpableLiver;
    }

    @Order(222)
    public SymptomState getPalpableSpleen() {
        return palpableSpleen;
    }

    @Order(223)
    public SymptomState getPharyngealErythema() {
        return pharyngealErythema;
    }

    @Order(224)
    public SymptomState getPharyngealExudate() {
        return pharyngealExudate;
    }

    @Order(225)
    public SymptomState getPigmentaryRetinopathy() {
        return pigmentaryRetinopathy;
    }

    @Order(226)
    public SymptomState getPurpuricRash() {
        return purpuricRash;
    }

    @Order(227)
    public SymptomState getRadiolucentBoneDisease() {
        return radiolucentBoneDisease;
    }

    @Order(228)
    public SymptomState getRapidBreathing() {
        return rapidBreathing;
    }

    @Order(230)
    public SymptomState getRedBloodVomit() {
        return redBloodVomit;
    }

    @Order(231)
    public SymptomState getRefusalFeedorDrink() {
        return refusalFeedorDrink;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    @Order(232)
    public SymptomState getRunnyNose() {
        return runnyNose;
    }

    @Order(233)
    public SymptomState getSeizures() {
        return seizures;
    }

    @Order(234)
    public SymptomState getSepsis() {
        return sepsis;
    }

    @Order(235)
    public SymptomState getShock() {
        return shock;
    }

    @Order(240)
    public SymptomState getSidePain() {
        return sidePain;
    }

    @Order(241)
    public SymptomState getSkinBruising() {
        return skinBruising;
    }

    @Order(242)
    public SymptomState getSkinRash() {
        return skinRash;
    }

    @Order(243)
    public SymptomState getSoreThroat() {
        return soreThroat;
    }

    @Order(244)
    public SymptomState getSplenomegaly() {
        return splenomegaly;
    }

    @Order(245)
    public SymptomState getStomachBleeding() {
        return stomachBleeding;
    }

    @Order(246)
    public SymptomState getSunkenEyesFontanelle() {
        return sunkenEyesFontanelle;
    }

    @Order(250)
    public SymptomState getSwollenGlands() {
        return swollenGlands;
    }

    @Order(251)
    public SymptomState getThrobocytopenia() {
        return throbocytopenia;
    }

    @Order(252)
    public SymptomState getTremor() {
        return tremor;
    }

    @Order(253)
    public SymptomState getUnexplainedBleeding() {
        return unexplainedBleeding;
    }

    @Order(254)
    public SymptomState getVomiting() {
        return vomiting;
    }

    @Order(260)
    public SymptomState getAbnormalLungXrayFindings() {
        return abnormalLungXrayFindings;
    }

    @Order(261)
    public SymptomState getConjunctivalInjection() {
        return conjunctivalInjection;
    }

    @Order(262)
    public SymptomState getAcuteRespiratoryDistressSyndrome() {
        return acuteRespiratoryDistressSyndrome;
    }

    @Order(263)
    public SymptomState getPneumoniaClinicalOrRadiologic() {
        return pneumoniaClinicalOrRadiologic;
    }

    @Order(264)
    public SymptomState getLossOfTaste() {
        return lossOfTaste;
    }

    @Order(265)
    public SymptomState getLossOfSmell() {
        return lossOfSmell;
    }

    @Order(266)
    public SymptomState getCoughWithSputum() {
        return coughWithSputum;
    }

    @Order(267)
    public SymptomState getCoughWithHeamoptysis() {
        return coughWithHeamoptysis;
    }

    @Order(268)
    public SymptomState getLymphadenopathy() {
        return lymphadenopathy;
    }

    @Order(269)
    public SymptomState getWheezing() {
        return wheezing;
    }

    @Order(270)
    public SymptomState getSkinUlcers() {
        return skinUlcers;
    }

    @Order(271)
    public SymptomState getInabilityToWalk() {
        return inabilityToWalk;
    }

    @Order(272)
    public SymptomState getInDrawingOfChestWall() {
        return inDrawingOfChestWall;
    }

    @Order(273)
    public SymptomState getOtherComplications() {
        return otherComplications;
    }

    @Order(274)
    public String getOtherComplicationsText() {
        return otherComplicationsText;
    }

    @Order(300)
    public SymptomState getRespiratoryDiseaseVentilation() {
        return respiratoryDiseaseVentilation;
    }

    @Order(301)
    public SymptomState getFeelingIll() {
        return feelingIll;
    }

    @Order(302)
    public SymptomState getShivering() {
        return shivering;
    }

    @Order(304)
    public SymptomState getFastHeartRate() {
        return fastHeartRate;
    }

    @Order(305)
    public SymptomState getOxygenSaturationLower94() {
        return oxygenSaturationLower94;
    }

    @Order(310)
    public SymptomState getFeverishFeeling() {
        return feverishFeeling;
    }

    @Order(311)
    public SymptomState getWeakness() {
        return weakness;
    }

    @Order(312)
    public SymptomState getFatigue() {
        return fatigue;
    }

    @Order(313)
    public SymptomState getCoughWithoutSputum() {
        return coughWithoutSputum;
    }

    @Order(314)
    public SymptomState getBreathlessness() {
        return breathlessness;
    }

    @Order(315)
    public SymptomState getChestPressure() {
        return chestPressure;
    }

    @Order(316)
    public SymptomState getBlueLips() {
        return blueLips;
    }

    @Order(317)
    public SymptomState getBloodCirculationProblems() {
        return bloodCirculationProblems;
    }

    @Order(318)
    public SymptomState getDizzinessStandingUp() {
        return dizzinessStandingUp;
    }

    @Order(319)
    public SymptomState getHighOrLowBloodPressure() {
        return highOrLowBloodPressure;
    }

    @Order(320)
    public SymptomState getUrinaryRetention() {
        return urinaryRetention;
    }

    @Order(330)
    public Integer getWeight() {
        return weight;
    }

    public void setAbdominalPain(SymptomState abdominalPain) {
        this.abdominalPain = abdominalPain;
    }

    public void setAlteredConsciousness(SymptomState alteredConsciousness) {
        this.alteredConsciousness = alteredConsciousness;
    }

    public void setAnorexiaAppetiteLoss(SymptomState anorexiaAppetiteLoss) {
        this.anorexiaAppetiteLoss = anorexiaAppetiteLoss;
    }

    public void setBackache(SymptomState backache) {
        this.backache = backache;
    }

    public void setBedridden(SymptomState bedridden) {
        this.bedridden = bedridden;
    }

    public void setBlackeningDeathOfTissue(SymptomState blackeningDeathOfTissue) {
        this.blackeningDeathOfTissue = blackeningDeathOfTissue;
    }

    public void setBleedingVagina(SymptomState bleedingVagina) {
        this.bleedingVagina = bleedingVagina;
    }

    public void setBloodInStool(SymptomState bloodInStool) {
        this.bloodInStool = bloodInStool;
    }

    public void setBloodPressureDiastolic(Integer bloodPressureDiastolic) {
        this.bloodPressureDiastolic = bloodPressureDiastolic;
    }

    public void setBloodPressureSystolic(Integer bloodPressureSystolic) {
        this.bloodPressureSystolic = bloodPressureSystolic;
    }

    public void setBloodUrine(SymptomState bloodUrine) {
        this.bloodUrine = bloodUrine;
    }

    public void setBloodyBlackStool(SymptomState bloodyBlackStool) {
        this.bloodyBlackStool = bloodyBlackStool;
    }

    public void setBuboesGroinArmpitNeck(SymptomState buboesGroinArmpitNeck) {
        this.buboesGroinArmpitNeck = buboesGroinArmpitNeck;
    }

    public void setBulgingFontanelle(SymptomState bulgingFontanelle) {
        this.bulgingFontanelle = bulgingFontanelle;
    }

    public void setChestPain(SymptomState chestPain) {
        this.chestPain = chestPain;
    }

    public void setChillsSweats(SymptomState chillsSweats) {
        this.chillsSweats = chillsSweats;
    }

    public void setConfusedDisoriented(SymptomState confusedDisoriented) {
        this.confusedDisoriented = confusedDisoriented;
    }

    public void setConjunctivitis(SymptomState conjunctivitis) {
        this.conjunctivitis = conjunctivitis;
    }

    public void setCough(SymptomState cough) {
        this.cough = cough;
    }

    public void setCoughingBlood(SymptomState coughingBlood) {
        this.coughingBlood = coughingBlood;
    }

    public void setDarkUrine(SymptomState darkUrine) {
        this.darkUrine = darkUrine;
    }

    public void setDehydration(SymptomState dehydration) {
        this.dehydration = dehydration;
    }

    public void setDiarrhea(SymptomState diarrhea) {
        this.diarrhea = diarrhea;
    }

    public void setDifficultyBreathing(SymptomState difficultyBreathing) {
        this.difficultyBreathing = difficultyBreathing;
    }

    public void setDigestedBloodVomit(SymptomState digestedBloodVomit) {
        this.digestedBloodVomit = digestedBloodVomit;
    }

    public void setEyePainLightSensitive(SymptomState eyePainLightSensitive) {
        this.eyePainLightSensitive = eyePainLightSensitive;
    }

    public void setEyesBleeding(SymptomState eyesBleeding) {
        this.eyesBleeding = eyesBleeding;
    }

    public void setFatigueWeakness(SymptomState fatigueWeakness) {
        this.fatigueWeakness = fatigueWeakness;
    }

    public void setFever(SymptomState fever) {
        this.fever = fever;
    }

    public void setFluidInLungCavity(SymptomState fluidInLungCavity) {
        this.fluidInLungCavity = fluidInLungCavity;
    }

    public void setGlasgowComaScale(Integer glasgowComaScale) {
        this.glasgowComaScale = glasgowComaScale;
    }

    public void setGumsBleeding(SymptomState gumsBleeding) {
        this.gumsBleeding = gumsBleeding;
    }

    public void setHeadache(SymptomState headache) {
        this.headache = headache;
    }

    public void setHearingloss(SymptomState hearingloss) {
        this.hearingloss = hearingloss;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setHemorrhagicSyndrome(SymptomState hemorrhagicSyndrome) {
        this.hemorrhagicSyndrome = hemorrhagicSyndrome;
    }

    public void setHiccups(SymptomState hiccups) {
        this.hiccups = hiccups;
    }

    public void setHyperglycemia(SymptomState hyperglycemia) {
        this.hyperglycemia = hyperglycemia;
    }

    public void setHypoglycemia(SymptomState hypoglycemia) {
        this.hypoglycemia = hypoglycemia;
    }

    public void setInjectionSiteBleeding(SymptomState injectionSiteBleeding) {
        this.injectionSiteBleeding = injectionSiteBleeding;
    }

    public void setJaundice(SymptomState jaundice) {
        this.jaundice = jaundice;
    }

    public void setJointPain(SymptomState jointPain) {
        this.jointPain = jointPain;
    }

    public void setKopliksSpots(SymptomState kopliksSpots) {
        this.kopliksSpots = kopliksSpots;
    }

    public void setLesions(SymptomState lesions) {
        this.lesions = lesions;
    }

    public void setLesionsAllOverBody(Boolean lesionsAllOverBody) {
        this.lesionsAllOverBody = lesionsAllOverBody;
    }

    public void setLesionsArms(Boolean lesionsArms) {
        this.lesionsArms = lesionsArms;
    }

    public void setLesionsDeepProfound(SymptomState lesionsDeepProfound) {
        this.lesionsDeepProfound = lesionsDeepProfound;
    }

    public void setLesionsFace(Boolean lesionsFace) {
        this.lesionsFace = lesionsFace;
    }

    public void setLesionsGenitals(Boolean lesionsGenitals) {
        this.lesionsGenitals = lesionsGenitals;
    }

    public void setLesionsLegs(Boolean lesionsLegs) {
        this.lesionsLegs = lesionsLegs;
    }

    public void setLesionsOnsetDate(Date lesionsOnsetDate) {
        this.lesionsOnsetDate = lesionsOnsetDate;
    }

    public void setLesionsPalmsHands(Boolean lesionsPalmsHands) {
        this.lesionsPalmsHands = lesionsPalmsHands;
    }

    public void setLesionsResembleImg1(SymptomState lesionsResembleImg1) {
        this.lesionsResembleImg1 = lesionsResembleImg1;
    }

    public void setLesionsResembleImg2(SymptomState lesionsResembleImg2) {
        this.lesionsResembleImg2 = lesionsResembleImg2;
    }

    public void setLesionsResembleImg3(SymptomState lesionsResembleImg3) {
        this.lesionsResembleImg3 = lesionsResembleImg3;
    }

    public void setLesionsResembleImg4(SymptomState lesionsResembleImg4) {
        this.lesionsResembleImg4 = lesionsResembleImg4;
    }

    public void setLesionsSameSize(SymptomState lesionsSameSize) {
        this.lesionsSameSize = lesionsSameSize;
    }

    public void setLesionsSameState(SymptomState lesionsSameState) {
        this.lesionsSameState = lesionsSameState;
    }

    public void setLesionsSolesFeet(Boolean lesionsSolesFeet) {
        this.lesionsSolesFeet = lesionsSolesFeet;
    }

    public void setLesionsThatItch(SymptomState lesionsThatItch) {
        this.lesionsThatItch = lesionsThatItch;
    }

    public void setLesionsThorax(Boolean lesionsThorax) {
        this.lesionsThorax = lesionsThorax;
    }

    public void setLossSkinTurgor(SymptomState lossSkinTurgor) {
        this.lossSkinTurgor = lossSkinTurgor;
    }

    public void setLymphadenopathyAxillary(SymptomState lymphadenopathyAxillary) {
        this.lymphadenopathyAxillary = lymphadenopathyAxillary;
    }

    public void setLymphadenopathyCervical(SymptomState lymphadenopathyCervical) {
        this.lymphadenopathyCervical = lymphadenopathyCervical;
    }

    public void setLymphadenopathyInguinal(SymptomState lymphadenopathyInguinal) {
        this.lymphadenopathyInguinal = lymphadenopathyInguinal;
    }

    public void setMalaise(SymptomState malaise) {
        this.malaise = malaise;
    }

    public void setMeningealSigns(SymptomState meningealSigns) {
        this.meningealSigns = meningealSigns;
    }

    public void setMidUpperArmCircumference(Integer midUpperArmCircumference) {
        this.midUpperArmCircumference = midUpperArmCircumference;
    }

    public void setMusclePain(SymptomState musclePain) {
        this.musclePain = musclePain;
    }

    public void setNausea(SymptomState nausea) {
        this.nausea = nausea;
    }

    public void setNeckStiffness(SymptomState neckStiffness) {
        this.neckStiffness = neckStiffness;
    }

    public void setNoseBleeding(SymptomState noseBleeding) {
        this.noseBleeding = noseBleeding;
    }

    public void setOedemaFaceNeck(SymptomState oedemaFaceNeck) {
        this.oedemaFaceNeck = oedemaFaceNeck;
    }

    public void setOedemaLowerExtremity(SymptomState oedemaLowerExtremity) {
        this.oedemaLowerExtremity = oedemaLowerExtremity;
    }

    public void setOnsetDate(Date onsetDate) {
        this.onsetDate = onsetDate;
    }

    public void setOnsetSymptom(String onsetSymptom) {
        this.onsetSymptom = onsetSymptom;
    }

    public void setOralUlcers(SymptomState oralUlcers) {
        this.oralUlcers = oralUlcers;
    }

    public void setOtherHemorrhagicSymptoms(SymptomState otherHemorrhagicSymptoms) {
        this.otherHemorrhagicSymptoms = otherHemorrhagicSymptoms;
    }

    public void setOtherHemorrhagicSymptomsText(String otherHemorrhagicSymptomsText) {
        this.otherHemorrhagicSymptomsText = otherHemorrhagicSymptomsText;
    }

    public void setOtherNonHemorrhagicSymptoms(SymptomState otherNonHemorrhagicSymptoms) {
        this.otherNonHemorrhagicSymptoms = otherNonHemorrhagicSymptoms;
    }

    public void setOtherNonHemorrhagicSymptomsText(String otherNonHemorrhagicSymptomsText) {
        this.otherNonHemorrhagicSymptomsText = otherNonHemorrhagicSymptomsText;
    }

    public void setOtitisMedia(SymptomState otitisMedia) {
        this.otitisMedia = otitisMedia;
    }

    public void setPainfulLymphadenitis(SymptomState painfulLymphadenitis) {
        this.painfulLymphadenitis = painfulLymphadenitis;
    }

    public void setPalpableLiver(SymptomState palpableLiver) {
        this.palpableLiver = palpableLiver;
    }

    public void setPalpableSpleen(SymptomState palpableSpleen) {
        this.palpableSpleen = palpableSpleen;
    }

    public void setPatientIllLocation(String patientIllLocation) {
        this.patientIllLocation = patientIllLocation;
    }

    public void setPharyngealErythema(SymptomState pharyngealErythema) {
        this.pharyngealErythema = pharyngealErythema;
    }

    public void setPharyngealExudate(SymptomState pharyngealExudate) {
        this.pharyngealExudate = pharyngealExudate;
    }

    public void setRapidBreathing(SymptomState rapidBreathing) {
        this.rapidBreathing = rapidBreathing;
    }

    public void setRedBloodVomit(SymptomState redBloodVomit) {
        this.redBloodVomit = redBloodVomit;
    }

    public void setRefusalFeedorDrink(SymptomState refusalFeedorDrink) {
        this.refusalFeedorDrink = refusalFeedorDrink;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public void setRunnyNose(SymptomState runnyNose) {
        this.runnyNose = runnyNose;
    }

    public void setSeizures(SymptomState seizures) {
        this.seizures = seizures;
    }

    public void setSepsis(SymptomState sepsis) {
        this.sepsis = sepsis;
    }

    public void setShock(SymptomState shock) {
        this.shock = shock;
    }

    public void setSidePain(SymptomState sidePain) {
        this.sidePain = sidePain;
    }

    public void setSkinBruising(SymptomState skinBruising) {
        this.skinBruising = skinBruising;
    }

    public void setSkinRash(SymptomState skinRash) {
        this.skinRash = skinRash;
    }

    public void setSoreThroat(SymptomState soreThroat) {
        this.soreThroat = soreThroat;
    }

    public void setStomachBleeding(SymptomState stomachBleeding) {
        this.stomachBleeding = stomachBleeding;
    }

    public void setSunkenEyesFontanelle(SymptomState sunkenEyesFontanelle) {
        this.sunkenEyesFontanelle = sunkenEyesFontanelle;
    }

    public void setSwollenGlands(SymptomState swollenGlands) {
        this.swollenGlands = swollenGlands;
    }

    public void setSymptomatic(Boolean symptomatic) {
        this.symptomatic = symptomatic;
    }

    public void setSymptomsComments(String symptomsComments) {
        this.symptomsComments = symptomsComments;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public void setTemperatureSource(TemperatureSource temperatureSource) {
        this.temperatureSource = temperatureSource;
    }

    public void setThrobocytopenia(SymptomState throbocytopenia) {
        this.throbocytopenia = throbocytopenia;
    }

    public void setTremor(SymptomState tremor) {
        this.tremor = tremor;
    }

    public void setUnexplainedBleeding(SymptomState unexplainedBleeding) {
        this.unexplainedBleeding = unexplainedBleeding;
    }

    public void setVomiting(SymptomState vomiting) {
        this.vomiting = vomiting;
    }

    @Order(331)
    public SymptomState getConvulsion() {
        return convulsion;
    }

    public void setConvulsion(SymptomState convulsion) {
        this.convulsion = convulsion;
    }

    public void setJaundiceWithin24HoursOfBirth(YesNoUnknown jaundiceWithin24HoursOfBirth) {
        this.jaundiceWithin24HoursOfBirth = jaundiceWithin24HoursOfBirth;
    }

    public void setBilateralCataracts(SymptomState bilateralCataracts) {
        this.bilateralCataracts = bilateralCataracts;
    }

    public void setUnilateralCataracts(SymptomState unilateralCataracts) {
        this.unilateralCataracts = unilateralCataracts;
    }

    public void setCongenitalGlaucoma(SymptomState congenitalGlaucoma) {
        this.congenitalGlaucoma = congenitalGlaucoma;
    }

    public void setPigmentaryRetinopathy(SymptomState pigmentaryRetinopathy) {
        this.pigmentaryRetinopathy = pigmentaryRetinopathy;
    }

    public void setPurpuricRash(SymptomState purpuricRash) {
        this.purpuricRash = purpuricRash;
    }

    public void setMicrocephaly(SymptomState microcephaly) {
        this.microcephaly = microcephaly;
    }

    public void setDevelopmentalDelay(SymptomState developmentalDelay) {
        this.developmentalDelay = developmentalDelay;
    }

    public void setSplenomegaly(SymptomState splenomegaly) {
        this.splenomegaly = splenomegaly;
    }

    public void setMeningoencephalitis(SymptomState meningoencephalitis) {
        this.meningoencephalitis = meningoencephalitis;
    }

    public void setRadiolucentBoneDisease(SymptomState radiolucentBoneDisease) {
        this.radiolucentBoneDisease = radiolucentBoneDisease;
    }

    public void setCongenitalHeartDisease(SymptomState congenitalHeartDisease) {
        this.congenitalHeartDisease = congenitalHeartDisease;
    }

    public void setCongenitalHeartDiseaseType(CongenitalHeartDiseaseType congenitalHeartDiseaseType) {
        this.congenitalHeartDiseaseType = congenitalHeartDiseaseType;
    }

    public void setCongenitalHeartDiseaseDetails(String congenitalHeartDiseaseDetails) {
        this.congenitalHeartDiseaseDetails = congenitalHeartDiseaseDetails;
    }

    @Order(332)
    public SymptomState getHydrophobia() {
        return hydrophobia;
    }

    public void setHydrophobia(SymptomState hydrophobia) {
        this.hydrophobia = hydrophobia;
    }

    @Order(333)
    public SymptomState getOpisthotonus() {
        return opisthotonus;
    }

    public void setOpisthotonus(SymptomState opisthotonus) {
        this.opisthotonus = opisthotonus;
    }

    @Order(334)
    public SymptomState getAnxietyStates() {
        return anxietyStates;
    }

    public void setAnxietyStates(SymptomState anxietyStates) {
        this.anxietyStates = anxietyStates;
    }

    @Order(335)
    public SymptomState getDelirium() {
        return delirium;
    }

    public void setDelirium(SymptomState delirium) {
        this.delirium = delirium;
    }

    @Order(336)
    public SymptomState getUproariousness() {
        return uproariousness;
    }

    public void setUproariousness(SymptomState uproariousness) {
        this.uproariousness = uproariousness;
    }

    @Order(337)
    public SymptomState getParesthesiaAroundWound() {
        return paresthesiaAroundWound;
    }

    public void setParesthesiaAroundWound(SymptomState paresthesiaAroundWound) {
        this.paresthesiaAroundWound = paresthesiaAroundWound;
    }

    @Order(338)
    public SymptomState getExcessSalivation() {
        return excessSalivation;
    }

    public void setExcessSalivation(SymptomState excessSalivation) {
        this.excessSalivation = excessSalivation;
    }

    @Order(339)
    public SymptomState getInsomnia() {
        return insomnia;
    }

    public void setInsomnia(SymptomState insomnia) {
        this.insomnia = insomnia;
    }

    @Order(340)
    public SymptomState getParalysis() {
        return paralysis;
    }

    public void setParalysis(SymptomState paralysis) {
        this.paralysis = paralysis;
    }

    @Order(341)
    public SymptomState getExcitation() {
        return excitation;
    }

    public void setExcitation(SymptomState excitation) {
        this.excitation = excitation;
    }

    @Order(342)
    public SymptomState getDysphagia() {
        return dysphagia;
    }

    public void setDysphagia(SymptomState dysphagia) {
        this.dysphagia = dysphagia;
    }

    @Order(343)
    public SymptomState getAerophobia() {
        return aerophobia;
    }

    public void setAerophobia(SymptomState aerophobia) {
        this.aerophobia = aerophobia;
    }

    @Order(344)
    public SymptomState getHyperactivity() {
        return hyperactivity;
    }

    public void setHyperactivity(SymptomState hyperactivity) {
        this.hyperactivity = hyperactivity;
    }

    @Order(345)
    public SymptomState getParesis() {
        return paresis;
    }

    public void setParesis(SymptomState paresis) {
        this.paresis = paresis;
    }

    @Order(346)
    public SymptomState getAgitation() {
        return agitation;
    }

    public void setAgitation(SymptomState agitation) {
        this.agitation = agitation;
    }

    @Order(347)
    public SymptomState getAscendingFlaccidParalysis() {
        return ascendingFlaccidParalysis;
    }

    public void setAscendingFlaccidParalysis(SymptomState ascendingFlaccidParalysis) {
        this.ascendingFlaccidParalysis = ascendingFlaccidParalysis;
    }

    @Order(348)
    public SymptomState getErraticBehaviour() {
        return erraticBehaviour;
    }

    public void setErraticBehaviour(SymptomState erraticBehaviour) {
        this.erraticBehaviour = erraticBehaviour;
    }

    @Order(349)
    public SymptomState getComa() {
        return coma;
    }

    public void setComa(SymptomState coma) {
        this.coma = coma;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setFluidInLungCavityAuscultation(SymptomState fluidInLungCavityAuscultation) {
        this.fluidInLungCavityAuscultation = fluidInLungCavityAuscultation;
    }

    public void setFluidInLungCavityXray(SymptomState fluidInLungCavityXray) {
        this.fluidInLungCavityXray = fluidInLungCavityXray;
    }

    public void setAbnormalLungXrayFindings(SymptomState abnormalLungXrayFindings) {
        this.abnormalLungXrayFindings = abnormalLungXrayFindings;
    }

    public void setConjunctivalInjection(SymptomState conjunctivalInjection) {
        this.conjunctivalInjection = conjunctivalInjection;
    }

    public void setAcuteRespiratoryDistressSyndrome(SymptomState acuteRespiratoryDistressSyndrome) {
        this.acuteRespiratoryDistressSyndrome = acuteRespiratoryDistressSyndrome;
    }

    public void setPneumoniaClinicalOrRadiologic(SymptomState pneumoniaClinicalOrRadiologic) {
        this.pneumoniaClinicalOrRadiologic = pneumoniaClinicalOrRadiologic;
    }

    public void setLossOfTaste(SymptomState lossOfTaste) {
        this.lossOfTaste = lossOfTaste;
    }

    public void setLossOfSmell(SymptomState lossOfSmell) {
        this.lossOfSmell = lossOfSmell;
    }

    public void setCoughWithSputum(SymptomState coughWithSputum) {
        this.coughWithSputum = coughWithSputum;
    }

    public void setCoughWithHeamoptysis(SymptomState coughWithHeamoptysis) {
        this.coughWithHeamoptysis = coughWithHeamoptysis;
    }

    public void setLymphadenopathy(SymptomState lymphadenopathy) {
        this.lymphadenopathy = lymphadenopathy;
    }

    public void setWheezing(SymptomState wheezing) {
        this.wheezing = wheezing;
    }

    public void setSkinUlcers(SymptomState skinUlcers) {
        this.skinUlcers = skinUlcers;
    }

    public void setInabilityToWalk(SymptomState inabilityToWalk) {
        this.inabilityToWalk = inabilityToWalk;
    }

    public void setInDrawingOfChestWall(SymptomState inDrawingOfChestWall) {
        this.inDrawingOfChestWall = inDrawingOfChestWall;
    }

    public void setOtherComplications(SymptomState otherComplications) {
        this.otherComplications = otherComplications;
    }

    public void setOtherComplicationsText(String otherComplicationsText) {
        this.otherComplicationsText = otherComplicationsText;
    }

    public void setRespiratoryDiseaseVentilation(SymptomState respiratoryDiseaseVentilation) {
        this.respiratoryDiseaseVentilation = respiratoryDiseaseVentilation;
    }

    public void setFeelingIll(SymptomState feelingIll) {
        this.feelingIll = feelingIll;
    }

    public void setShivering(SymptomState shivering) {
        this.shivering = shivering;
    }

    public void setFastHeartRate(SymptomState fastHeartRate) {
        this.fastHeartRate = fastHeartRate;
    }

    public void setOxygenSaturationLower94(SymptomState oxygenSaturationLower94) {
        this.oxygenSaturationLower94 = oxygenSaturationLower94;
    }

    public void setFeverishFeeling(SymptomState feverishFeeling) {
        this.feverishFeeling = feverishFeeling;
    }

    public void setWeakness(SymptomState weakness) {
        this.weakness = weakness;
    }

    public void setFatigue(SymptomState fatigue) {
        this.fatigue = fatigue;
    }

    public void setCoughWithoutSputum(SymptomState coughWithoutSputum) {
        this.coughWithoutSputum = coughWithoutSputum;
    }

    public void setBreathlessness(SymptomState breathlessness) {
        this.breathlessness = breathlessness;
    }

    public void setChestPressure(SymptomState chestPressure) {
        this.chestPressure = chestPressure;
    }

    public void setBlueLips(SymptomState blueLips) {
        this.blueLips = blueLips;
    }

    @Order(350)
    public SymptomState getPalpitations() {
        return palpitations;
    }

    public void setPalpitations(SymptomState palpitations) {
        this.palpitations = palpitations;
    }

    public void setDizzinessStandingUp(SymptomState dizzinessStandingUp) {
        this.dizzinessStandingUp = dizzinessStandingUp;
    }

    public void setHighOrLowBloodPressure(SymptomState highOrLowBloodPressure) {
        this.highOrLowBloodPressure = highOrLowBloodPressure;
    }

    public void setUrinaryRetention(SymptomState urinaryRetention) {
        this.urinaryRetention = urinaryRetention;
    }

    public void setBloodCirculationProblems(SymptomState bloodCirculationProblems) {
        this.bloodCirculationProblems = bloodCirculationProblems;
    }

    public SymptomState getBodyAche() {
        return bodyAche;
    }

    public void setBodyAche(SymptomState bodyAche) {
        this.bodyAche = bodyAche;
    }

    public SymptomState getNumbness() {
        return numbness;
    }

    public void setNumbness(SymptomState numbness) {
        this.numbness = numbness;
    }

    public SymptomState getMuscleTwitching() {
        return muscleTwitching;
    }

    public void setMuscleTwitching(SymptomState muscleTwitching) {
        this.muscleTwitching = muscleTwitching;
    }

    public SymptomState getPunctureMarkAtWound() {
        return punctureMarkAtWound;
    }

    public void setPunctureMarkAtWound(SymptomState punctureMarkAtWound) {
        this.punctureMarkAtWound = punctureMarkAtWound;
    }

    public SymptomState getBleedingAroundBite() {
        return bleedingAroundBite;
    }

    public void setBleedingAroundBite(SymptomState bleedingAroundBite) {
        this.bleedingAroundBite = bleedingAroundBite;
    }

    public SymptomState getDisturbedVision() {
        return disturbedVision;
    }

    public void setDisturbedVision(SymptomState disturbedVision) {
        this.disturbedVision = disturbedVision;
    }

    public SymptomState getDiscoloredSkin() {
        return discoloredSkin;
    }

    public void setDiscoloredSkin(SymptomState discoloredSkin) {
        this.discoloredSkin = discoloredSkin;
    }

    public SymptomState getGrowthNodulesOnSkin() {
        return growthNodulesOnSkin;
    }

    public void setGrowthNodulesOnSkin(SymptomState growthNodulesOnSkin) {
        this.growthNodulesOnSkin = growthNodulesOnSkin;
    }

    public SymptomState getPainlessUlcer() {
        return painlessUlcer;
    }

    public void setPainlessUlcer(SymptomState painlessUlcer) {
        this.painlessUlcer = painlessUlcer;
    }

    public SymptomState getEyelashes() {
        return eyelashes;
    }

    public void setEyelashes(SymptomState eyelashes) {
        this.eyelashes = eyelashes;
    }

    public SymptomState getEnlargesNerves() {
        return enlargesNerves;
    }

    public void setEnlargesNerves(SymptomState enlargesNerves) {
        this.enlargesNerves = enlargesNerves;
    }

    public SymptomState getMuscleWeakness() {
        return muscleWeakness;
    }

    public void setMuscleWeakness(SymptomState muscleWeakness) {
        this.muscleWeakness = muscleWeakness;
    }

    public CaseCondition getCaseCondition() {
        return caseCondition;
    }

    public void setCaseCondition(CaseCondition caseCondition) {
        this.caseCondition = caseCondition;
    }

    public SymptomState getEarPain() {
        return earPain;
    }

    public void setEarPain(SymptomState earPain) {
        this.earPain = earPain;
    }

    public TypeOfLeprosy getTypeOfLeprosy() {
        return typeOfLeprosy;
    }

    public void setTypeOfLeprosy(TypeOfLeprosy typeOfLeprosy) {
        this.typeOfLeprosy = typeOfLeprosy;
    }

    public Boolean getLeprosyReaction() {
        return leprosyReaction;
    }

    public void setLeprosyReaction(Boolean leprosyReaction) {
        this.leprosyReaction = leprosyReaction;
    }

    public LeprosyStage getLeprosyStage() {
        return leprosyStage;
    }

    public void setLeprosyStage(LeprosyStage leprosyStage) {
        this.leprosyStage = leprosyStage;
    }

    public Date getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    public void setDateOfDiagnosis(Date dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public String getTreatmentGiven() {
        return treatmentGiven;
    }

    public void setTreatmentGiven(String treatmentGiven) {
        this.treatmentGiven = treatmentGiven;
    }

    public Integer getEhfScore() {
        return ehfScore;
    }

    public void setEhfScore(Integer ehfScore) {
        this.ehfScore = ehfScore;
    }

    public DisabilityGrading getTimeOfDiagnosis() {
        return timeOfDiagnosis;
    }

    public void setTimeOfDiagnosis(DisabilityGrading timeOfDiagnosis) {
        this.timeOfDiagnosis = timeOfDiagnosis;
    }

    public DisabilityGrading getTimeOfRFT() {
        return timeOfRFT;
    }

    public void setTimeOfRFT(DisabilityGrading timeOfRFT) {
        this.timeOfRFT = timeOfRFT;
    }

    public SymptomState getRedEyeWithoutDischarge() {
        return redEyeWithoutDischarge;
    }

    public void setRedEyeWithoutDischarge(SymptomState redEyeWithoutDischarge) {
        this.redEyeWithoutDischarge = redEyeWithoutDischarge;
    }

    public SymptomState getLeukocoria() {
        return leukocoria;
    }

    public void setLeukocoria(SymptomState leukocoria) {
        this.leukocoria = leukocoria;
    }

    public SymptomState getUnilateralInvolvement() {
        return unilateralInvolvement;
    }

    public void setUnilateralInvolvement(SymptomState unilateralInvolvement) {
        this.unilateralInvolvement = unilateralInvolvement;
    }

    public SymptomState getDecreaseVision() {
        return decreaseVision;
    }

    public void setDecreaseVision(SymptomState decreaseVision) {
        this.decreaseVision = decreaseVision;
    }

    public SymptomState getCircumciliaryCongestion() {
        return circumciliaryCongestion;
    }

    public void setCircumciliaryCongestion(SymptomState circumciliaryCongestion) {
        this.circumciliaryCongestion = circumciliaryCongestion;
    }

    public SymptomState getFibrinoidAntChamberRxn() {
        return fibrinoidAntChamberRxn;
    }

    public void setFibrinoidAntChamberRxn(SymptomState fibrinoidAntChamberRxn) {
        this.fibrinoidAntChamberRxn = fibrinoidAntChamberRxn;
    }

    public SymptomState getHypopyon() {
        return hypopyon;
    }

    public void setHypopyon(SymptomState hypopyon) {
        this.hypopyon = hypopyon;
    }

    public SymptomState getShalloAntChamber() {
        return shalloAntChamber;
    }

    public void setShalloAntChamber(SymptomState shalloAntChamber) {
        this.shalloAntChamber = shalloAntChamber;
    }

    public SymptomState getDecreaseIntraocuPressure() {
        return decreaseIntraocuPressure;
    }

    public void setDecreaseIntraocuPressure(SymptomState decreaseIntraocuPressure) {
        this.decreaseIntraocuPressure = decreaseIntraocuPressure;
    }

    public SymptomState getPhotophobia() {
        return photophobia;
    }

    public void setPhotophobia(SymptomState photophobia) {
        this.photophobia = photophobia;
    }

    public SymptomState getSuddenantofrxn() {
        return suddenantofrxn;
    }

    public void setSuddenantofrxn(SymptomState suddenantofrxn) {
        this.suddenantofrxn = suddenantofrxn;
    }

    public SymptomState getRedantWhiterxn() {
        return redantWhiterxn;
    }

    public void setRedantWhiterxn(SymptomState redantWhiterxn) {
        this.redantWhiterxn = redantWhiterxn;
    }

    public SymptomState getLossantCornealRxn() {
        return lossantCornealRxn;
    }

    public void setLossantCornealRxn(SymptomState lossantCornealRxn) {
        this.lossantCornealRxn = lossantCornealRxn;
    }

    public SymptomState getReduceVialequiEquity() {
        return reduceVialequiEquity;
    }

    public void setReduceVialequiEquity(SymptomState reduceVialequiEquity) {
        this.reduceVialequiEquity = reduceVialequiEquity;
    }

    public SymptomState getReducEyeOP() {
        return reducEyeOP;
    }

    public void setReducEyeOP(SymptomState reducEyeOP) {
        this.reducEyeOP = reducEyeOP;
    }

    public SymptomState getPorredGlow() {
        return porredGlow;
    }

    public void setPorredGlow(SymptomState porredGlow) {
        this.porredGlow = porredGlow;
    }

    public SymptomState getRetinalDetachment() {
        return retinalDetachment;
    }

    public void setRetinalDetachment(SymptomState retinalDetachment) {
        this.retinalDetachment = retinalDetachment;
    }

    public SymptomState getRetinalNecrosis() {
        return retinalNecrosis;
    }

    public void setRetinalNecrosis(SymptomState retinalNecrosis) {
        this.retinalNecrosis = retinalNecrosis;
    }

    public SymptomState getHyoptony() {
        return hyoptony;
    }

    public void setHyoptony(SymptomState hyoptony) {
        this.hyoptony = hyoptony;
    }

    public SymptomState getCataract() {
        return cataract;
    }

    public void setCataract(SymptomState cataract) {
        this.cataract = cataract;
    }

    public SymptomState getPththisisBulbi() {
        return pththisisBulbi;
    }

    public void setPththisisBulbi(SymptomState pththisisBulbi) {
        this.pththisisBulbi = pththisisBulbi;
    }
}
