package de.symeda.sormas.ui.newpalfeature;

import com.vaadin.ui.VerticalLayout;
import de.symeda.sormas.api.nepalsfeature.news.NewsCriteria;
import de.symeda.sormas.ui.ViewModelProviders;
import de.symeda.sormas.ui.utils.AbstractView;

public class NewsView extends AbstractView {
    public static final String VIEW_NAME = "news";
    private final NewsGrid grid;
    private final NewsCriteria newsCriteria;
    private NewsFilterForm filterForm;

    public NewsView() {
        super(VIEW_NAME);
        newsCriteria = new NewsCriteria();
        grid = new NewsGrid(newsCriteria);
        final VerticalLayout gridLayout = new VerticalLayout();
        gridLayout.addComponent(createFilterBar());
        gridLayout.addComponent(grid);
        addComponent(gridLayout);

        gridLayout.setMargin(true);
        gridLayout.setSpacing(false);
        gridLayout.setSizeFull();
        gridLayout.setExpandRatio(grid, 1);
        gridLayout.setStyleName("crud-main-layout");
    }

    public VerticalLayout createFilterBar() {
        VerticalLayout filterLayout = new VerticalLayout();
        filterLayout.setSpacing(false);
        filterLayout.setMargin(false);
        filterLayout.setWidth(100, Unit.PERCENTAGE);
        filterForm = new NewsFilterForm();
        filterForm.setValue(newsCriteria);
        filterLayout.addComponent(filterForm);
        filterForm.addApplyHandler( clickEvent -> reloadGrid());
        filterForm.addResetHandler(clickEvent -> {
            ViewModelProviders.of(NewsView.class).remove(NewsCriteria.class);
            navigateTo(null);
        });
        return filterLayout;
    }

    private void reloadGrid() {
        grid.reload();
    }
}
