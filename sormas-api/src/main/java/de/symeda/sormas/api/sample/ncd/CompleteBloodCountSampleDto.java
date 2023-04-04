package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.utils.PersonalData;

public class CompleteBloodCountSampleDto {
    public static final String COMPLETE_BLOOD_COUNT_SAMPLE = "CompleteBloodCountSample";
    public static final String I18N_PREFIX = "CompleteBloodCountSample";
    public static final String TLC = "tlc";
    public static final String NEUTROPHILS = "neutrophils";
    public static final String LYMPHOCYTES = "lymphocytes";
    public static final String MONOCYTES = "monocytes";
    public static final String EOSINOPHILS = "eosinophils";
    public static final String BASOPHILS = "basophils";
    public static final String PLATETET_COUNT = "platetetCount";
    public static final String RBC = "rbc";
    public static final String HB = "hb";
    public static final String PCV = "pcv";
    public static final String MCV = "mcv";
    public static final String HCH = "hch";
    public static final String MCHC = "mchc";
    public static final String RDW_CV = "rdwCv";
    public static final String HB_A1C = "hbA1c";

    @PersonalData
    private Float tlc;
    @PersonalData
    private Float neutrophils;
    @PersonalData
    private Float lymphocytes;
    @PersonalData
    private Float monocytes;
    @PersonalData
    private Float eosinophils;
    @PersonalData
    private Float basophils;
    @PersonalData
    private Float platetetCount;
    @PersonalData
    private Float rbc;
    @PersonalData
    private Float hb;
    @PersonalData
    private Float pcv;
    @PersonalData
    private Float mcv;
    @PersonalData
    private Float hch;
    @PersonalData
    private Float mchc;
    @PersonalData
    private Float rdwCv;
    @PersonalData
    private Float hbA1c;

    public Float getTlc() {
        return tlc;
    }

    public void setTlc(Float tlc) {
        this.tlc = tlc;
    }

    public Float getNeutrophils() {
        return neutrophils;
    }

    public void setNeutrophils(Float neutrophils) {
        this.neutrophils = neutrophils;
    }

    public Float getLymphocytes() {
        return lymphocytes;
    }

    public void setLymphocytes(Float lymphocytes) {
        this.lymphocytes = lymphocytes;
    }

    public Float getMonocytes() {
        return monocytes;
    }

    public void setMonocytes(Float monocytes) {
        this.monocytes = monocytes;
    }

    public Float getEosinophils() {
        return eosinophils;
    }

    public void setEosinophils(Float eosinophils) {
        this.eosinophils = eosinophils;
    }

    public Float getBasophils() {
        return basophils;
    }

    public void setBasophils(Float basophils) {
        this.basophils = basophils;
    }

    public Float getPlatetetCount() {
        return platetetCount;
    }

    public void setPlatetetCount(Float platetetCount) {
        this.platetetCount = platetetCount;
    }

    public Float getRbc() {
        return rbc;
    }

    public void setRbc(Float rbc) {
        this.rbc = rbc;
    }

    public Float getHb() {
        return hb;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getPcv() {
        return pcv;
    }

    public void setPcv(Float pcv) {
        this.pcv = pcv;
    }

    public Float getMcv() {
        return mcv;
    }

    public void setMcv(Float mcv) {
        this.mcv = mcv;
    }

    public Float getHch() {
        return hch;
    }

    public void setHch(Float hch) {
        this.hch = hch;
    }

    public Float getMchc() {
        return mchc;
    }

    public void setMchc(Float mchc) {
        this.mchc = mchc;
    }

    public Float getRdwCv() {
        return rdwCv;
    }

    public void setRdwCv(Float rdwCv) {
        this.rdwCv = rdwCv;
    }

    public Float getHbA1c() {
        return hbA1c;
    }

    public void setHbA1c(Float hbA1c) {
        this.hbA1c = hbA1c;
    }
}
