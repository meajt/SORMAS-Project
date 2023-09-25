package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.EntityDto;
import de.symeda.sormas.api.utils.PersonalData;

public class HealthScreeningTestDto extends EntityDto {
    public static final String HEALTH_SCREENING_TEST = "HealthScreeningTest";
    public static final String I18N_PREFIX = "HealthScreeningTest";
    public static final String FASTING = "fasting";
    public static final String POST_PARANDIAL = "postParandial";
    public static final String TRIGLYCERIDE = "triglyceride";
    public static final String TOTAL_CHOLESTEROL = "totalCholesterol";

    @PersonalData
    private Integer fasting;
    @PersonalData
    private Integer postParandial;
    @PersonalData
    private Integer triglyceride;
    @PersonalData
    private Integer totalCholesterol;

    private UrineRoutineExaminationSampleDto urineRoutineExaminationSampleDto;

    public Integer getFasting() {
        return fasting;
    }

    public void setFasting(Integer fasting) {
        this.fasting = fasting;
    }

    public Integer getPostParandial() {
        return postParandial;
    }

    public void setPostParandial(Integer postParandial) {
        this.postParandial = postParandial;
    }

    public Integer getTriglyceride() {
        return triglyceride;
    }

    public void setTriglyceride(Integer triglyceride) {
        this.triglyceride = triglyceride;
    }

    public Integer getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(Integer totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public UrineRoutineExaminationSampleDto getUrineRoutineExaminationSampleDto() {
        return urineRoutineExaminationSampleDto;
    }

    public void setUrineRoutineExaminationSampleDto(UrineRoutineExaminationSampleDto urineRoutineExaminationSampleDto) {
        this.urineRoutineExaminationSampleDto = urineRoutineExaminationSampleDto;
    }
}
