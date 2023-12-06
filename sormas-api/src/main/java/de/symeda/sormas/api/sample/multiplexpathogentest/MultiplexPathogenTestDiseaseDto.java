package de.symeda.sormas.api.sample.multiplexpathogentest;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.EntityDto;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.sample.PathogenTestResultType;
import de.symeda.sormas.api.utils.DependingOnFeatureType;
import de.symeda.sormas.api.utils.Required;

@DependingOnFeatureType(featureType = FeatureType.SAMPLES_LAB)
public class MultiplexPathogenTestDiseaseDto {

	public static final String I18N_PREFIX = "MultiplexPathogenTestDisease";
	public static final String TESTED_DISEASE = "testedDisease";
	public static final String TEST_RESULT = "testResult";
	public static final String INFLUENZA_A_TEST_RESULT = "influenzaATestResult";
	public static final String INFLUENZA_A_OTHER_TEST_RESULT = "influenzaAOtherTestResult";
	public static final String INFLUENZA_A_CT_VALUE= "influenzaACTValue";
	public static final String INFLUENZA_B_CT_VALUE= "influenzaBCTValue";

	public static final String INFLUENZA_B_TEST_RESULT = "influenzaBTestResult";
	public static final String INFLUENZA_B_OTHER_TEST_RESULT = "influenzaBOtherTestResult";
	@Required
	private Disease testedDisease;
	@Required
	private PathogenTestResultType testResult;
	private InfluenzaAPathogenTestResult influenzaATestResult;
	private Float influenzaACTValue;
	private String influenzaAOtherTestResult;
	private InfluenzaBPathogenTestResult influenzaBTestResult;
	private Float influenzaBCTValue;
	private String influenzaBOtherTestResult;

	public Disease getTestedDisease() {
		return testedDisease;
	}

	public void setTestedDisease(Disease testedDisease) {
		this.testedDisease = testedDisease;
	}

	public PathogenTestResultType getTestResult() {
		return testResult;
	}

	public void setTestResult(PathogenTestResultType testResult) {
		this.testResult = testResult;
	}

	public InfluenzaAPathogenTestResult getInfluenzaATestResult() {
		return influenzaATestResult;
	}

	public void setInfluenzaATestResult(InfluenzaAPathogenTestResult influenzaATestResult) {
		this.influenzaATestResult = influenzaATestResult;
	}

	public String getInfluenzaAOtherTestResult() {
		return influenzaAOtherTestResult;
	}

	public void setInfluenzaAOtherTestResult(String influenzaAOtherTestResult) {
		this.influenzaAOtherTestResult = influenzaAOtherTestResult;
	}

	public InfluenzaBPathogenTestResult getInfluenzaBTestResult() {
		return influenzaBTestResult;
	}

	public void setInfluenzaBTestResult(InfluenzaBPathogenTestResult influenzaBTestResult) {
		this.influenzaBTestResult = influenzaBTestResult;
	}

	public String getInfluenzaBOtherTestResult() {
		return influenzaBOtherTestResult;
	}

	public void setInfluenzaBOtherTestResult(String influenzaBOtherTestResult) {
		this.influenzaBOtherTestResult = influenzaBOtherTestResult;
	}

	public Float getInfluenzaACTValue() {
		return influenzaACTValue;
	}

	public void setInfluenzaACTValue(Float influenzaACTValue) {
		this.influenzaACTValue = influenzaACTValue;
	}

	public Float getInfluenzaBCTValue() {
		return influenzaBCTValue;
	}

	public void setInfluenzaBCTValue(Float influenzaBCTValue) {
		this.influenzaBCTValue = influenzaBCTValue;
	}
}
