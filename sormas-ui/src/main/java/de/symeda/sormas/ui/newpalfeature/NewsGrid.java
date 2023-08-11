package de.symeda.sormas.ui.newpalfeature;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.renderers.HtmlRenderer;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.caze.CaseReferenceDto;
import de.symeda.sormas.api.event.EventSourceType;
import de.symeda.sormas.api.event.RiskLevel;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.nepalsfeature.news.NewsCriteria;
import de.symeda.sormas.api.nepalsfeature.news.NewsDto;
import de.symeda.sormas.ui.ControllerProvider;
import de.symeda.sormas.ui.utils.FilteredGrid;
import de.symeda.sormas.ui.utils.ShowDetailsListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NewsGrid extends FilteredGrid<NewsDto, NewsCriteria> {
    private final static String OPEN_LINK_ACTION = "openLinkAction";
    private final static String CREATE_EVENT_ACTION = "createEventAction";
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public NewsGrid(NewsCriteria criteria) {
        super(NewsDto.class);
        setSizeFull();
        setLazyDataProvider();
        setCriteria(criteria);
        Column<NewsDto, String> openLickColum = addColumn(entry -> VaadinIcons.EXTERNAL_LINK.getHtml(), new HtmlRenderer());
        openLickColum.setId(OPEN_LINK_ACTION);
        openLickColum.setCaption("Open Link");
        openLickColum.setWidth(100);

        addItemClickListener(new ShowDetailsListener<>(OPEN_LINK_ACTION, e -> getUI().getPage().open(e.getNewsLink(), "_blank")));


        Column<NewsDto, String> createEvent = addColumn(entry -> VaadinIcons.PLUS_CIRCLE.getHtml(), new HtmlRenderer());
        createEvent.setId(CREATE_EVENT_ACTION);
        createEvent.setCaption("Create Event");
        createEvent.setWidth(100);
        addItemClickListener(new ShowDetailsListener<>(CREATE_EVENT_ACTION, news -> {
            ControllerProvider.getEventController().create(event -> {
                event.setEventTitle(news.getTitle());
                event.setEventDesc(news.getSummary());
                event.setSrcType(EventSourceType.MEDIA_NEWS);
                event.setSrcMediaWebsite(news.getNewsLink());
                event.setSrcMediaName(news.getNewsSource());
                event.setRiskLevel(actionPriorityFromCaption(news.getEpidemiologicalRiskLevel()));

                try {
                    event.setStartDate(dateFormat.parse(news.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            });
        }));
        setColumns(NewsDto.TITLE, NewsDto.SUMMARY,  NewsDto.PROVINCE,  NewsDto.DISTRICT,  NewsDto.PALIKA, NewsDto.DATE, NewsDto.RISK_LEVEL, OPEN_LINK_ACTION, CREATE_EVENT_ACTION);
        getColumn(NewsDto.SUMMARY).setWidth(600);
        getColumn(NewsDto.PROVINCE).setWidth(80);
        getColumn(NewsDto.DISTRICT).setWidth(80);
        getColumn(NewsDto.RISK_LEVEL).setWidth(80);
    }
    private  RiskLevel actionPriorityFromCaption(String priorityCaption) {
        if (I18nProperties.getEnumCaption(RiskLevel.HIGH).equals(priorityCaption))
            return RiskLevel.HIGH;
        if (I18nProperties.getEnumCaption(RiskLevel.MODERATE).equals(priorityCaption))
            return RiskLevel.MODERATE;
        if (I18nProperties.getEnumCaption(RiskLevel.LOW).equals(priorityCaption))
            return RiskLevel.LOW;
        return RiskLevel.UNKNOWN;
    }
    public void reload() {
        getDataProvider().refreshAll();
    }

    public void setLazyDataProvider() {
        setLazyDataProvider(FacadeProvider.getNewsFacade()::getNewList, FacadeProvider.getNewsFacade()::count);
    }
    public void setEagerDataProvider() {
        setEagerDataProvider(FacadeProvider.getNewsFacade()::getNewList);
    }
}
