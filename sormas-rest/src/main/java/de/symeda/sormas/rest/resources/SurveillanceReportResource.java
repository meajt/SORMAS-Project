package de.symeda.sormas.rest.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.caze.surveillancereport.SurveillanceReportDto;
import de.symeda.sormas.rest.resources.EntityDtoResource;

@Path("/surveillancereports")
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class SurveillanceReportResource extends EntityDtoResource {

	@POST
	@Path("/query/cases")
	public List<SurveillanceReportDto> getByCaseUuids(List<String> uuids) {
		return FacadeProvider.getSurveillanceReportFacade().getByCaseUuids(uuids);
	}

	@POST
	@Path("/push")
	public Response postCaseReports(@Valid List<SurveillanceReportDto> dtos) {
		return savePushedDtosNonAtomic(dtos, FacadeProvider.getSurveillanceReportFacade()::saveSurveillanceReport);
	}
}
