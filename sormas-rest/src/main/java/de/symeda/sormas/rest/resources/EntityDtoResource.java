package de.symeda.sormas.rest.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Providers;

import de.symeda.sormas.rest.TransactionWrapper;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.symeda.sormas.api.PushResponse;

public abstract class EntityDtoResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@EJB
	private TransactionWrapper transactionWrapper;

	@Context
	private Providers providers;

	protected <T> Response savePushedDtosNonAtomic(List<T> dtos, UnaryOperator<T> saveEntityDto) {
		if (dtos == null || dtos.isEmpty()) {
			return Response.status(HttpStatus.SC_OK).build();
		}

		List<PushResponse> results = new ArrayList<>(dtos.size());

		for (T dto : dtos) {
			try {
				transactionWrapper.execute(saveEntityDto, dto);
				// save a few bytes by setting only the status code
				results.add(new PushResponse(HttpStatus.SC_OK, null));
			} catch (Exception e) {
				results.add(getPushResultError(e));
			}
		}

		if (dtos.size() == 1) {
			return Response.status(results.get(0).getStatusCode()).entity(results).build();
		} else {
			return Response.status(HttpStatus.SC_MULTI_STATUS).entity(results).build();
		}
	}

	private PushResponse getPushResultError(Exception e) {

		logger.warn("{}", e.getMessage());

		final ExceptionMapper<Exception> exceptionMapper = (ExceptionMapper<Exception>) providers.getExceptionMapper(e.getClass());
		if (exceptionMapper != null) {
			try (Response response = exceptionMapper.toResponse(e)) {
				return new PushResponse(response.getStatus(), response.getEntity());
			}
		}
		return new PushResponse(HttpStatus.SC_UNPROCESSABLE_ENTITY, "The entity could not be processed.");
	}

}
