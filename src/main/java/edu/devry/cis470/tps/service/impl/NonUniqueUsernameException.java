package edu.devry.cis470.tps.service.impl;

public class NonUniqueUsernameException extends Exception {
	private static final long serialVersionUID = -1548081368473801873L;

	public NonUniqueUsernameException() {
		super();
	}

	public NonUniqueUsernameException(final String message) {
		super(message);
	}

	public NonUniqueUsernameException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	public NonUniqueUsernameException(final Throwable cause) {
		super(cause);
	}

}
