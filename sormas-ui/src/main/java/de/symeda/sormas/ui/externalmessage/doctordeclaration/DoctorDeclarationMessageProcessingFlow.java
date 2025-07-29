/*
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2022 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package de.symeda.sormas.ui.externalmessage.doctordeclaration;

import static de.symeda.sormas.ui.externalmessage.processing.ExternalMessageProcessingUIHelper.showCreateCaseWindow;
import static de.symeda.sormas.ui.externalmessage.processing.ExternalMessageProcessingUIHelper.showFormWithLabMessage;
import static de.symeda.sormas.ui.externalmessage.processing.ExternalMessageProcessingUIHelper.showMissingDiseaseConfiguration;
import static de.symeda.sormas.ui.externalmessage.processing.ExternalMessageProcessingUIHelper.showRelatedForwardedMessageConfirmation;
import static de.symeda.sormas.ui.utils.processing.ProcessingUiHelper.showPickOrCreateEntryWindow;
import static de.symeda.sormas.ui.utils.processing.ProcessingUiHelper.showPickOrCreatePersonWindow;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.caze.CaseDataDto;
import de.symeda.sormas.api.caze.CaseSelectionDto;
import de.symeda.sormas.api.contact.ContactDto;
import de.symeda.sormas.api.contact.SimilarContactDto;
import de.symeda.sormas.api.event.EventDto;
import de.symeda.sormas.api.event.EventIndexDto;
import de.symeda.sormas.api.event.EventParticipantDto;
import de.symeda.sormas.api.event.SimilarEventParticipantDto;
import de.symeda.sormas.api.externalmessage.ExternalMessageDto;
import de.symeda.sormas.api.externalmessage.processing.ExternalMessageMapper;
import de.symeda.sormas.api.externalmessage.processing.ExternalMessageProcessingFacade;
import de.symeda.sormas.api.externalmessage.processing.PickOrCreateEventResult;
import de.symeda.sormas.api.externalmessage.processing.doctordeclaration.AbstractDoctorDeclarationMessageProcessingFlow;
import de.symeda.sormas.api.feature.FeatureType;
import de.symeda.sormas.api.i18n.Captions;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Strings;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.person.notifier.NotifierDto;
import de.symeda.sormas.api.user.UserRight;
import de.symeda.sormas.api.utils.dataprocessing.EntitySelection;
import de.symeda.sormas.api.utils.dataprocessing.HandlerCallback;
import de.symeda.sormas.api.utils.dataprocessing.PickOrCreateEntryResult;
import de.symeda.sormas.ui.ControllerProvider;
import de.symeda.sormas.ui.UiUtil;
import de.symeda.sormas.ui.contact.ContactCreateForm;
import de.symeda.sormas.ui.events.EventDataForm;
import de.symeda.sormas.ui.events.EventParticipantCreateForm;
import de.symeda.sormas.ui.events.eventLink.EventSelectionField;
import de.symeda.sormas.ui.externalmessage.processing.EntrySelectionComponentForExternalMessage;
import de.symeda.sormas.ui.externalmessage.processing.ExternalMessageProcessingUIHelper;
import de.symeda.sormas.ui.utils.CommitDiscardWrapperComponent;
import de.symeda.sormas.ui.utils.VaadinUiUtil;
import de.symeda.sormas.ui.utils.processing.EntrySelectionField;

/**
 * Doctor declaration processing flow implemented with Vaadin dialogs/components for handling confirmation and object edit/save steps.
 * This class extends {@link AbstractDoctorDeclarationMessageProcessingFlow} and provides UI-specific implementations for handling
 * various steps in the doctor declaration message processing flow.
 */
public class DoctorDeclarationMessageProcessingFlow extends AbstractDoctorDeclarationMessageProcessingFlow {

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDeclarationMessageProcessingFlow.class);

	/**
	 * Constructor for initializing the processing flow with the required dependencies.
	 *
	 * @param externalMessage
	 *            The external message to be processed.
	 * @param mapper
	 *            The mapper for mapping external message data.
	 * @param processingFacade
	 *            The facade for handling external message processing operations.
	 */
	public DoctorDeclarationMessageProcessingFlow(
		ExternalMessageDto externalMessage,
		ExternalMessageMapper mapper,
		ExternalMessageProcessingFacade processingFacade) {
		super(externalMessage, UiUtil.getUser(), mapper, processingFacade);
		LOGGER.debug("Initialized DoctorDeclarationMessageProcessingFlow with externalMessage: {}", externalMessage);
	}

	/**
	 * Handles the case where the disease is missing in the external message.
	 *
	 * @return A {@link CompletionStage} that resolves to a boolean indicating whether the missing disease was handled successfully.
	 */
	@Override
	protected CompletionStage<Boolean> handleMissingDisease() {
		LOGGER.debug("Handling missing disease for externalMessage: {}", getExternalMessage());
		return showMissingDiseaseConfiguration();
	}

	/**
	 * Handles related forwarded messages for the external message.
	 *
	 * @return A {@link CompletionStage} that resolves to a boolean indicating whether the related forwarded messages were handled
	 *         successfully.
	 */
	@Override
	protected CompletionStage<Boolean> handleRelatedForwardedMessages() {
		LOGGER.debug("Handling related forwarded messages for externalMessage: {}", getExternalMessage());
		return showRelatedForwardedMessageConfirmation();
	}

	/**
	 * Displays a window to pick or create a person associated with the external message.
	 *
	 * @param person
	 *            The person to be picked or created.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handlePickOrCreatePerson(PersonDto person, HandlerCallback<EntitySelection<PersonDto>> callback) {
		LOGGER.debug("Handling pick or create person for person: {}", person);
		showPickOrCreatePersonWindow(person, callback);
	}

	/**
	 * Handles the selection or creation of an entry (case, contact, or event participant) based on the external message.
	 *
	 * @param similarCases
	 *            A list of similar cases.
	 * @param similarContacts
	 *            A list of similar contacts.
	 * @param similarEventParticipants
	 *            A list of similar event participants.
	 * @param externalMessage
	 *            The external message being processed.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handlePickOrCreateEntry(
		List<CaseSelectionDto> similarCases,
		List<SimilarContactDto> similarContacts,
		List<SimilarEventParticipantDto> similarEventParticipants,
		ExternalMessageDto externalMessage,
		HandlerCallback<PickOrCreateEntryResult> callback) {
		LOGGER.debug(
			"Handling pick or create entry for externalMessage: {}, similarCases: {}, similarContacts: {}, similarEventParticipants: {}",
			externalMessage,
			similarCases,
			similarContacts,
			similarEventParticipants);

		// Build options for selecting or creating an entry (case, contact, or event participant)
		EntrySelectionField.Options.Builder optionsBuilder = new EntrySelectionField.Options.Builder().addSelectCase(similarCases) // Add similar cases to the options
			.addSelectContact(similarContacts) // Add similar contacts to the options
			.addSelectEventParticipant(similarEventParticipants) // Add similar event participants to the options
			.addCreateEntry(EntrySelectionField.OptionType.CREATE_CASE, FeatureType.CASE_SURVEILANCE, UserRight.CASE_CREATE, UserRight.CASE_EDIT) // Option to create a new case
			.addCreateEntry(
				EntrySelectionField.OptionType.CREATE_CONTACT,
				FeatureType.CONTACT_TRACING,
				UserRight.CONTACT_CREATE,
				UserRight.CONTACT_EDIT) // Option to create a new contact
			.addCreateEntry(
				EntrySelectionField.OptionType.CREATE_EVENT_PARTICIPANT,
				FeatureType.EVENT_SURVEILLANCE,
				UserRight.EVENTPARTICIPANT_CREATE,
				UserRight.EVENTPARTICIPANT_EDIT); // Option to create a new event participant

		// If multiple options are available, show a selection window
		if (optionsBuilder.size() > 1) {
			showPickOrCreateEntryWindow(new EntrySelectionComponentForExternalMessage(externalMessage, optionsBuilder.build()), callback);
		} else {
			// If only one option is available, directly proceed with it
			callback.done(optionsBuilder.getSingleAvailableCreateResult());
		}
	}

	/**
	 * Handles the creation of a case based on the external message.
	 *
	 * @param caze
	 *            The case data to be created.
	 * @param person
	 *            The person associated with the case.
	 * @param externalMessage
	 *            The external message being processed.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handleCreateCase(CaseDataDto caze, PersonDto person, ExternalMessageDto externalMessage, HandlerCallback<CaseDataDto> callback) {
		LOGGER.debug("Handling create case for case: {}, person: {}, externalMessage: {}", caze, person, externalMessage);

		HandlerCallback<CaseDataDto> updateNotifierCallback = new HandlerCallback<CaseDataDto>() {

			@Override
			public void done(CaseDataDto result) {
				// If the external message contains notifier information, update the case with notifier details
				if (externalMessage.getNotifierRegistrationNumber() != null) {
					NotifierDto notifierDto = new NotifierDto();
					notifierDto.setRegistrationNumber(externalMessage.getNotifierRegistrationNumber());
					notifierDto.setFirstName(externalMessage.getNotifierFirstName());
					notifierDto.setLastName(externalMessage.getNotifierLastName());
					notifierDto.setAddress(externalMessage.getNotifierAddress());
					notifierDto.setPhone(externalMessage.getNotifierPhone());
					notifierDto.setEmail(externalMessage.getNotifierEmail());

					// Update the case with notifier details and complete the callback
					callback.done(getExternalMessageProcessingFacade().updateAndSetCaseNotifier(result.getUuid(), notifierDto));
					return;
				}
				// If no notifier information is present, complete the callback with the result
				callback.done(result);
			}

			@Override
			public void cancel() {
				// Handle cancellation of the operation
				callback.cancel();
			}
		};

		// Show the create case window with the provided data and callback
		showCreateCaseWindow(caze, person, externalMessage, getMapper(), updateNotifierCallback);
	}

	/**
	 * Handles the creation of a contact based on the external message.
	 *
	 * @param contact
	 *            The contact data to be created.
	 * @param person
	 *            The person associated with the contact.
	 * @param externalMessage
	 *            The external message being processed.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handleCreateContact(
		ContactDto contact,
		PersonDto person,
		ExternalMessageDto externalMessage,
		HandlerCallback<ContactDto> callback) {
		LOGGER.debug("Handling create contact for contact: {}, person: {}, externalMessage: {}", contact, person, externalMessage);

		Window window = VaadinUiUtil.createPopupWindow();

		CommitDiscardWrapperComponent<ContactCreateForm> contactCreateComponent =
			ControllerProvider.getContactController().getContactCreateComponent(null, false, null, true);

		contactCreateComponent.addCommitListener(() -> {
			ExternalMessageProcessingUIHelper.updateAddressAndSavePerson(
				FacadeProvider.getPersonFacade().getByUuid(contactCreateComponent.getWrappedComponent().getValue().getPerson().getUuid()),
				getMapper());

			callback.done(contactCreateComponent.getWrappedComponent().getValue());
		});
		contactCreateComponent.addDiscardListener(callback::cancel);

		contactCreateComponent.getWrappedComponent().setValue(contact);
		contactCreateComponent.getWrappedComponent().setPerson(person);

		showFormWithLabMessage(externalMessage, contactCreateComponent, window, I18nProperties.getString(Strings.headingCreateNewContact), false);
	}

	/**
	 * Displays a window to pick or create an event associated with the external message.
	 *
	 * @param externalMessage
	 *            The external message being processed.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handlePickOrCreateEvent(ExternalMessageDto externalMessage, HandlerCallback<PickOrCreateEventResult> callback) {
		LOGGER.debug("Handling pick or create event for externalMessage: {}", externalMessage);

		EventSelectionField eventSelect =
			new EventSelectionField(externalMessage.getDisease(), I18nProperties.getString(Strings.infoPickOrCreateEventForLabMessage), null);
		eventSelect.setWidth(1024, Sizeable.Unit.PIXELS);

		Window window = VaadinUiUtil.createPopupWindow();

		final CommitDiscardWrapperComponent<EventSelectionField> component = new CommitDiscardWrapperComponent<>(eventSelect);
		component.addCommitListener(() -> {
			PickOrCreateEventResult result = new PickOrCreateEventResult();
			EventIndexDto selectedEvent = eventSelect.getValue();
			if (selectedEvent != null) {
				result.setEvent(selectedEvent);
			} else {
				result.setNewEvent(true);
			}

			callback.done(result);
		});
		component.addDiscardListener(callback::cancel);
		Registration closeListener = window.addCloseListener((e -> component.discard()));
		component.addDoneListener(() -> {
			closeListener.remove();
			window.close();
		});

		eventSelect.setSelectionChangeCallback(commitAllowed -> component.getCommitButton().setEnabled(commitAllowed));

		window.setContent(component);
		window.setCaption(I18nProperties.getString(Strings.headingPickOrCreateEvent));
		UI.getCurrent().addWindow(window);
	}

	/**
	 * Handles the creation of an event based on the external message.
	 *
	 * @param event
	 *            The event data to be created.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handleCreateEvent(EventDto event, HandlerCallback<EventDto> callback) {
		LOGGER.debug("Handling create event for event: {}", event);

		EventDataForm eventCreateForm = new EventDataForm(true, false, true);
		eventCreateForm.setValue(event);
		eventCreateForm.getField(EventDto.DISEASE).setReadOnly(true);
		final CommitDiscardWrapperComponent<EventDataForm> editView =
			new CommitDiscardWrapperComponent<>(eventCreateForm, UiUtil.permitted(UserRight.EVENT_CREATE), eventCreateForm.getFieldGroup());

		Window window = VaadinUiUtil.createPopupWindow();
		editView.addCommitListener(() -> {
			if (!eventCreateForm.getFieldGroup().isModified()) {
				EventDto dto = eventCreateForm.getValue();

				EventDto savedEvent = FacadeProvider.getEventFacade().save(dto);
				Notification.show(I18nProperties.getString(Strings.messageEventCreated), Notification.Type.WARNING_MESSAGE);

				callback.done(savedEvent);
			}
		});
		editView.addDiscardListener(callback::cancel);
		Registration closeListener = window.addCloseListener((e -> editView.discard()));
		editView.addDoneListener(() -> {
			closeListener.remove();
			window.close();
		});

		window.setContent(editView);
		window.setCaption(I18nProperties.getString(Strings.headingCreateNewEvent));
		UI.getCurrent().addWindow(window);
	}

	/**
	 * Handles the creation of an event participant based on the external message.
	 *
	 * @param eventParticipant
	 *            The event participant data to be created.
	 * @param event
	 *            The event associated with the participant.
	 * @param externalMessage
	 *            The external message being processed.
	 * @param callback
	 *            The callback to handle the result of the operation.
	 */
	@Override
	protected void handleCreateEventParticipant(
		EventParticipantDto eventParticipant,
		EventDto event,
		ExternalMessageDto externalMessage,
		HandlerCallback<EventParticipantDto> callback) {
		LOGGER.debug(
			"Handling create event participant for eventParticipant: {}, event: {}, externalMessage: {}",
			eventParticipant,
			event,
			externalMessage);

		Window window = VaadinUiUtil.createPopupWindow();

		EventParticipantCreateForm createForm = new EventParticipantCreateForm(
			event.getEventLocation().getRegion() == null && event.getEventLocation().getDistrict() == null,
			eventParticipant.getPerson().getCreationDate() == null);
		createForm.setValue(eventParticipant);
		final CommitDiscardWrapperComponent<EventParticipantCreateForm> createComponent =
			new CommitDiscardWrapperComponent<>(createForm, UiUtil.permitted(UserRight.EVENTPARTICIPANT_CREATE), createForm.getFieldGroup());

		createComponent.addCommitListener(() -> {
			if (!createForm.getFieldGroup().isModified()) {
				final EventParticipantDto dto = createForm.getValue();

				FacadeProvider.getPersonFacade().save(dto.getPerson());
				EventParticipantDto savedDto = FacadeProvider.getEventParticipantFacade().save(dto);
				Notification.show(I18nProperties.getString(Strings.messageEventParticipantCreated), Notification.Type.ASSISTIVE_NOTIFICATION);

				callback.done(savedDto);
			}
		});
		createComponent.addDiscardListener(callback::cancel);

		showFormWithLabMessage(externalMessage, createComponent, window, I18nProperties.getString(Strings.headingCreateNewEventParticipant), false);
	}

	/**
	 * Confirms whether to pick an existing event participant.
	 *
	 * @return A {@link CompletionStage} that resolves to a boolean indicating whether the user chose to continue with the existing
	 *         participant.
	 */
	@Override
	protected CompletionStage<Boolean> confirmPickExistingEventParticipant() {
		LOGGER.debug("Confirming pick existing event participant for externalMessage: {}", getExternalMessage());

		CompletableFuture<Boolean> ret = new CompletableFuture<>();

		// Create a confirmation dialog with "Continue" and "Back" options
		CommitDiscardWrapperComponent<VerticalLayout> commitDiscardWrapperComponent =
			new CommitDiscardWrapperComponent<>(new VerticalLayout(new Label(I18nProperties.getString(Strings.infoEventParticipantAlreadyExisting))));
		commitDiscardWrapperComponent.getCommitButton().setCaption(I18nProperties.getCaption(Captions.actionContinue)); // Continue button
		commitDiscardWrapperComponent.getDiscardButton().setCaption(I18nProperties.getCaption(Captions.actionBack)); // Back button

		// Handle the user's choice
		commitDiscardWrapperComponent.addCommitListener(() -> ret.complete(true)); // User chose to continue
		commitDiscardWrapperComponent.addDiscardListener(() -> ret.complete(false)); // User chose to go back

		// Display the confirmation dialog
		VaadinUiUtil.showModalPopupWindow(commitDiscardWrapperComponent, I18nProperties.getCaption(Captions.info), true);

		return ret;
	}

	/**
	 * Notifies that corrections have been saved.
	 *
	 * @return A {@link CompletionStage} that resolves when the notification is complete.
	 */
	@Override
	protected CompletionStage<Void> notifyCorrectionsSaved() {
		LOGGER.debug("Notifying corrections saved for externalMessage: {}", getExternalMessage());
		return null;
	}
}
