package de.symeda.sormas.api.clinicalcourse;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.i18n.Validations;
import de.symeda.sormas.api.symptoms.DisabilityGrading;
import de.symeda.sormas.api.symptoms.LeprosyStage;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.api.utils.*;
import de.symeda.sormas.api.utils.pseudonymization.PseudonymizableDto;

import static de.symeda.sormas.api.Disease.LEPROSY;

@DependingOnFeatureType(featureType = FeatureType.CLINICAL_MANAGEMENT)
public class ClinicalVisitDto extends PseudonymizableDto {

	private static final long serialVersionUID = -8220449896773019721L;

	public static final long APPROXIMATE_JSON_SIZE_IN_BYTES = 7613;

	public static final String I18N_PREFIX = "ClinicalVisit";

	public static final String CLINICAL_COURSE = "clinicalCourse";
	public static final String SYMPTOMS = "symptoms";
	public static final String DISEASE = "disease";
	public static final String VISIT_DATE_TIME = "visitDateTime";
	public static final String VISIT_REMARKS = "visitRemarks";
	public static final String VISITING_PERSON = "visitingPerson";
	public static final String TYPE_OF_CLINICAL_MEASUREMENT = "typeOfClinicalMeasurement";
	public static final String EHF_SCORE = "ehfScore";
	public static final String DISABILITY_GRADING = "disabilityGrading";
	public static final String ULCER = "ulcer";
	public static final String IS_LEPROSY_REACTION = "leprosyReaction";
	public static final String LEPROSY_STAGE = "leprosyStage";
	public static final String DATE_OF_DIAGNOSIS = "dateOfDiagnosis";
	public static final String TREATMENT_GIVE = "treatmentGiven";

	private ClinicalCourseReferenceDto clinicalCourse;
	@Valid
	@Diseases(value = {Disease.LEPROSY}, hide = true)
	private SymptomsDto symptoms;
	private Disease disease;
	private Date visitDateTime;
	@SensitiveData
	@Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
	private String visitingPerson;
	@SensitiveData
	@Size(max = FieldConstraints.CHARACTER_LIMIT_DEFAULT, message = Validations.textTooLong)
	private String visitRemarks;

	@Diseases({Disease.LEPROSY})
	private TypeOfClinicalMeasurement typeOfClinicalMeasurement;
	@Min(0)
	@Max(12)
	@Diseases({Disease.LEPROSY})
	private Integer ehfScore;

	@Diseases({Disease.LEPROSY})
	private DisabilityGrading disabilityGrading;

	@Diseases({Disease.LEPROSY})
	private Boolean ulcer;
	@Diseases({LEPROSY})
	private Boolean leprosyReaction;
	@Diseases({LEPROSY})
	private LeprosyStage leprosyStage;
	@Diseases({LEPROSY})
	private Date dateOfDiagnosis;
	@Diseases({LEPROSY})
	private String treatmentGiven;

	public static ClinicalVisitDto build(ClinicalCourseReferenceDto clinicalCourse, Disease disease) {

		ClinicalVisitDto clinicalVisit = new ClinicalVisitDto();
		clinicalVisit.setUuid(DataHelper.createUuid());
		clinicalVisit.setClinicalCourse(clinicalCourse);
		clinicalVisit.setSymptoms(SymptomsDto.build());
		clinicalVisit.setDisease(disease);
		clinicalVisit.setVisitDateTime(new Date());
		return clinicalVisit;
	}

	public ClinicalCourseReferenceDto getClinicalCourse() {
		return clinicalCourse;
	}

	public void setClinicalCourse(ClinicalCourseReferenceDto clinicalCourse) {
		this.clinicalCourse = clinicalCourse;
	}

	public SymptomsDto getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(SymptomsDto symptoms) {
		this.symptoms = symptoms;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Date getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(Date visitDateTime) {
		this.visitDateTime = visitDateTime;
	}

	public String getVisitRemarks() {
		return visitRemarks;
	}

	public void setVisitRemarks(String visitRemarks) {
		this.visitRemarks = visitRemarks;
	}

	public String getVisitingPerson() {
		return visitingPerson;
	}

	public void setVisitingPerson(String visitingPerson) {
		this.visitingPerson = visitingPerson;
	}

	public TypeOfClinicalMeasurement getTypeOfClinicalMeasurement() {
		return typeOfClinicalMeasurement;
	}

	public void setTypeOfClinicalMeasurement(TypeOfClinicalMeasurement typeOfClinicalMeasurement) {
		this.typeOfClinicalMeasurement = typeOfClinicalMeasurement;
	}

	public Integer getEhfScore() {
		return ehfScore;
	}

	public void setEhfScore(Integer ehfScore) {
		this.ehfScore = ehfScore;
	}

	public DisabilityGrading getDisabilityGrading() {
		return disabilityGrading;
	}

	public void setDisabilityGrading(DisabilityGrading disabilityGrading) {
		this.disabilityGrading = disabilityGrading;
	}

	public Boolean getUlcer() {
		return ulcer;
	}

	public void setUlcer(Boolean ulcer) {
		this.ulcer = ulcer;
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

}
