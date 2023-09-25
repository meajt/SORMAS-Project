package de.symeda.sormas.ui.caze.components.linelisting;

import com.vaadin.ui.VerticalLayout;
import de.symeda.sormas.api.caze.CaseDataDto;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.utils.UtilDate;
import de.symeda.sormas.ui.UserProvider;
import de.symeda.sormas.ui.utils.components.linelisting.model.LineDto;

import java.util.LinkedList;
import java.util.function.Consumer;

public abstract class CaseLineListSave  extends VerticalLayout {

    protected  LineDto<CaseDataDto> getCaseDataDtoLineDto(LineListingLayout.CaseLineDto caseLineDto) {
        LineDto<CaseDataDto> result = new LineDto<>();
        CaseDataDto caze = CaseDataDto.build(PersonDto.build().toReference(), caseLineDto.getDisease());

        caze.setDiseaseDetails(caseLineDto.getDiseaseDetails());
        caze.setResponsibleRegion(caseLineDto.getRegion());
        caze.setResponsibleDistrict(caseLineDto.getDistrict());
        caze.setReportDate(UtilDate.from(caseLineDto.getDateOfReport()));
        caze.setEpidNumber(caseLineDto.getEpidNumber());
        caze.setResponsibleCommunity(caseLineDto.getCommunity());
        caze.setFacilityType(caseLineDto.getFacilityType());
        caze.setHealthFacility(caseLineDto.getFacility());
        caze.setHealthFacilityDetails(caseLineDto.getFacilityDetails());
        if (caseLineDto.getDateOfOnset() != null) {
            caze.getSymptoms().setOnsetDate(UtilDate.from(caseLineDto.getDateOfOnset()));
        }
        if (UserProvider.getCurrent() != null) {
            caze.setReportingUser(UserProvider.getCurrent().getUserReference());
        }
        result.setEntity(caze);

        final PersonDto person = PersonDto.build();
        person.setFirstName(caseLineDto.getPerson().getFirstName());
        person.setLastName(caseLineDto.getPerson().getLastName());
        person.setApproximateAge(caseLineDto.getPerson().getApproximateAgeValue());
        person.setApproximateAgeType(caseLineDto.getPerson().getApproximateAgeType());
        if (caseLineDto.getPerson().getBirthDate() != null) {
            person.setBirthdateYYYY(caseLineDto.getPerson().getBirthDate().getDateOfBirthYYYY());
            person.setBirthdateMM(caseLineDto.getPerson().getBirthDate().getDateOfBirthMM());
            person.setBirthdateDD(caseLineDto.getPerson().getBirthDate().getDateOfBirthDD());
        }
        person.setSex(caseLineDto.getPerson().getSex());
        result.setPerson(person);

        return result;
    }

    abstract public void validate();

    abstract public void closeWindow();
    abstract public void setSaveCallback(Consumer<LinkedList<LineDto<CaseDataDto>>> saveCallback);
}
