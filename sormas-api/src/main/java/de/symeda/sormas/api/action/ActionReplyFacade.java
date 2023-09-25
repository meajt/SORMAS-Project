package de.symeda.sormas.api.action;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ActionReplyFacade {
    ActionReplyDto saveActionReply(ActionReplyDto dto);
    List<ActionReplyDto> getActionReply(ActionReferenceDto actionRef);
}
