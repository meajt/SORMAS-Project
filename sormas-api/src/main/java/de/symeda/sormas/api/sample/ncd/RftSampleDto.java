package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.utils.PersonalData;

public class RftSampleDto {
    public static final String RFT_SAMPLE = "RftSampleDto";
    public static final String I18N_PREFIX = "RftSample";
    public static final String UREA = "urea";
    public static final String CREATININE = "creatinine";
    public static final String SODIUM = "sodium";
    public static final String POTASSIUM = "potassium";

    @PersonalData
    private Float urea;
    @PersonalData
    private Float creatinine;
    @PersonalData
    private Float sodium;
    @PersonalData
    private Float potassium;

    public Float getUrea() {
        return urea;
    }

    public void setUrea(Float urea) {
        this.urea = urea;
    }

    public Float getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(Float creatinine) {
        this.creatinine = creatinine;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    public Float getPotassium() {
        return potassium;
    }

    public void setPotassium(Float potassium) {
        this.potassium = potassium;
    }
}
