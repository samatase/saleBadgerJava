package app.saleBadger.webexception;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.saleBadger.validator.ErrorValidationMapper;

public class ForbiddenException extends WebApplicationException {
	private static final long serialVersionUID = 1L;

	public ForbiddenException(List<String> messages) {
		super(Response.status(Response.Status.FORBIDDEN)
				.entity(getValidationList(messages))
				.type(MediaType.APPLICATION_JSON).build());
	}

	public static ErrorValidationMapper getValidationList(List<String> errorMessages) {
		ErrorValidationMapper validationError = new ErrorValidationMapper();
		for (String message : errorMessages) {
			validationError.addError(message,
					Response.Status.FORBIDDEN.getStatusCode());
		}

		return validationError;
	}
}
