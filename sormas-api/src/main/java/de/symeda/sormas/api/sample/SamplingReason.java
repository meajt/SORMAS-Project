/*
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2021 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
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

package de.symeda.sormas.api.sample;

import de.symeda.sormas.api.CountryHelper;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.utils.HideForCountries;
import de.symeda.sormas.api.utils.HideForCountriesExcept;

public enum SamplingReason {

	PRESENCE_OF_SYMPTOMS,
	OUTBREAK,
	@HideForCountries(countries = CountryHelper.COUNTRY_CODE_NEPAL)
	SCREENING,
	@HideForCountries(countries = CountryHelper.COUNTRY_CODE_NEPAL)
	PROFESSIONAL_REASON,
	QUARANTINE_REGULATIONS,
	CONTACT_TO_CASE,
	@HideForCountries(countries = CountryHelper.COUNTRY_CODE_NEPAL)
	PLANNING_TO_TRAVEL,
	@HideForCountries(countries = CountryHelper.COUNTRY_CODE_NEPAL)
	RETURNING_TRAVELER,
	@HideForCountries(countries = CountryHelper.COUNTRY_CODE_NEPAL)
	PERSONAL_REASON,
	@HideForCountries(countries = CountryHelper.COUNTRY_CODE_NEPAL)
	MOVING_RETURNING_RETIREMENT_HOME,
	@HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
	SWISS_COVID_APP_NOTIFICATION,
	@HideForCountriesExcept(countries = CountryHelper.COUNTRY_CODE_SWITZERLAND)
	QUARANTINE_END,
	UNKNOWN,
	OTHER_REASON;

	@Override
	public String toString() {
		return I18nProperties.getEnumCaption(this);
	}
}
