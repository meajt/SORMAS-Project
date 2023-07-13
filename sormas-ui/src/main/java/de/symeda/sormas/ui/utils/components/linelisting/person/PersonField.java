package de.symeda.sormas.ui.utils.components.linelisting.person;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.ui.*;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.person.ApproximateAgeType;
import de.symeda.sormas.api.person.Sex;
import de.symeda.sormas.ui.configuration.validator.StringToNumberValidator;
import de.symeda.sormas.ui.utils.CssStyles;
import de.symeda.sormas.ui.utils.components.birthdate.BirthDateField;

public class PersonField extends CustomField<PersonFieldDto> {

	private final Binder<PersonFieldDto> binder = new Binder<>(PersonFieldDto.class);

	private final TextField firstname;
	private final TextField lastname;
	private final BirthDateField birthDate;
	private final ComboBox<Sex> sex;
	private final TextField approximateAge;
	private final ComboBox<ApproximateAgeType> approximateAgeType;

	public PersonField() {
		firstname = new TextField();
		lastname = new TextField();
		birthDate = new BirthDateField();
		sex = new ComboBox<>();
		approximateAge = new TextField();
		approximateAgeType = new ComboBox<>();
	}

	@Override
	protected Component initContent() {
		if (getValue() == null) {
			setValue(new PersonFieldDto());
		}

		HorizontalLayout layout = new HorizontalLayout();

		firstname.setId("firstName");
		binder.forField(firstname).asRequired().bind(PersonFieldDto.FIRST_NAME);

		lastname.setId("lastName");
		binder.forField(lastname).asRequired().bind(PersonFieldDto.LAST_NAME);

		birthDate.setId("birthDate");
		binder.forField(birthDate).bind(PersonFieldDto.BIRTH_DATE);
		birthDate.setVisible(false);
		approximateAge.setId("approximateAge");
		approximateAge.setWidth(80, Unit.PIXELS);
		binder.forField(approximateAge)
				.withValidator(new StringToNumberValidator("Must be number"))
				.bind(PersonFieldDto.AGE);

		approximateAgeType.setId("approximateAgeType");
		approximateAgeType.setWidth(100, Unit.PIXELS);
		binder.forField(approximateAgeType).bind(PersonFieldDto.APPROXIMATE_AGE_TYPE);
		approximateAgeType.setItems(ApproximateAgeType.values());

		sex.setId("sex");
		sex.setItems(Sex.values());
		sex.setWidth(100, Unit.PIXELS);
		binder.forField(sex).asRequired().bind(PersonFieldDto.SEX);

		layout.addComponents(firstname, lastname, sex, approximateAge, approximateAgeType);

		return layout;
	}

	@Override
	protected void doSetValue(PersonFieldDto personFieldDto) {
		binder.setBean(personFieldDto);
	}

	@Override
	public PersonFieldDto getValue() {
		return binder.getBean();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		firstname.setEnabled(enabled);
		lastname.setEnabled(enabled);
		birthDate.setEnabled(enabled);
		sex.setEnabled(enabled);
	}

	public BinderValidationStatus<PersonFieldDto> validate() {
		return binder.validate();
	}

	public void showCaptions() {
		firstname.setCaption(I18nProperties.getPrefixCaption(PersonFieldDto.I18N_PREFIX, PersonFieldDto.FIRST_NAME));
		firstname.removeStyleName(CssStyles.CAPTION_HIDDEN);
		lastname.setCaption(I18nProperties.getPrefixCaption(PersonFieldDto.I18N_PREFIX, PersonFieldDto.LAST_NAME));
		lastname.removeStyleName(CssStyles.CAPTION_HIDDEN);
		birthDate.setCaption(I18nProperties.getPrefixCaption(PersonFieldDto.I18N_PREFIX, PersonFieldDto.BIRTH_DATE));
		sex.setCaption(I18nProperties.getPrefixCaption(PersonFieldDto.I18N_PREFIX, PersonFieldDto.SEX));
		sex.removeStyleName(CssStyles.CAPTION_HIDDEN);
		approximateAge.setCaption(I18nProperties.getPrefixCaption(PersonFieldDto.I18N_PREFIX, PersonFieldDto.AGE));
		approximateAgeType.setCaption(I18nProperties.getPrefixCaption(PersonFieldDto.I18N_PREFIX, PersonFieldDto.APPROXIMATE_AGE_TYPE));
	}

	public void hideCaptions() {
		CssStyles.style(firstname, CssStyles.SOFT_REQUIRED, CssStyles.CAPTION_HIDDEN);
		CssStyles.style(lastname, CssStyles.SOFT_REQUIRED, CssStyles.CAPTION_HIDDEN);
		CssStyles.style(sex, CssStyles.SOFT_REQUIRED, CssStyles.CAPTION_HIDDEN);
	}
}
