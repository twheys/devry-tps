package edu.devry.cis470.tps.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Http500Exception extends RuntimeException {
	private static final long serialVersionUID = -3746774397403886932L;

	public Http500Exception() {
		super();
	}

	public Http500Exception(final String message) {
		super(message);
	}

	public Http500Exception(final String message, final Throwable cause) {
		super(message, cause);
	}

	public Http500Exception(final Throwable cause) {
		super(cause);
	}

}
