package de.symeda.sormas.api.epidata;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.i18n.I18nProperties;

import java.util.List;

public enum CaseDetectionMethod {
    HOUSE_TO_HOUSE_VISIT,
    MOBILE_MALARIA_CLINIC,
    CONTACT_SURVEY,
    FEVER_SURVEY,
    POPULATION_BASE_SURVEY,
    REFER,
    CONTACT_EXAMINATION,
    SKIN_LAMP,
    VOLUNTARY,
    HOSPITAL_VISIT_FOR_OTHER_PURPOSE,
    SURVEY,
    LEPROSY_POST_EXPOSURE_PROPHYLAXIS_PROGRAM;

    private static final List<CaseDetectionMethod> activeLeprosyMethod = List.of(REFER, CONTACT_EXAMINATION);
    private static final List<CaseDetectionMethod> passiveLeprosyMethod = List.of(VOLUNTARY, HOSPITAL_VISIT_FOR_OTHER_PURPOSE, SURVEY, LEPROSY_POST_EXPOSURE_PROPHYLAXIS_PROGRAM);
    private static final List<CaseDetectionMethod> activeMethod = List.of(HOUSE_TO_HOUSE_VISIT, MOBILE_MALARIA_CLINIC, CONTACT_SURVEY, FEVER_SURVEY, POPULATION_BASE_SURVEY);


    public static List<CaseDetectionMethod> getCaseDetectionMethod(CaseDetectionMethodGroup group, Disease disease) {
        if (group == null) {
            return List.of();
        }
        if (disease == Disease.LEPROSY) {
            if (group == CaseDetectionMethodGroup.ACTIVE_CASE_DETECTION) {
                return activeLeprosyMethod;
            } else {
                return passiveLeprosyMethod;
            }
        } else if (group == CaseDetectionMethodGroup.ACTIVE_CASE_DETECTION){
            return activeMethod;
        }
        return List.of();
    }

    @Override
    public String toString() {
        return I18nProperties.getEnumCaption(this);
    }
}
