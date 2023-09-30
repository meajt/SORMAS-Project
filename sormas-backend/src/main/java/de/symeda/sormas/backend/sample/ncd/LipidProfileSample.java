package de.symeda.sormas.backend.sample.ncd;

import de.symeda.sormas.backend.common.AbstractDomainObject;

import javax.persistence.Entity;

@Entity(name = "lipid_profile_sample")
public class LipidProfileSample extends AbstractDomainObject {
    public static final String I18N_PREFIX = "LipidProfileSample";
    public static final String LIPID_PROFILE_SAMPLE = "lipidProfileSample";
    public static final String CHOLESTROL_METHOD = "cholestrolMethod";
    public static final String HDL_METHOD = "hdlMethod";
    public static final String LDL_METHOD = "ldlMethod";
    public static final String TRIGLYCERIDE_METHOD = "triglycerideMethod";
    public static final String URIC_ACID_METHOD = "uricAcidMethod";
    public static final String TOTAL_PROTEIN_METHOD = "totalProteinMethod";
    public static final String ALBUMIN_METHOD = "albuminMethod";
    public static final String CALCIUM_METHOD = "calciumMethod";

    private Float cholestrolMethod;

    private Float hdlMethod;

    private Float ldlMethod;

    private Float triglycerideMethod;


    private Float uricAcidMethod;

    private Float totalProteinMethod;

    private Float albuminMethod;

    private Float calciumMethod;

    private Integer fasting;
    private Integer postParandial;

    public Float getCholestrolMethod() {
        return cholestrolMethod;
    }

    public void setCholestrolMethod(Float cholestrolMethod) {
        this.cholestrolMethod = cholestrolMethod;
    }

    public Float getHdlMethod() {
        return hdlMethod;
    }

    public void setHdlMethod(Float hdlMethod) {
        this.hdlMethod = hdlMethod;
    }

    public Float getLdlMethod() {
        return ldlMethod;
    }

    public void setLdlMethod(Float ldlMethod) {
        this.ldlMethod = ldlMethod;
    }

    public Float getTriglycerideMethod() {
        return triglycerideMethod;
    }

    public void setTriglycerideMethod(Float triglycerideMethod) {
        this.triglycerideMethod = triglycerideMethod;
    }

    public Float getUricAcidMethod() {
        return uricAcidMethod;
    }

    public void setUricAcidMethod(Float uricAcidMethod) {
        this.uricAcidMethod = uricAcidMethod;
    }

    public Float getTotalProteinMethod() {
        return totalProteinMethod;
    }

    public void setTotalProteinMethod(Float totalProteinMethod) {
        this.totalProteinMethod = totalProteinMethod;
    }

    public Float getAlbuminMethod() {
        return albuminMethod;
    }

    public void setAlbuminMethod(Float albuminMethod) {
        this.albuminMethod = albuminMethod;
    }

    public Float getCalciumMethod() {
        return calciumMethod;
    }

    public void setCalciumMethod(Float calciumMethod) {
        this.calciumMethod = calciumMethod;
    }

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
}
