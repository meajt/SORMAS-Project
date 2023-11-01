package de.symeda.sormas.ui.caze.components.linelisting;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.caze.CaseDataDto;
import de.symeda.sormas.api.hospitalization.HospitalizationDto;
import de.symeda.sormas.api.hospitalization.RegistrationType;
import de.symeda.sormas.api.i18n.Captions;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Strings;
import de.symeda.sormas.api.i18n.Validations;
import de.symeda.sormas.api.infrastructure.community.CommunityReferenceDto;
import de.symeda.sormas.api.infrastructure.district.DistrictReferenceDto;
import de.symeda.sormas.api.infrastructure.facility.FacilityDto;
import de.symeda.sormas.api.infrastructure.facility.FacilityReferenceDto;
import de.symeda.sormas.api.infrastructure.facility.FacilityType;
import de.symeda.sormas.api.infrastructure.facility.FacilityTypeGroup;
import de.symeda.sormas.api.infrastructure.region.RegionReferenceDto;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.api.user.JurisdictionLevel;
import de.symeda.sormas.api.user.UserDto;
import de.symeda.sormas.api.utils.UtilDate;
import de.symeda.sormas.api.utils.ValidationRuntimeException;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.ui.UserProvider;
import de.symeda.sormas.ui.configuration.validator.StringToNumberValidator;
import de.symeda.sormas.ui.utils.*;
import de.symeda.sormas.ui.utils.components.linelisting.line.DeleteLineEvent;
import de.symeda.sormas.ui.utils.components.linelisting.line.LineLayout;
import de.symeda.sormas.ui.utils.components.linelisting.model.LineDto;
import de.symeda.sormas.ui.utils.components.linelisting.person.PersonField;
import de.symeda.sormas.ui.utils.components.linelisting.person.PersonFieldDto;
import de.symeda.sormas.ui.utils.components.linelisting.section.LineListingSection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HospitalLineListLayout extends CaseLineListSave {
    private final Window window;
    private final ComboBox<FacilityTypeGroup> typeGroup;
    private final ComboBox<FacilityType> type;
    private ComboBox<FacilityReferenceDto> facility;
    private TextField facilityDetails;
    List<HospitalLineListLayout.HospitalLineLayout> caseLines;
    private Consumer<LinkedList<LineDto<CaseDataDto>>> saveCallback;

    public HospitalLineListLayout(Window window) {
        this.window = window;
        LineListingSection sharedInformationComponent = new LineListingSection(Captions.lineListingSharedInformation);
        HorizontalLayout sharedInformationBar = new HorizontalLayout();
        sharedInformationBar.addStyleName(CssStyles.SPACING_SMALL);

        typeGroup = new ComboBox<>(I18nProperties.getCaption(Captions.Facility_typeGroup));
        typeGroup.setId("typeGroup");
        typeGroup.setWidth(200, Unit.PIXELS);
        typeGroup.setItems(FacilityTypeGroup.getAccomodationGroups());
        typeGroup.setRequiredIndicatorVisible(true);
        sharedInformationBar.addComponent(typeGroup);

        type = new ComboBox<>(I18nProperties.getPrefixCaption(FacilityDto.I18N_PREFIX, FacilityDto.TYPE));
        type.setId("type");
        type.setWidth(200, Unit.PIXELS);
        type.setRequiredIndicatorVisible(true);
        sharedInformationBar.addComponent(type);

        facility = new ComboBox<>(I18nProperties.getPrefixCaption(CaseDataDto.I18N_PREFIX, CaseDataDto.HEALTH_FACILITY));
        facility.setItemCaptionGenerator(item -> item.buildCaption());
        facility.setId("facility" );
        facility.setWidth(364, Unit.PIXELS);
        facility.setRequiredIndicatorVisible(true);
        sharedInformationBar.addComponent(facility);
        typeGroup.addValueChangeListener(e -> {
            type.setItems(typeGroup.getValue() != null ? FacilityType.getAccommodationTypes(typeGroup.getValue()) : new ArrayList<>());
            type.setValue(null);
        });
        type.addValueChangeListener(e -> {
            if (type.getValue() != null ) {
                updateFacilities(type.getValue());
            }
        });

        sharedInformationComponent.addComponent(sharedInformationBar);
        addComponent(sharedInformationComponent);

        caseLines = new ArrayList<>();
        LineListingSection lineComponent = new LineListingSection("List of new cases");
        HospitalLineLayout line = buildNewLine(lineComponent);
        caseLines.add(line);
        lineComponent.addComponent(line);
        lineComponent.setSpacing(false);
        addComponent(lineComponent);

        HorizontalLayout actionBar = new HorizontalLayout();
        Button addLine = ButtonHelper.createIconButton(Captions.lineListingAddLine, VaadinIcons.PLUS, e -> {
            HospitalLineLayout newLine = buildNewLine(lineComponent);
            caseLines.add(newLine);
            lineComponent.addComponent(newLine);

            if (caseLines.size() > 1) {
                caseLines.get(0).getDelete().setEnabled(true);
            }
        }, ValoTheme.BUTTON_PRIMARY);

        actionBar.addComponent(addLine);
        actionBar.setComponentAlignment(addLine, Alignment.MIDDLE_LEFT);

        addComponent(actionBar);
        HorizontalLayout buttonsPanel = new HorizontalLayout();
        buttonsPanel.setMargin(false);
        buttonsPanel.setSpacing(true);
        buttonsPanel.setWidth(100, Unit.PERCENTAGE);

        Button cancelButton = ButtonHelper.createButton(Captions.actionDiscard, event -> closeWindow());

        buttonsPanel.addComponent(cancelButton);
        buttonsPanel.setComponentAlignment(cancelButton, Alignment.BOTTOM_RIGHT);
        buttonsPanel.setExpandRatio(cancelButton, 1);

        Button saveButton = ButtonHelper.createButton(Captions.actionSave, event -> saveCallback.accept(getCaseLineDtos()), ValoTheme.BUTTON_PRIMARY);

        buttonsPanel.addComponent(saveButton);
        buttonsPanel.setComponentAlignment(saveButton, Alignment.BOTTOM_RIGHT);
        buttonsPanel.setExpandRatio(saveButton, 0);

        addComponent(buttonsPanel);
        setComponentAlignment(buttonsPanel, Alignment.BOTTOM_RIGHT);
        setUserHealthFacility();
    }

    private void updateFacilities(FacilityType facilityType) {
        List<FacilityReferenceDto> facilities = FacadeProvider.getFacilityFacade().getByType(facilityType, false);
        facility.setItems(facilities);
    }

    private LinkedList<LineDto<CaseDataDto>> getCaseLineDtos() {
        FacilityType facilityType = type.getValue();
        FacilityReferenceDto facilityReferenceDto = facility.getValue();
       return caseLines.stream()
                .map(line -> {
                    HospitalLineDto caseLineDto = line.getBean();
                    LineDto<CaseDataDto> result = getCaseDataDtoLineDto(caseLineDto);
                    result.getPerson().setMobileNo(caseLineDto.getMobileNo());
                    CaseDataDto caze = result.getEntity();
                    caze.setFacilityType(facilityType);
                    caze.setHealthFacility(facilityReferenceDto);
                    caze.setResponsibleWardNo(caseLineDto.getWardNoValue());
                    if (caseLineDto.getRegistrationType() != null) {
                        HospitalizationDto hospitalizationDto = HospitalizationDto.build();
                        hospitalizationDto.setRegistrationNo(caseLineDto.getRegistrationNo());
                        hospitalizationDto.setRegistrationType(caseLineDto.getRegistrationType());
                        hospitalizationDto.setAdmissionDate(UtilDate.from(caseLineDto.getDateOfVisit()));
                        if (caseLineDto.getRegistrationType() == RegistrationType.OPD) {
                            hospitalizationDto.setAdmittedToHealthFacility(YesNoUnknown.NO);
                        } else  {
                            hospitalizationDto.setAdmittedToHealthFacility(YesNoUnknown.YES);
                        }
                        caze.setHospitalization(hospitalizationDto);
                    }
                    return result;
                }).collect(Collectors.toCollection(LinkedList::new));

    }

    @Override
    public void validate() {
        boolean validationFailed = facility.getValue() == null;
        for (HospitalLineLayout line : caseLines) {
            if (line.hasErrors()) {
                validationFailed = true;
            }
        }
        if (validationFailed) {
            throw new ValidationRuntimeException(I18nProperties.getString(Strings.errorFieldValidationFailed));
        }
    }

    @Override
    public void closeWindow() {
        window.close();
    }

    private HospitalLineLayout buildNewLine(VerticalLayout lineComponent) {
       // setUserHealthFacility();
        HospitalLineLayout newLine = new HospitalLineLayout(caseLines.size());
        HospitalLineDto newLineDto = new HospitalLineDto();
        newLine.setBean(newLineDto);
        newLine.addDeleteLineListener(e -> {
            HospitalLineLayout selectedLine = (HospitalLineLayout) e.getComponent();
            lineComponent.removeComponent(selectedLine);
            caseLines.remove(selectedLine);
            caseLines.get(0).formatAsFirstLine();
            if (caseLines.size() > 1) {
                caseLines.get(0).getDelete().setEnabled(true);
            } else {
                caseLines.get(0).getDelete().setEnabled(false);
            }
        });
        return newLine;
    }

    private void setUserHealthFacility() {
        UserDto user = UserProvider.getCurrent().getUser();
        FacilityReferenceDto healthFacility = user.getHealthFacility();
        if (healthFacility != null) {
            String facilityId = healthFacility.getUuid();
            FacilityDto facilityDto = FacadeProvider.getFacilityFacade().getByUuid(facilityId);
            FacilityType facilityUserType = facilityDto.getType();
            FacilityTypeGroup facilityUserTypeGroup = facilityDto.getType().getFacilityTypeGroup();
            typeGroup.setValue(facilityUserTypeGroup);
            type.setValue(facilityUserType);
            facility.setValue(healthFacility);
        }
        if (user.getJurisdictionLevel() == JurisdictionLevel.HEALTH_FACILITY) {
            typeGroup.setEnabled(false);
            type.setEnabled(false);
            facility.setEnabled(false);
        }
    }

    public void setSaveCallback(Consumer<LinkedList<LineDto<CaseDataDto>>> saveCallback) {
        this.saveCallback = saveCallback;
    }

    class HospitalLineLayout extends LineLayout {
        private final Binder<HospitalLineDto> binder = new Binder<>(HospitalLineDto.class);

        private final DateField dateOfReport;
        private final ComboBox<RegionReferenceDto> region;
        private final ComboBox<DistrictReferenceDto> district;
        private final ComboBox<CommunityReferenceDto> community;
        private final TextField wardNo;
        private final PersonField person;
        private final DateField dateOfOnset;

        private final ComboBox<Disease> disease;
        private final TextField diseaseDetails;
        private final ComboBox<RegistrationType> registrationType;
        private final TextField registrationNo;
        private final TextField mobileNo;
        private final Button delete;
        private final DateField dateOfVisit;
        public HospitalLineLayout(int lineIndex) {
            addStyleName(CssStyles.SPACING_SMALL);
            setMargin(false);
            disease = new ComboBox<>();
            disease.setId("lineListingDisease");
            disease.setItems(Disease.values());
            diseaseDetails = new TextField();
            diseaseDetails.setId("lineListingDiseaseDetails");
            diseaseDetails.setVisible(false);

            region = new ComboBox<>();
            region.setId("lineListingRegion");

            district = new ComboBox<>();
            district.setId("lineListingDistrict");
            wardNo = new TextField();
            region.setItems(FacadeProvider.getRegionFacade().getAllActiveByServerCountry());
            region.addValueChangeListener(e -> {
                RegionReferenceDto regionDto = e.getValue();
                updateDistricts(regionDto);
            });
            district.addValueChangeListener(e -> {
                DistrictReferenceDto districtReferenceDto = e.getValue();
                updateCommunities(districtReferenceDto);
            });

            binder.forField(disease).asRequired().bind(LineListingLayout.CaseLineDto.DISEASE);
            binder.forField(diseaseDetails)
                    .asRequired(new FieldVisibleAndNotEmptyValidator<>(I18nProperties.getString(Strings.errorFieldValidationFailed)))
                    .bind(LineListingLayout.CaseLineDto.DISEASE_DETAILS);
            binder.forField(region).asRequired().bind(LineListingLayout.CaseLineDto.REGION);
            binder.forField(district).asRequired().bind(LineListingLayout.CaseLineDto.DISTRICT);

            dateOfReport = new DateField();
            dateOfReport.setValue(LocalDate.now());
            dateOfReport.setId("lineListingDateOfReport_" + lineIndex);
            dateOfReport.setWidth(100, Unit.PIXELS);
            binder.forField(dateOfReport).asRequired().bind(LineListingLayout.CaseLineDto.DATE_OF_REPORT);
            dateOfReport.setRangeEnd(LocalDate.now());
            community = new ComboBox<>();
            community.setItemCaptionGenerator(item -> item.buildCaption());
            community.setId("lineListingCommunity_" + lineIndex);
            community.addStyleName(CssStyles.SOFT_REQUIRED);
            binder.forField(community).bind(LineListingLayout.CaseLineDto.COMMUNITY);

            facilityDetails = new TextField();
            facilityDetails.setId("lineListingFacilityDetails_" + lineIndex);
            CssStyles.style(facilityDetails, CssStyles.SOFT_REQUIRED);
            facilityDetails.setVisible(false);
            //updateFacilityFields(facility, facilityDetails);
            binder.forField(facilityDetails).bind(LineListingLayout.CaseLineDto.FACILITY_DETAILS);

            person = new PersonField();
            person.setId("lineListingPerson_" + lineIndex);
            binder.forField(person).bind(LineListingLayout.CaseLineDto.PERSON);

            dateOfOnset = new DateField();
            dateOfOnset.setId("lineListingDateOfOnSet_" + lineIndex);
            dateOfOnset.setWidth(100, Unit.PIXELS);
            //dateOfOnset.addStyleName(CssStyles.CAPTION_FIXED_WIDTH_100);
            binder.forField(dateOfOnset).bind(LineListingLayout.CaseLineDto.DATE_OF_ONSET);
            delete = ButtonHelper
                    .createIconButtonWithCaption("delete_" + lineIndex, "", VaadinIcons.TRASH, event -> fireEvent(new DeleteLineEvent(this)));

            registrationType = new ComboBox<>();
            registrationType.setItems(RegistrationType.values());
            registrationNo = new TextField();
            mobileNo = new TextField();
            binder.forField(registrationType).bind(HospitalLineDto.REGISTRATION_TYPE);
            binder.forField(registrationNo).bind(HospitalLineDto.REGISTRATION_NO);
            binder.forField(wardNo)
                    .withValidator(new StringToNumberValidator(I18nProperties.getCaption(Validations.onlyIntegerNumbersAllowed)))
                    .bind(HospitalLineDto.WARD_NO);
            binder.forField(mobileNo)
                    .withValidator(str -> com.google.common.base.Strings.isNullOrEmpty(str) || Pattern.matches("[0-9]{10}", str), "Invalid mobile no.")
                    .bind(HospitalLineDto.MOBILE_NO);

            dateOfVisit = new DateField();
            dateOfVisit.setId("lineListingDateOfVisit_"+lineIndex);
            dateOfVisit.setWidth(150, Unit.PIXELS);
            dateOfVisit.addStyleName(CssStyles.CAPTION_OVERFLOW);
            binder.forField(dateOfVisit).bind(HospitalLineDto.DATE_OF_VISIT);

            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.addStyleName(CssStyles.SPACING_SMALL);
            verticalLayout.setMargin(false);
            addComponent(verticalLayout);

            HorizontalLayout topHorizontalLayout = new HorizontalLayout();
            topHorizontalLayout.addStyleName(CssStyles.SPACING_SMALL);
            topHorizontalLayout.setMargin(false);
            topHorizontalLayout.addComponent(dateOfReport);
            topHorizontalLayout.addComponents(disease, region, district, community, wardNo, dateOfOnset);

            HorizontalLayout bottomHorizontalLayout = new HorizontalLayout();
            bottomHorizontalLayout.addStyleName(CssStyles.SPACING_SMALL);
            bottomHorizontalLayout.setMargin(false);
            bottomHorizontalLayout.addComponents(registrationType, registrationNo, dateOfVisit);
            bottomHorizontalLayout.addComponents(person, mobileNo,  delete);
            bottomHorizontalLayout.setComponentAlignment(delete, Alignment.MIDDLE_LEFT);


            verticalLayout.addComponent(topHorizontalLayout);
            verticalLayout.addComponent(bottomHorizontalLayout);
            Label separator = new Label("<hr style=\"border-top:2px solid #eee;width:100em;\"/><br/>", ContentMode.HTML);
            separator.setWidth(100f, Sizeable.Unit.PERCENTAGE);
            verticalLayout.addComponent(separator);
            formatAsFirstLine();
            if (lineIndex == 0) {
                delete.setEnabled(false);
            }
        }

        private void updateDistricts(RegionReferenceDto regionDto) {
            FieldHelper.updateItems(district, regionDto != null ? FacadeProvider.getDistrictFacade().getAllActiveByRegion(regionDto.getUuid()) : null);
        }

        private void updateCommunities(DistrictReferenceDto districtDto) {
            FieldHelper.updateItems(community, districtDto != null ? FacadeProvider.getCommunityFacade().getAllActiveByDistrict(districtDto.getUuid()) : null);
        }

        private void formatAsFirstLine() {
            formatAsOtherLine();
            disease.setCaption(I18nProperties.getCaption(Captions.disease));
            dateOfReport.setCaption(I18nProperties.getPrefixCaption(CaseDataDto.I18N_PREFIX, CaseDataDto.REPORT_DATE));
            dateOfReport.removeStyleName(CssStyles.CAPTION_HIDDEN);
            region.setCaption(I18nProperties.getPrefixCaption(CaseDataDto.I18N_PREFIX, CaseDataDto.RESPONSIBLE_REGION));
            district.setCaption(I18nProperties.getPrefixCaption(CaseDataDto.I18N_PREFIX, CaseDataDto.RESPONSIBLE_DISTRICT));
            community.setCaption(I18nProperties.getPrefixCaption(CaseDataDto.I18N_PREFIX, CaseDataDto.RESPONSIBLE_COMMUNITY));
            wardNo.setCaption(I18nProperties.getPrefixCaption(CaseDataDto.I18N_PREFIX, CaseDataDto.WARD_NO));
            registrationNo.setCaption(I18nProperties.getPrefixCaption(HospitalizationDto.I18N_PREFIX, HospitalizationDto.REGISTRATION_NO));
            registrationType.setCaption(I18nProperties.getPrefixCaption(HospitalizationDto.I18N_PREFIX, HospitalizationDto.REGISTRATION_TYPE));
            mobileNo.setCaption(I18nProperties.getPrefixCaption(PersonDto.I18N_PREFIX, PersonDto.MOBILE_NO));
            person.showCaptions();
            dateOfOnset.setCaption(I18nProperties.getPrefixCaption(SymptomsDto.I18N_PREFIX, SymptomsDto.ONSET_DATE));
            dateOfOnset.setDescription(I18nProperties.getPrefixDescription(SymptomsDto.I18N_PREFIX, SymptomsDto.ONSET_DATE));
            dateOfVisit.setCaption(I18nProperties.getPrefixCaption(HospitalizationDto.I18N_PREFIX, HospitalizationDto.ADMISSION_DATE));
        }
        private void formatAsOtherLine() {
            CssStyles.style(dateOfReport, CssStyles.SOFT_REQUIRED, CssStyles.CAPTION_HIDDEN);
            CssStyles.style(facilityDetails, CssStyles.SOFT_REQUIRED, CssStyles.CAPTION_HIDDEN);
        }
        public void setBean(HospitalLineDto bean) {
            binder.setBean(bean);
        }
        public HospitalLineDto getBean() {
            return binder.getBean();
        }
        public Button getDelete() {
            return delete;
        }

        public boolean hasErrors() {
            BinderValidationStatus<PersonFieldDto> personValidationStatus = person.validate();
            BinderValidationStatus<HospitalLineDto> lineValidationStatus = binder.validate();
            return personValidationStatus.hasErrors() || lineValidationStatus.hasErrors();
        }
    }

    public static class HospitalLineDto extends LineListingLayout.CaseLineDto {
        public static final String REGISTRATION_NO = "registrationNo";
        public static final String REGISTRATION_TYPE = "registrationType";
        public static final String MOBILE_NO = "mobileNo";
        public static final String WARD_NO = "wardNo";
        public static final String DATE_OF_VISIT = "dateOfVisit";
        private String registrationNo;
        private RegistrationType registrationType;
        private String mobileNo;
        private String wardNo;
        private LocalDate dateOfVisit;

        public String getRegistrationNo() {
            return registrationNo;
        }

        public void setRegistrationNo(String registrationNo) {
            this.registrationNo = registrationNo;
        }

        public RegistrationType getRegistrationType() {
            return registrationType;
        }

        public void setRegistrationType(RegistrationType registrationType) {
            this.registrationType = registrationType;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getWardNo() {
            return wardNo;
        }

        public void setWardNo(String wardNo) {
            this.wardNo = wardNo;
        }

        public Integer getWardNoValue() {
            if (!com.google.common.base.Strings.isNullOrEmpty(wardNo)) {
                return Integer.parseInt(wardNo);
            }
            return null;
        }

        public LocalDate getDateOfVisit() {
            return dateOfVisit;
        }

        public void setDateOfVisit(LocalDate dateOfVisit) {
            this.dateOfVisit = dateOfVisit;
        }
    }
}
