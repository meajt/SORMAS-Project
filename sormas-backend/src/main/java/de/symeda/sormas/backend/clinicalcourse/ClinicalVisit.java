package de.symeda.sormas.backend.clinicalcourse;

import static de.symeda.sormas.api.utils.FieldConstraints.CHARACTER_LIMIT_DEFAULT;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.clinicalcourse.TypeOfClinicalMeasurement;
import de.symeda.sormas.api.symptoms.DisabilityGrading;
import de.symeda.sormas.api.symptoms.LeprosyStage;
import de.symeda.sormas.backend.common.AbstractDomainObject;
import de.symeda.sormas.backend.symptoms.Symptoms;

@Entity
public class ClinicalVisit extends AbstractDomainObject {

	private static final long serialVersionUID = -8220449896773019721L;

	public static final String TABLE_NAME = "clinicalvisit";

	public static final String CLINICAL_COURSE = "clinicalCourse";
	public static final String SYMPTOMS = "symptoms";
	public static final String DISEASE = "disease";
	public static final String VISIT_DATE_TIME = "visitDateTime";
	public static final String VISIT_REMARKS = "visitRemarks";
	public static final String VISITING_PERSON = "visitingPerson";

	private ClinicalCourse clinicalCourse;
	private Symptoms symptoms;
	private Disease disease;
	private Date visitDateTime;
	private String visitRemarks;
	private String visitingPerson;
	private TypeOfClinicalMeasurement typeOfClinicalMeasurement;
	private Integer ehfScore;
	private DisabilityGrading disabilityGrading;
	private Boolean ulcer;
	private Boolean leprosyReaction;

	private LeprosyStage leprosyStage;

	private Date dateOfDiagnosis;

	private String treatmentGiven;


	@ManyToOne(cascade = {})
	@JoinColumn(nullable = false)
	public ClinicalCourse getClinicalCourse() {
		return clinicalCourse;
	}

	public void setClinicalCourse(ClinicalCourse clinicalCourse) {
		this.clinicalCourse = clinicalCourse;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Symptoms getSymptoms() {
		if (symptoms == null) {
			symptoms = new Symptoms();
		}
		return symptoms;
	}

	public void setSymptoms(Symptoms symptoms) {
		this.symptoms = symptoms;
	}

	@Enumerated(EnumType.STRING)
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(Date visitDateTime) {
		this.visitDateTime = visitDateTime;
	}

	@Column(length = CHARACTER_LIMIT_DEFAULT)
	public String getVisitRemarks() {
		return visitRemarks;
	}

	public void setVisitRemarks(String visitRemarks) {
		this.visitRemarks = visitRemarks;
	}

	@Column(length = CHARACTER_LIMIT_DEFAULT)
	public String getVisitingPerson() {
		return visitingPerson;
	}

	public void setVisitingPerson(String visitingPerson) {
		this.visitingPerson = visitingPerson;
	}

	@Enumerated(EnumType.STRING)
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

	@Enumerated(EnumType.STRING)
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

	@Enumerated(EnumType.STRING)
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
