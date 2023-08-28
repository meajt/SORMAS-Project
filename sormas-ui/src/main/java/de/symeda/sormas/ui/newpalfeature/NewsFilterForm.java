package de.symeda.sormas.ui.newpalfeature;

import com.vaadin.v7.ui.ComboBox;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.nepalsfeature.news.NewsCriteria;
import de.symeda.sormas.api.nepalsfeature.news.NewsDto;
import de.symeda.sormas.api.utils.fieldvisibility.FieldVisibilityCheckers;
import de.symeda.sormas.ui.utils.AbstractFilterForm;
import de.symeda.sormas.ui.utils.InfrastructureFieldsHelper;

public class NewsFilterForm extends AbstractFilterForm<NewsCriteria> {

    protected NewsFilterForm() {
        super(NewsCriteria.class, NewsDto.I18N_PREFIX, FieldVisibilityCheckers.withCountry(FacadeProvider.getConfigFacade().getCountryLocale()));
    }

    @Override
    protected String[] getMainFilterLocators() {
        return new String[]{
                NewsCriteria.REGION,
                NewsCriteria.DISTRICT,
                NewsCriteria.COMMUNITY,
                NewsCriteria.RISK_LEVE,
                NewsCriteria.START_DATE,
                NewsCriteria.END_DATE
        };
    }

    @Override
    protected void addFields() {
        addFields(
            NewsCriteria.RISK_LEVE,
            NewsCriteria.START_DATE,
            NewsCriteria.END_DATE);
        ComboBox region = addField(NewsCriteria.REGION, ComboBox.class);
        ComboBox district = addField(NewsCriteria.DISTRICT, ComboBox.class);
        ComboBox community = addField(NewsCriteria.COMMUNITY, ComboBox.class);
        InfrastructureFieldsHelper.initInfrastructureFields(region, district, community);

    }
}
