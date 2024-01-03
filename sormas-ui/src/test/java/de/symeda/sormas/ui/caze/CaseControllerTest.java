package de.symeda.sormas.ui.caze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;
import com.vaadin.v7.data.util.converter.ConverterFactory;

import de.symeda.sormas.api.caze.CaseDataDto;
import de.symeda.sormas.api.person.ApproximateAgeType;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.utils.DateHelper;
import de.symeda.sormas.ui.AbstractBeanTest;
import de.symeda.sormas.ui.SormasUI;
import de.symeda.sormas.ui.utils.SormasDefaultConverterFactory;

public class CaseControllerTest extends AbstractBeanTest {

	private SormasUI ui;

	@BeforeEach
	public void initUI() throws Exception {

/*		creator.createUser(
			null,
			null,
			null,
			"ad",
			"min",
			creator.getUserRoleReference(DefaultUserRole.ADMIN),
			creator.getUserRoleReference(DefaultUserRole.NATIONAL_USER));*/

		VaadinRequest request = Mockito.mock(VaadinServletRequest.class);
		when(request.getUserPrincipal()).thenReturn((Principal) () -> "admin");

		CurrentInstance.set(VaadinRequest.class, request);

		VaadinService service = Mockito.mock(VaadinService.class);
		CurrentInstance.set(VaadinService.class, service);

		VaadinSession session = Mockito.mock(VaadinSession.class);
		ConverterFactory converterFactory = new SormasDefaultConverterFactory();
		when(session.getConverterFactory()).thenReturn(converterFactory);
		when(session.getService()).thenReturn(service);
		CurrentInstance.set(VaadinSession.class, session);

		ui = new SormasUI();
		CurrentInstance.set(UI.class, ui);

		java.lang.reflect.Field pageField = UI.class.getDeclaredField("page");
		pageField.setAccessible(true);
		pageField.set(ui, Mockito.mock(Page.class));
	}

	@Test
	public void updatePersonRegistrationAge() {
		final CaseDataDto caseDataDto = new CaseDataDto();
		Date date = DateHelper.parseDate("2024-01-02", new SimpleDateFormat("yyyy-MM-dd"));
		caseDataDto.setReportDate(date);

		PersonDto personDto = new PersonDto();
		personDto.setBirthdateYYYY(2000);
		CaseController caseController = new CaseController();
		caseController.updatePersonRegistrationAge(caseDataDto, personDto);
		assertEquals(24, caseDataDto.getPersonAgeDuringRegistration());
		assertEquals(ApproximateAgeType.YEARS, caseDataDto.getPersonAgeTypeDuringRegistration());

		personDto.setBirthdateMM(2);
		caseController.updatePersonRegistrationAge(caseDataDto, personDto);
		assertEquals(287, caseDataDto.getPersonAgeDuringRegistration());
		assertEquals(ApproximateAgeType.MONTHS, caseDataDto.getPersonAgeTypeDuringRegistration());

		personDto.setApproximateAge(20);
		personDto.setApproximateAgeType(ApproximateAgeType.YEARS);
		caseController.updatePersonRegistrationAge(caseDataDto, personDto);
		assertEquals(20, caseDataDto.getPersonAgeDuringRegistration());
		assertEquals(ApproximateAgeType.YEARS, caseDataDto.getPersonAgeTypeDuringRegistration());
	}


//	@SuppressWarnings("unchecked")
//	@Test
//	public void testGetCaseCreateComponent() {
//
//		CaseController controller = new CaseController();
//		CommitDiscardWrapperComponent<CaseCreateForm> caseCreateComponent = controller.getCaseCreateComponent(null,
//				null, null, null);
//
//		// TODO add UI class and attach form, so everything works as expected
//		CaseCreateForm caseCreateForm = caseCreateComponent.getWrappedComponent();
//		((Field<String>) caseCreateForm.getField(CaseCreateForm.FIRST_NAME)).setValue("Steff");
//		((Field<String>) caseCreateForm.getField(CaseCreateForm.LAST_NAME)).setValue("Steffson");
//		((Field<Disease>) caseCreateForm.getField(CaseDataDto.DISEASE)).setValue(Disease.EVD);
//
//		caseCreateComponent.commit();
//	}
}
