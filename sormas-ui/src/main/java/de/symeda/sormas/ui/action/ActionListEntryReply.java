package de.symeda.sormas.ui.action;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.action.ActionReferenceDto;
import de.symeda.sormas.api.action.ActionReplyDto;
import de.symeda.sormas.api.i18n.Captions;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.utils.HtmlHelper;
import de.symeda.sormas.ui.utils.CssStyles;
import de.symeda.sormas.ui.utils.DateFormatHelper;

import java.util.List;

import static de.symeda.sormas.api.utils.HtmlHelper.cleanHtml;

public class ActionListEntryReply extends HorizontalLayout {
    VerticalLayout withContentLayout;
    ActionReferenceDto action;

    public ActionListEntryReply(ActionReferenceDto actionReferenceDto) {
        this.action = actionReferenceDto;
        withContentLayout = new VerticalLayout();
        withContentLayout.setWidth(100, Unit.PERCENTAGE);
        withContentLayout.setSpacing(false);
        withContentLayout.setMargin(false);
        addComponent(withContentLayout);
        reload();
    }

    private void addReplay(ActionReplyDto reply) {

        VerticalLayout descReplyLayout = new VerticalLayout();
        descReplyLayout.setMargin(false);
        descReplyLayout.setSpacing(false);
        descReplyLayout.setWidth(100, Unit.PERCENTAGE);
        descReplyLayout.addStyleName(CssStyles.RICH_TEXT_CONTENT_CONTAINER);

        VerticalLayout topLeftLayout = new VerticalLayout();

        topLeftLayout.setMargin(false);
        topLeftLayout.setSpacing(false);
        Label creatorLabel = new Label(
                String.format(
                        I18nProperties.getCaption(Captions.actionCreatingLabel),
                        DateFormatHelper.formatDate(reply.getCreationDate()),
                        reply.getCreatedBy().getCaption()));
        creatorLabel.addStyleName(CssStyles.LABEL_ITALIC);
        topLeftLayout.addComponent(creatorLabel);
        withContentLayout.addComponent(topLeftLayout);
        withContentLayout.addComponents(descReplyLayout);

        Label description = new Label(cleanHtml(reply.getReply(), HtmlHelper.EVENTACTION_WHITELIST), ContentMode.HTML);
        description.setWidth(100, Unit.PERCENTAGE);
        descReplyLayout.addComponent(description);
    }

    private void reload() {
        if (action != null) {
            List<ActionReplyDto> actionReply = FacadeProvider.getActionReplyFacade().getActionReply(action);
            actionReply.forEach(this::addReplay);
        }
    }


}