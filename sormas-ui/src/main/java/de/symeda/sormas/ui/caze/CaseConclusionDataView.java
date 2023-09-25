package de.symeda.sormas.ui.caze;

import de.symeda.sormas.api.epidata.EpiDataDto;
import de.symeda.sormas.api.user.UserRight;
import de.symeda.sormas.ui.ControllerProvider;
import de.symeda.sormas.ui.utils.CommitDiscardWrapperComponent;
import de.symeda.sormas.ui.utils.DetailSubComponentWrapper;

public class CaseConclusionDataView extends AbstractCaseView{
    public static final String VIEW_NAME = ROOT_VIEW_NAME + "/conclusion";
    private CommitDiscardWrapperComponent<CaseConclusionForm> epiDataComponent;
    public CaseConclusionDataView() {
        super(VIEW_NAME, true);
    }

    @Override
    protected void initView(String params) {
        epiDataComponent = ControllerProvider.getCaseController()
                .getCaseConclusionComponent();
        setSubComponent(epiDataComponent);
        setEditPermission(epiDataComponent);

    }
}
