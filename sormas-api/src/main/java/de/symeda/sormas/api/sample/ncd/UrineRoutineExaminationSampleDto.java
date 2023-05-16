package de.symeda.sormas.api.sample.ncd;

import de.symeda.sormas.api.EntityDto;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.utils.DependingOnFeatureType;
import de.symeda.sormas.api.utils.PersonalData;

@DependingOnFeatureType(featureType = FeatureType.ADDITIONAL_TESTS)
public class UrineRoutineExaminationSampleDto extends EntityDto {

    public static final String I18N_PREFIX = "UrineRoutineExamination";
    public static final String URINE_ROUTINE_EXAMINATION = "UrineRoutineExamination";

    public static final String COLOR = "color";
    public static final String TRANSPARENCY = "transparency";
    public static final String PH = "ph";
    public static final String ALBUMIN = "albumin";
    public static final String SUGAR = "sugar";
    public static final String PUS_CELLS = "pusCells";
    public static final String RBC = "rbc";
    public static final String EPITHELIAL_CELL = "epithelialCell";
    public static final String CRYSTALS = "crystals";
    public static final String CASTS = "casts";

    public static final String OTHER_URINE_TEST = "urineOtherTest";
    @PersonalData
    private UrineColor color;
    @PersonalData
    private UrineTransparency transparency;
    @PersonalData
    private UrinePh ph;
    @PersonalData
    private UrineAlbumin albumin;
    @PersonalData
    private UrineSugar sugar;
    @PersonalData
    private UrinePusCells pusCells;
    @PersonalData
    private UrineRbc rbc;
    @PersonalData
    private UrineEpithelialCell epithelialCell;
    @PersonalData
    private UrineCrystals crystals;
    @PersonalData
    private UrineCasts casts;

    @PersonalData
    private UrineOtherTest urineOtherTest;


    public UrineColor getColor() {
        return color;
    }

    public void setColor(UrineColor color) {
        this.color = color;
    }

    public UrineTransparency getTransparency() {
        return transparency;
    }

    public void setTransparency(UrineTransparency transparency) {
        this.transparency = transparency;
    }

    public UrinePh getPh() {
        return ph;
    }

    public void setPh(UrinePh ph) {
        this.ph = ph;
    }

    public UrineAlbumin getAlbumin() {
        return albumin;
    }

    public void setAlbumin(UrineAlbumin albumin) {
        this.albumin = albumin;
    }

    public UrineSugar getSugar() {
        return sugar;
    }

    public void setSugar(UrineSugar sugar) {
        this.sugar = sugar;
    }

    public UrinePusCells getPusCells() {
        return pusCells;
    }

    public void setPusCells(UrinePusCells pusCells) {
        this.pusCells = pusCells;
    }

    public UrineRbc getRbc() {
        return rbc;
    }

    public void setRbc(UrineRbc rbc) {
        this.rbc = rbc;
    }

    public UrineEpithelialCell getEpithelialCell() {
        return epithelialCell;
    }

    public void setEpithelialCell(UrineEpithelialCell epithelialCell) {
        this.epithelialCell = epithelialCell;
    }

    public UrineCrystals getCrystals() {
        return crystals;
    }

    public void setCrystals(UrineCrystals crystals) {
        this.crystals = crystals;
    }

    public UrineCasts getCasts() {
        return casts;
    }

    public void setCasts(UrineCasts casts) {
        this.casts = casts;
    }

    public UrineOtherTest getUrineOtherTest() {
        return urineOtherTest;
    }

    public void setUrineOtherTest(UrineOtherTest urineOtherTest) {
        this.urineOtherTest = urineOtherTest;
    }
}
