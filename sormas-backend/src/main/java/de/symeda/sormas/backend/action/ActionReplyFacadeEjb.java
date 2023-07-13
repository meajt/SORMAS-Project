package de.symeda.sormas.backend.action;

import de.symeda.sormas.api.action.ActionReferenceDto;
import de.symeda.sormas.api.action.ActionReplyDto;
import de.symeda.sormas.api.action.ActionReplyFacade;
import de.symeda.sormas.api.user.UserRight;
import de.symeda.sormas.backend.user.UserService;
import de.symeda.sormas.backend.util.DtoHelper;
import de.symeda.sormas.backend.util.ModelConstants;
import de.symeda.sormas.backend.util.RightsAllowed;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "ActionReplyFacade")
@RightsAllowed(UserRight._EVENT_VIEW)
public class ActionReplyFacadeEjb implements ActionReplyFacade {
    @PersistenceContext(unitName = ModelConstants.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @EJB
    private ActionService actionService;
    @EJB
    private UserService userService;

    public ActionReply fillOrBuildEntity(ActionReplyDto source, ActionReply target, boolean checkChangeDate) {
        if (source == null) {
            return null;
        }
        target = DtoHelper.fillOrBuildEntity(source, target, ActionReply::new, checkChangeDate);
        target.setReply(source.getReply());
        target.setAction(actionService.getByReferenceDto(source.getAction()));
        target.setCreatedBy(userService.getByReferenceDto(source.getCreatedBy()));
        return target;
    }

    public ActionReplyDto toDto(ActionReply source) {
        if (source == null) {
            return null;
        }
        ActionReplyDto target = new ActionReplyDto();
        DtoHelper.fillDto(target, source);
        target.setReply(source.getReply());
        target.setCreatedBy(source.getCreatedBy().toReference());
        target.setAction(source.getAction().toReference());
        return target;
    }

    public ActionReplyDto saveActionReply(ActionReplyDto dto) {
        ActionReply actionReply = fillOrBuildEntity(dto, null, true);
        em.persist(actionReply);
        em.flush();
        return toDto(actionReply);
    }

    public List<ActionReplyDto> getActionReply(ActionReferenceDto actionRef) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ActionReply> cq = cb.createQuery(ActionReply.class);
        Root<ActionReply> actionReply = cq.from(ActionReply.class);
        Join<ActionReply, Action> actionJoin = actionReply.join(ActionReply.ACTION, JoinType.INNER);
        cq.select(actionReply);
        cq.where(cb.equal(actionJoin.get(Action.UUID), actionRef.getUuid()));
        List<ActionReply> result = em.createQuery(cq).getResultList();
        return result.stream().map(this::toDto).collect(Collectors.toList());
    }

    @LocalBean
    @Stateless
    public static class ActionReplyFacadeEjbLocal extends ActionReplyFacadeEjb {

    }

}
