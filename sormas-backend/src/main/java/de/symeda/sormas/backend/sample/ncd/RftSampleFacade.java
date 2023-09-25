package de.symeda.sormas.backend.sample.ncd;

import de.symeda.sormas.api.sample.ncd.RftSampleDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface RftSampleFacade {
    RftSampleDto saveRftSample(@Valid RftSampleDto dto);

    RftSampleDto getRftSampleBySampleId(String sampleId);

    RftSampleDto getRftSampleByUuId(String uuId);
    RftSample fillOrBuildEntity(@NotNull RftSampleDto source, RftSample target, boolean checkChangeDate);
}
