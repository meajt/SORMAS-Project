package de.symeda.sormas.backend.sample.ncd;

import de.symeda.sormas.api.sample.ncd.RftSampleDto;
import de.symeda.sormas.backend.util.DtoHelper;
import de.symeda.sormas.backend.util.ModelConstants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

@Stateless(name = "RftSampleFacade")
public class RftSampleFacadeEjb implements RftSampleFacade {

    @PersistenceContext(unitName = ModelConstants.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public RftSampleDto saveRftSample(RftSampleDto dto) {
        return null;
    }

    @Override
    public RftSampleDto getRftSampleBySampleId(String sampleId) {
        return null;
    }

    @Override
    public RftSampleDto getRftSampleByUuId(String uuId) {
        return null;
    }

    public RftSample fillOrBuildEntity(RftSampleDto source, RftSample target, boolean checkChangeDate) {
        if (source == null)
            return null;
        target = DtoHelper.fillOrBuildEntity(source, target, RftSample::new, checkChangeDate);
        target.setUrea(source.getUrea());
        target.setCreatinine(source.getCreatinine());
        target.setSodium(source.getSodium());
        target.setPotassium(source.getPotassium());
        return target;
    }

    public static RftSampleDto toDto(RftSample source, RftSampleDto target) {
        if (source == null)
            return null;
        if(target == null)
            target = new RftSampleDto();
        DtoHelper.fillDto(target, source);
        target.setUrea(source.getUrea());
        target.setCreatinine(source.getCreatinine());
        target.setSodium(source.getSodium());
        target.setPotassium(source.getPotassium());
        return target;
    }
}
