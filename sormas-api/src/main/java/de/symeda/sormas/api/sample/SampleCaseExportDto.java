package de.symeda.sormas.api.sample;

import java.io.Serializable;
import java.util.Date;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.caze.CaseClassification;
import de.symeda.sormas.api.person.Sex;
import de.symeda.sormas.api.utils.Order;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.api.uuid.AbstractUuidDto;

public class SampleCaseExportDto extends AbstractUuidDto {

	public static final String I18N_PREFIX = "SampleCaseExport";

	private long id;
	private String labSampleID;
	private SpecimenCondition specimenCondition;
	private Date sampleDateTime;
	private String reportingUserName;

	private String firstName;
	private String lastName;
	private Integer age;
	private Sex sex;
	private String contactNo;

	private CaseClassification caseType;
	private Disease caseDisease;
	private String responsibleRegionName;
	private String responsibleDistrictName;
	private String responsibleCommunityName;
	private Integer wardNo;
	private String caseReportedHospital;
	private YesNoUnknown wasPatientAdmittedASInpatient;
	private Date hospitalDateOfAdmission;
	private YesNoUnknown admittedIndICU;
	private Date dateOfOnsetSymptoms;

	private SampleExportPathogenTest pathogenTest1 = new SampleExportPathogenTest();
	private SampleExportPathogenTest pathogenTest2 = new SampleExportPathogenTest();
	private SampleExportPathogenTest pathogenTest3 = new SampleExportPathogenTest();

	public SampleCaseExportDto(
			String uuid,
			long id,
			String labSampleID,
			SpecimenCondition specimenCondition,
			Date sampleDateTime,
			String reportingUserName,
			String firstName,
			String lastName,
			Sex sex,
			String contactNo,
			CaseClassification caseType,
			Disease caseDisease,
			String responsibleRegionName,
			String responsibleDistrictName,
			String responsibleCommunityName,
			Integer wardNo,
			String caseReportedHospital,
			YesNoUnknown wasPatientAdmittedASInpatient,
			Date hospitalDateOfAdmission,
			YesNoUnknown admittedIndICU,
			Date dateOfOnsetSymptoms) {
		super(uuid);
		this.id = id;
		this.labSampleID = labSampleID;
		this.specimenCondition = specimenCondition;
		this.sampleDateTime = sampleDateTime;
		this.reportingUserName = reportingUserName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.contactNo = contactNo;
		this.caseType = caseType;
		this.caseDisease = caseDisease;
		this.responsibleRegionName = responsibleRegionName;
		this.responsibleDistrictName = responsibleDistrictName;
		this.responsibleCommunityName = responsibleCommunityName;
		this.wardNo = wardNo;
		this.caseReportedHospital = caseReportedHospital;
		this.wasPatientAdmittedASInpatient = wasPatientAdmittedASInpatient;
		this.hospitalDateOfAdmission = hospitalDateOfAdmission;
		this.admittedIndICU = admittedIndICU;
		this.dateOfOnsetSymptoms = dateOfOnsetSymptoms;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Order(0)
	public String getLabSampleID() {
		return labSampleID;
	}

	public void setLabSampleID(String labSampleID) {
		this.labSampleID = labSampleID;
	}

	@Order(1)
	public SpecimenCondition getSpecimenCondition() {
		return specimenCondition;
	}

	public void setSpecimenCondition(SpecimenCondition specimenCondition) {
		this.specimenCondition = specimenCondition;
	}

	@Order(2)
	public Date getSampleDateTime() {
		return sampleDateTime;
	}

	public void setSampleDateTime(Date sampleDateTime) {
		this.sampleDateTime = sampleDateTime;
	}

	@Order(3)
	public String getReportingUserName() {
		return reportingUserName;
	}

	public void setReportingUserName(String reportingUserName) {
		this.reportingUserName = reportingUserName;
	}

	@Order(4)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Order(5)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Order(6)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Order(7)
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@Order(8)
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Order(9)
	public CaseClassification getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseClassification caseType) {
		this.caseType = caseType;
	}

	@Order(10)
	public Disease getCaseDisease() {
		return caseDisease;
	}

	public void setCaseDisease(Disease caseDisease) {
		this.caseDisease = caseDisease;
	}

	@Order(11)
	public String getResponsibleRegionName() {
		return responsibleRegionName;
	}

	public void setResponsibleRegionName(String responsibleRegionName) {
		this.responsibleRegionName = responsibleRegionName;
	}

	@Order(12)
	public String getResponsibleDistrictName() {
		return responsibleDistrictName;
	}

	public void setResponsibleDistrictName(String responsibleDistrictName) {
		this.responsibleDistrictName = responsibleDistrictName;
	}

	@Order(13)
	public String getResponsibleCommunityName() {
		return responsibleCommunityName;
	}

	public void setResponsibleCommunityName(String responsibleCommunityName) {
		this.responsibleCommunityName = responsibleCommunityName;
	}

	@Order(14)
	public Integer getWardNo() {
		return wardNo;
	}

	public void setWardNo(Integer wardNo) {
		this.wardNo = wardNo;
	}

	@Order(15)
	public String getCaseReportedHospital() {
		return caseReportedHospital;
	}

	public void setCaseReportedHospital(String caseReportedHospital) {
		this.caseReportedHospital = caseReportedHospital;
	}

	@Order(16)
	public YesNoUnknown getWasPatientAdmittedASInpatient() {
		return wasPatientAdmittedASInpatient;
	}

	public void setWasPatientAdmittedASInpatient(YesNoUnknown wasPatientAdmittedASInpatient) {
		this.wasPatientAdmittedASInpatient = wasPatientAdmittedASInpatient;
	}

	@Order(17)
	public Date getHospitalDateOfAdmission() {
		return hospitalDateOfAdmission;
	}

	public void setHospitalDateOfAdmission(Date hospitalDateOfAdmission) {
		this.hospitalDateOfAdmission = hospitalDateOfAdmission;
	}

	@Order(18)
	public YesNoUnknown getAdmittedIndICU() {
		return admittedIndICU;
	}

	public void setAdmittedIndICU(YesNoUnknown admittedIndICU) {
		this.admittedIndICU = admittedIndICU;
	}

	@Order(19)
	public Date getDateOfOnsetSymptoms() {
		return dateOfOnsetSymptoms;
	}

	public void setDateOfOnsetSymptoms(Date dateOfOnsetSymptoms) {
		this.dateOfOnsetSymptoms = dateOfOnsetSymptoms;
	}

	@Order(110)
	public String getPathogenTestLab1() {
		return pathogenTest1.lab;
	}

	@Order(111)
	public Disease getPathogenTestTestedDisease1() {
		return pathogenTest1.testedDisease;
	}

	@Order(112)
	public PathogenTestResultType getPathogenTestTestResultType1() {
		return pathogenTest1.testResultType;
	}

	//@Order(113)
	public String getPathogenTestCqSubType1() {
		return pathogenTest1.cqSubType;
	}

	@Order(114)
	public Float getPathogenTestCqValue1() {
		return pathogenTest1.cqValue;
	}

	@Order(120)
	public String getPathogenTestLab2() {
		return pathogenTest2.lab;
	}

	@Order(121)
	public Disease getPathogenTestTestedDisease2() {
		return pathogenTest2.testedDisease;
	}

	@Order(122)
	public PathogenTestResultType getPathogenTestTestResultType2() {
		return pathogenTest2.testResultType;
	}

	//@Order(123)
	public String getPathogenTestCqSubType2() {
		return pathogenTest2.cqSubType;
	}

	@Order(124)
	public Float getPathogenTestCqValue2() {
		return pathogenTest2.cqValue;
	}

	@Order(130)
	public String getPathogenTestLab3() {
		return pathogenTest3.lab;
	}

	@Order(131)
	public Disease getPathogenTestTestedDisease3() {
		return pathogenTest3.testedDisease;
	}

	@Order(132)
	public PathogenTestResultType getPathogenTestTestResultType3() {
		return pathogenTest3.testResultType;
	}

	//@Order(133)
	public String getPathogenTestCqSubType3() {
		return pathogenTest3.cqSubType;
	}

	@Order(134)
	public Float getPathogenTestCqValue3() {
		return pathogenTest3.cqValue;
	}

	public void setPathogenTest1(SampleExportPathogenTest pathogenTest1) {
		this.pathogenTest1 = pathogenTest1;
	}

	public void setPathogenTest2(SampleExportPathogenTest pathogenTest2) {
		this.pathogenTest2 = pathogenTest2;
	}

	public void setPathogenTest3(SampleExportPathogenTest pathogenTest3) {
		this.pathogenTest3 = pathogenTest3;
	}

	public static class SampleExportPathogenTest implements Serializable {
		private String lab;
		private Disease testedDisease;
		private PathogenTestResultType testResultType;
		private String cqSubType;
		private Float cqValue;

		public SampleExportPathogenTest() {

		}

		public SampleExportPathogenTest(String lab, Disease testedDisease, PathogenTestResultType testResultType, String cqSubType, Float cqValue) {
			this.lab = lab;
			this.testedDisease = testedDisease;
			this.testResultType = testResultType;
			this.cqSubType = cqSubType;
			this.cqValue = cqValue;
		}
	}

}
