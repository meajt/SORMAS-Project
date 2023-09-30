package de.symeda.sormas.backend.sample.ncd;

import de.symeda.sormas.api.sample.ncd.*;
import de.symeda.sormas.backend.common.AbstractDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = UrineRoutineExaminationSample.TABLE_NAME)
public class UrineRoutineExaminationSample extends AbstractDomainObject {
    public static final String TABLE_NAME = "urine_re_sample";
    private UrineColor color;

    private UrineTransparency transparency;

    private UrinePh ph;

    private UrineAlbumin albumin;

    private UrineSugar sugar;

    private UrinePusCells pusCells;

    private UrineRbc rbc;

    private UrineEpithelialCell epithelialCell;

    private UrineCrystals crystals;

    private UrineCasts casts;
    private UrineOtherTest urineOtherTest;

    @Column
    public UrineColor getColor() {
        return color;
    }

    public void setColor(UrineColor color) {
        this.color = color;
    }
    @Column
    public UrineTransparency getTransparency() {
        return transparency;
    }

    public void setTransparency(UrineTransparency transparency) {
        this.transparency = transparency;
    }
    @Column
    public UrinePh getPh() {
        return ph;
    }

    public void setPh(UrinePh ph) {
        this.ph = ph;
    }
    @Column
    public UrineAlbumin getAlbumin() {
        return albumin;
    }

    public void setAlbumin(UrineAlbumin albumin) {
        this.albumin = albumin;
    }
    @Column
    public UrineSugar getSugar() {
        return sugar;
    }

    public void setSugar(UrineSugar sugar) {
        this.sugar = sugar;
    }
    @Column
    public UrinePusCells getPusCells() {
        return pusCells;
    }

    public void setPusCells(UrinePusCells pusCells) {
        this.pusCells = pusCells;
    }
    @Column
    public UrineRbc getRbc() {
        return rbc;
    }

    public void setRbc(UrineRbc rbc) {
        this.rbc = rbc;
    }
    @Column
    public UrineEpithelialCell getEpithelialCell() {
        return epithelialCell;
    }

    public void setEpithelialCell(UrineEpithelialCell epithelialCell) {
        this.epithelialCell = epithelialCell;
    }
    @Column
    public UrineCrystals getCrystals() {
        return crystals;
    }

    public void setCrystals(UrineCrystals crystals) {
        this.crystals = crystals;
    }
    @Column
    public UrineCasts getCasts() {
        return casts;
    }

    public void setCasts(UrineCasts casts) {
        this.casts = casts;
    }

    @Column
    public UrineOtherTest getUrineOtherTest() {
        return urineOtherTest;
    }

    public void setUrineOtherTest(UrineOtherTest urineOtherTest) {
        this.urineOtherTest = urineOtherTest;
    }
}
