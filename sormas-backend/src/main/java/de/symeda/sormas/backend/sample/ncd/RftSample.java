package de.symeda.sormas.backend.sample.ncd;

import de.symeda.sormas.backend.common.AbstractDomainObject;

import javax.persistence.Entity;

@Entity(name = "rft_sample")
public class RftSample extends AbstractDomainObject {
    public static final String TABLE_NAME = "rft_sample";

    public static final String SAMPLE_ID = "sample_id";
    public static final String UREA = "urea";
    public static final String CREATININE = "creatinine";
    public static final String SODIUM = "sodium";
    public static final String POTASSIUM = "potassium";


    private Float urea;

    private Float creatinine;

    private Float sodium;

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
