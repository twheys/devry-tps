package edu.devry.cis470.tps.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Http404Exception extends RuntimeException {
	private static final long serialVersionUID = -3746774397403886932L;

	public Http404Exception() {
		super();
	}

	public Http404Exception(final String message) {
		super(message);
	}

	public Http404Exception(final String message, final Throwable cause) {
		super(message, cause);
	}

	public Http404Exception(final Throwable cause) {
		super(cause);
	}

}
