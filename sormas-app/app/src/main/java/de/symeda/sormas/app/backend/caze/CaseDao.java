package de.symeda.sormas.app.backend.caze;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Date;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.app.backend.common.AbstractAdoDao;
import de.symeda.sormas.app.backend.common.DaoException;
import de.symeda.sormas.app.backend.common.DatabaseHelper;
import de.symeda.sormas.app.backend.config.ConfigProvider;
import de.symeda.sormas.app.backend.epidata.EpiData;
import de.symeda.sormas.app.backend.epidata.EpiDataBurial;
import de.symeda.sormas.app.backend.epidata.EpiDataGathering;
import de.symeda.sormas.app.backend.epidata.EpiDataTravel;
import de.symeda.sormas.app.backend.hospitalization.Hospitalization;
import de.symeda.sormas.app.backend.hospitalization.PreviousHospitalization;
import de.symeda.sormas.app.backend.person.Person;
import de.symeda.sormas.app.backend.symptoms.Symptoms;
import de.symeda.sormas.app.backend.user.User;
import de.symeda.sormas.app.util.DataUtils;

public class CaseDao extends AbstractAdoDao<Case> {

    private static final Log.Level LOG_LEVEL = Log.Level.DEBUG;
    private static final Logger logger = LoggerFactory.getLogger(RuntimeExceptionDao.class);

    public CaseDao(Dao<Case,Long> innerDao) throws SQLException {
        super(innerDao);
    }

    @Override
    public String getTableName() {
        return Case.TABLE_NAME;
    }

    @Override
    public boolean saveUnmodified(Case caze) throws DaoException {
        if (caze.getIllLocation() != null) {
            DatabaseHelper.getLocationDao().saveUnmodified(caze.getIllLocation());
        }
        if (caze.getSymptoms() != null) {
            DatabaseHelper.getSymptomsDao().saveUnmodified(caze.getSymptoms());
        }
        if (caze.getHospitalization() != null) {
            DatabaseHelper.getHospitalizationDao().saveUnmodified(caze.getHospitalization());
        }
        if (caze.getEpiData() != null) {
            DatabaseHelper.getEpiDataDao().saveUnmodified(caze.getEpiData());
        }

        return super.saveUnmodified(caze);
    }

    @Override
    public Date getLatestChangeDate() throws DaoException, SQLException {
        Date cazeDate = super.getLatestChangeDate();
        if (cazeDate == null) {
            return null;
        }
        Date symptomsDate = DatabaseHelper.getSymptomsDao().getLatestChangeDate();
        if (symptomsDate != null && symptomsDate.after(cazeDate)) {
            cazeDate = symptomsDate;
        }
        Date hospitalizationDate = DatabaseHelper.getHospitalizationDao().getLatestChangeDate();
        if (hospitalizationDate != null && hospitalizationDate.after(cazeDate)) {
            cazeDate = hospitalizationDate;
        }
        Date epiDataDate = DatabaseHelper.getEpiDataDao().getLatestChangeDate();
        if (epiDataDate != null && epiDataDate.after(cazeDate)) {
            cazeDate = epiDataDate;
        }

        return cazeDate;
    }

    // TODO #69 create some date filter for finding the right case (this is implemented in CaseService.java too)
    public Case getByPersonAndDisease(Person person, Disease disease) throws SQLException {
        QueryBuilder builder = queryBuilder();
        Where where = builder.where();
        where.and(
                where.eq(Case.PERSON+"_id", person),
                where.eq(Case.DISEASE, disease)
        );

        return (Case) builder.queryForFirst();
    }

    public static Case createCase(Person person) throws InstantiationException, IllegalAccessException {
        Case caze = DataUtils.createNew(Case.class);
        caze.setPerson(person);

        // Symptoms
        caze.setSymptoms(DataUtils.createNew(Symptoms.class));

        // Hospitalization
        caze.setHospitalization(DataUtils.createNew(Hospitalization.class));

        // Epi Data
        caze.setEpiData(DataUtils.createNew(EpiData.class));

        // Location
        User currentUser = ConfigProvider.getUser();
        caze.setRegion(currentUser.getRegion());
        caze.setDistrict(currentUser.getDistrict());
        caze.setHealthFacility(currentUser.getHealthFacility());
        if (caze.getHealthFacility() != null) {
            caze.setCommunity(caze.getHealthFacility().getCommunity());
        }

        return caze;
    }
}
