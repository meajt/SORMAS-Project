package de.symeda.sormas.backend.sample.ncd;

import de.symeda.sormas.api.EntityDto;
import de.symeda.sormas.api.utils.PersonalData;
import de.symeda.sormas.backend.common.AbstractDomainObject;

import javax.persistence.Entity;

@Entity(name = "complete_blood_count_sample")
public class CompleteBloodCountSample extends AbstractDomainObject {
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


    private Float tlc;

    private Float neutrophils;

    private Float lymphocytes;

    private Float monocytes;

    private Float eosinophils;

    private Float basophils;

    private Float platetetCount;

    private Float rbc;

    private Float hb;

    private Float pcv;

    private Float mcv;

    private Float hch;

    private Float mchc;

    private Float rdwCv;

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
