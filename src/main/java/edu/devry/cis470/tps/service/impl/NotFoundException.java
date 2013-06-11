package edu.devry.cis470.tps.service.impl;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 2005459929478910009L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(final Throwable cause) {
		super(cause);
	}

}
