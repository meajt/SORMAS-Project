package de.symeda.sormas.ui.epidata;

import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.epidata.MalariaEpiDataDto;
import de.symeda.sormas.api.sample.PathogenTestDto;
import de.symeda.sormas.api.utils.fieldaccess.UiFieldAccessCheckers;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.DateTimeField;
import de.symeda.sormas.ui.utils.InfrastructureFieldsHelper;

import static de.symeda.sormas.ui.utils.CssStyles.VSPACE_TOP_3;
import static de.symeda.sormas.ui.utils.LayoutUtil.*;

public class MalariaEpiDataForm extends AbstractEditForm<MalariaEpiDataDto> {

    private final String LAYOUT_HTML =
            fluidRow(fluidRowLocs(MalariaEpiDataDto.CASE_DETECTION_METHOD), fluidRowLocs(MalariaEpiDataDto.ACTIVE_CASE_DETECTION))
            +"<div>Length of residence at current/present home address</div>"
            +fluidRow(fluidRowLocs(MalariaEpiDataDto.LENGTH_OF_RESIDENCE_Y_Y), fluidRowLocs(MalariaEpiDataDto.LENGTH_OF_RESIDENCE_M_M))
                    +"<div>if resident in current address is less than one year</div>"
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.RESIDENT_REGION), fluidRowLocs(MalariaEpiDataDto.RESIDENT_DISTRICT))
            +fluidRow(fluidRowLocs(MalariaEpiDataDto.RESIDENT_COMMUNITY), fluidRowLocs(MalariaEpiDataDto.RESIDENT_WARD_NO))
                    +"<div>Recent travel history</div>"
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.TRAVEL_ADDRESS_WITH_IN_COUNTY), fluidRowLocs(MalariaEpiDataDto.TRAVEL_DATE_WITH_IN_COUNTY))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.TRAVEL_ADDRESS_OUTSIDE_COUNTY), fluidRowLocs(MalariaEpiDataDto.TRAVEL_DATE_OUTSIDE_COUNTY))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.TYPE_OF_PREVENTING_MEASURES), fluidRowLocs(MalariaEpiDataDto.DRUG_NAME))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.DOSE_NAME), fluidRowLocs(MalariaEpiDataDto.DOSE_START_DATE))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.DOSE_END_DATE), fluidRowLocs(MalariaEpiDataDto.CONTACT_WITH_MALARIA_CASES_DETAIL))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.BLOOD_TRANSFUSION), fluidRowLocs(MalariaEpiDataDto.HISTORY_OF_CONFIRMED))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.HAS_PREVIOUS_MALARIA), fluidRowLocs(MalariaEpiDataDto.PAST_REGION))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.PAST_DATE), fluidRowLocs(MalariaEpiDataDto.PAST_DISTRICT))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.PAST_COMMUNITY), fluidRowLocs(MalariaEpiDataDto.PAST_WARD_NO))
                    +fluidRow(fluidRowLocs(MalariaEpiDataDto.FACILITY), fluidRowLocs(MalariaEpiDataDto.PLASMODIUM_SPECIES))
                    +fluidRowLocs(MalariaEpiDataDto.HAS_TREATED_N_M_T_P);

    private ComboBox residentDistrictCombo;
    private ComboBox residentCommunityCombo;
    protected MalariaEpiDataForm() {
        super(
                MalariaEpiDataDto.class,
                MalariaEpiDataDto.I18N_PREFIX,
                false,
                FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()),
                UiFieldAccessCheckers.getNoop());
        addFields();
    }

    @Override
    protected String createHtmlLayout() {
        return LAYOUT_HTML;
    }

    @Override
    protected void addFields() {
        addField(MalariaEpiDataDto.CASE_DETECTION_METHOD);
        addField(MalariaEpiDataDto.ACTIVE_CASE_DETECTION);
        addField(MalariaEpiDataDto.LENGTH_OF_RESIDENCE_Y_Y, TextField.class);
        addField(MalariaEpiDataDto.LENGTH_OF_RESIDENCE_M_M, TextField.class);

        addField(MalariaEpiDataDto.TRAVEL_ADDRESS_WITH_IN_COUNTY);
        addField(MalariaEpiDataDto.TRAVEL_DATE_WITH_IN_COUNTY);
        addField(MalariaEpiDataDto.TRAVEL_ADDRESS_OUTSIDE_COUNTY);
        addField(MalariaEpiDataDto.TRAVEL_DATE_OUTSIDE_COUNTY);
        addField(MalariaEpiDataDto.TYPE_OF_PREVENTING_MEASURES, ComboBox.class);
        addField(MalariaEpiDataDto.DRUG_NAME);
        addField(MalariaEpiDataDto.DOSE_NAME);
        addField(MalariaEpiDataDto.DOSE_START_DATE);
        addField(MalariaEpiDataDto.DOSE_END_DATE);
       // addField(MalariaEpiDataDto.DRUG_TAKEN_DURATION, DateR.class);
        addField(MalariaEpiDataDto.CONTACT_WITH_MALARIA_CASES_DETAIL);
        addField(MalariaEpiDataDto.BLOOD_TRANSFUSION);
        addField(MalariaEpiDataDto.HISTORY_OF_CONFIRMED);
        addField(MalariaEpiDataDto.HAS_PREVIOUS_MALARIA);
        addField(MalariaEpiDataDto.PLASMODIUM_SPECIES, ComboBox.class);
        addField(MalariaEpiDataDto.HAS_TREATED_N_M_T_P);
        ComboBox lab = addInfrastructureField(MalariaEpiDataDto.FACILITY);
        lab.addItems(FacadeProvider.getFacilityFacade().getAllActiveLaboratories(true));
        ComboBox residentRegionCombo = addField(MalariaEpiDataDto.RESIDENT_REGION, ComboBox.class);
        residentDistrictCombo = addField(MalariaEpiDataDto.RESIDENT_DISTRICT, ComboBox.class);
        residentCommunityCombo = addField(MalariaEpiDataDto.RESIDENT_COMMUNITY, ComboBox.class);
        addField(MalariaEpiDataDto.RESIDENT_WARD_NO);
        addField(MalariaEpiDataDto.PAST_WARD_NO);
        InfrastructureFieldsHelper.initInfrastructureFields(residentRegionCombo, residentDistrictCombo, residentCommunityCombo);
        ComboBox pastRegionCombo = addField(MalariaEpiDataDto.PAST_REGION, ComboBox.class);
        ComboBox pastDistrictCombo = addField(MalariaEpiDataDto.PAST_DISTRICT, ComboBox.class);
        ComboBox pastCommunityCombo = addField(MalariaEpiDataDto.PAST_COMMUNITY, ComboBox.class);
        InfrastructureFieldsHelper.initInfrastructureFields(pastRegionCombo, pastDistrictCombo, pastCommunityCombo);

    }
}
