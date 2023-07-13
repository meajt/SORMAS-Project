package de.symeda.sormas.backend.action;

import de.symeda.auditlog.api.Audited;
import de.symeda.sormas.api.user.UserReferenceDto;
import de.symeda.sormas.backend.common.AbstractDomainObject;
import de.symeda.sormas.backend.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Audited
public class ActionReply extends AbstractDomainObject {
    public static final String ACTION = "action";
    private User createdBy;
    private String reply;
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
