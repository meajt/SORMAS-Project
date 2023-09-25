package de.symeda.sormas.ui.caze;

import com.vaadin.v7.ui.ComboBox;
import de.symeda.sormas.api.caze.CaseConclusionDto;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.InfrastructureFieldsHelper;

import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRow;
import static de.symeda.sormas.ui.utils.LayoutUtil.fluidRowLocs;

public class CaseConclusionForm extends AbstractEditForm<CaseConclusionDto> {

    private final String HTML_LAYOUT =
            "<div>Malaria infection likely acquired place.</div>"
            +fluidRow(fluidRowLocs(CaseConclusionDto.REGION_REFERENCE), fluidRowLocs(CaseConclusionDto.DISTRICT_REFERENCE))
            +fluidRow(fluidRowLocs(CaseConclusionDto.COMMUNITY_REFERENCE_D), fluidRowLocs(CaseConclusionDto.WARD_NO))
            +fluidRow(fluidRowLocs(CaseConclusionDto.SPECIES), fluidRowLocs(CaseConclusionDto.CASE_CLASSIFICATION));
    protected CaseConclusionForm() {
        super(CaseConclusionDto.class, CaseConclusionDto.I18N_PREFIX, false);
        addFields();
    }

    @Override
    protected String createHtmlLayout() {
        return HTML_LAYOUT;
    }

    @Override
    protected void addFields() {
        ComboBox regionCombo = addField(CaseConclusionDto.REGION_REFERENCE, ComboBox.class);
        ComboBox districtCombo = addField(CaseConclusionDto.DISTRICT_REFERENCE, ComboBox.class);
        ComboBox communityCombo = addField(CaseConclusionDto.COMMUNITY_REFERENCE_D, ComboBox.class);
        addField(CaseConclusionDto.WARD_NO);
        addField(CaseConclusionDto.SPECIES, ComboBox.class);
        addField(CaseConclusionDto.CASE_CLASSIFICATION, ComboBox.class);
        InfrastructureFieldsHelper.initInfrastructureFields(regionCombo, districtCombo, communityCombo);
    }
}
