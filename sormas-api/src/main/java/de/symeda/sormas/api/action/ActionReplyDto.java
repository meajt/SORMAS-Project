package de.symeda.sormas.api.action;

import de.symeda.sormas.api.EntityDto;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.user.UserReferenceDto;
import de.symeda.sormas.api.utils.DependingOnFeatureType;

@DependingOnFeatureType(featureType = FeatureType.EVENT_SURVEILLANCE)
public class ActionReplyDto extends EntityDto {
    private UserReferenceDto createdBy;
    private ActionReferenceDto action;
    private String reply;

    public UserReferenceDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserReferenceDto createdBy) {
        this.createdBy = createdBy;
    }

    public ActionReferenceDto getAction() {
        return action;
    }

    public void setAction(ActionReferenceDto action) {
        this.action = action;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
