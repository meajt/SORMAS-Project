package de.symeda.sormas.app.backend.task;

import de.symeda.sormas.api.task.TaskDto;
import de.symeda.sormas.app.backend.caze.Case;
import de.symeda.sormas.app.backend.caze.CaseDtoHelper;
import de.symeda.sormas.app.backend.common.AdoDtoHelper;
import de.symeda.sormas.app.backend.common.DaoException;
import de.symeda.sormas.app.backend.common.DatabaseHelper;
import de.symeda.sormas.app.backend.contact.Contact;
import de.symeda.sormas.app.backend.contact.ContactDtoHelper;
import de.symeda.sormas.app.backend.event.Event;
import de.symeda.sormas.app.backend.event.EventDtoHelper;
import de.symeda.sormas.app.backend.user.User;
import de.symeda.sormas.app.backend.user.UserDtoHelper;

/**
 * Created by Stefan Szczesny on 24.10.2016.
 */
public class TaskDtoHelper extends AdoDtoHelper<Task, TaskDto> {

    @Override
    public Task create() {
        return new Task();
    }

    @Override
    public TaskDto createDto() {
        return new TaskDto();
    }

    @Override
    public void fillInnerFromDto(Task ado, TaskDto dto) {
        try {
            ado.setTaskContext(dto.getTaskContext());
            if (dto.getCaze() != null) {
                ado.setCaze(DatabaseHelper.getCaseDao().queryUuid(dto.getCaze().getUuid()));
            } else {
                ado.setCaze(null);
            }
            if (dto.getContact() != null) {
                ado.setContact(DatabaseHelper.getContactDao().queryUuid(dto.getContact().getUuid()));
            } else {
                ado.setContact(null);
            }
            if (dto.getEvent() != null) {
                ado.setEvent(DatabaseHelper.getEventDao().queryUuid(dto.getEvent().getUuid()));
            } else {
                ado.setEvent(null);
            }
            ado.setTaskType(dto.getTaskType());
            ado.setTaskStatus(dto.getTaskStatus());
            ado.setDueDate(dto.getDueDate());
            ado.setPriority(dto.getPriority());
            ado.setSuggestedStart(dto.getSuggestedStart());
            ado.setStatusChangeDate(dto.getStatusChangeDate());
            ado.setPerceivedStart(dto.getPerceivedStart());

            if (dto.getCreatorUser() != null) {
                ado.setCreatorUser(DatabaseHelper.getUserDao().queryUuid(dto.getCreatorUser().getUuid()));
            } else {
                ado.setCreatorUser(null);
            }
            ado.setCreatorComment(dto.getCreatorComment());
            if (dto.getAssigneeUser() != null) {
                ado.setAssigneeUser(DatabaseHelper.getUserDao().queryUuid(dto.getAssigneeUser().getUuid()));
            } else {
                ado.setAssigneeUser(null);
            }
            ado.setAssigneeReply(dto.getAssigneeReply());

        /*
        @TODO Event

        if (dto.getEvent() != null) {
            ado.setCaze(DatabaseHelper.getCaseDao().queryUuid(dto.getEvent().getUuid()));
        } else {
            ado.setCaze(null);
        }

        */
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fillInnerFromAdo(TaskDto dto, Task ado) {

        dto.setTaskContext(ado.getTaskContext());
        if (ado.getCaze() != null) {
            Case caze = DatabaseHelper.getCaseDao().queryForId(ado.getCaze().getId());
            dto.setCaze(CaseDtoHelper.toReferenceDto(caze));
        } else {
            dto.setCaze(null);
        }
        if (ado.getContact() != null) {
            Contact contact = DatabaseHelper.getContactDao().queryForId(ado.getContact().getId());
            dto.setContact(ContactDtoHelper.toReferenceDto(contact));
        } else {
            dto.setContact(null);
        }
        if (ado.getEvent() != null) {
            Event event = DatabaseHelper.getEventDao().queryForId(ado.getEvent().getId());
            dto.setEvent(EventDtoHelper.toReferenceDto(event));
        } else {
            dto.setEvent(null);
        }
        dto.setTaskType(ado.getTaskType());
        dto.setTaskStatus(ado.getTaskStatus());
        dto.setDueDate(ado.getDueDate());
        dto.setPriority(ado.getPriority());
        dto.setSuggestedStart(ado.getSuggestedStart());
        dto.setStatusChangeDate(ado.getStatusChangeDate());
        dto.setPerceivedStart(ado.getPerceivedStart());

        if (ado.getCreatorUser() != null) {
            User user = DatabaseHelper.getUserDao().queryForId(ado.getCreatorUser().getId());
            dto.setCreatorUser(UserDtoHelper.toReferenceDto(user));
        } else {
            dto.setCreatorUser(null);
        }
        dto.setCreatorComment(ado.getCreatorComment());
        if (ado.getAssigneeUser() != null) {
            User user = DatabaseHelper.getUserDao().queryForId(ado.getAssigneeUser().getId());
            dto.setAssigneeUser(UserDtoHelper.toReferenceDto(user));
        } else {
            dto.setAssigneeUser(null);
        }
        dto.setAssigneeReply(ado.getAssigneeReply());
    }
}
