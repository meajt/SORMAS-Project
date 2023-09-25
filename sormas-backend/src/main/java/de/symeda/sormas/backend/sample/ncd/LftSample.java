package de.symeda.sormas.backend.sample.ncd;

import de.symeda.auditlog.api.Audited;
import de.symeda.sormas.backend.common.AbstractDomainObject;

import javax.persistence.Entity;

@Entity(name = "lft_sample")
@Audited
public class LftSample extends AbstractDomainObject {
    public static final String LFT_SAMPLE = "LftSampleDto";
    public static final String I18N_PREFIX = "LftSample";
    public static final String BILIRUBIN_T = "bilirubinT";
    public static final String BILIRUBIN_D = "bilirubinD";
    public static final String ALKALINE_PHOSPATASE = "alkalinePhospatase";
    public static final String KINETIC = "kinetic";
    public static final String SGPT = "sgpt";
    public static final String SGOT = "sgot";
    public static final String VLDL = "vldl";


    private Float bilirubinT;

    private Float bilirubinD;

    private Float alkalinePhospatase;

    private Float kinetic;

    private Float sgpt;

    private Float sgot;

    private Float vldl;

    public Float getBilirubinT() {
        return bilirubinT;
    }

    public void setBilirubinT(Float bilirubinT) {
        this.bilirubinT = bilirubinT;
    }

    public Float getBilirubinD() {
        return bilirubinD;
    }

    public void setBilirubinD(Float bilirubinD) {
        this.bilirubinD = bilirubinD;
    }

    public Float getAlkalinePhospatase() {
        return alkalinePhospatase;
    }

    public void setAlkalinePhospatase(Float alkalinePhospatase) {
        this.alkalinePhospatase = alkalinePhospatase;
    }

    public Float getKinetic() {
        return kinetic;
    }

    public void setKinetic(Float kinetic) {
        this.kinetic = kinetic;
    }

    public Float getSgpt() {
        return sgpt;
    }

    public void setSgpt(Float sgpt) {
        this.sgpt = sgpt;
    }

    public Float getSgot() {
        return sgot;
    }

    public void setSgot(Float sgot) {
        this.sgot = sgot;
    }

    public Float getVldl() {
        return vldl;
    }

    public void setVldl(Float vldl) {
        this.vldl = vldl;
    }
}
