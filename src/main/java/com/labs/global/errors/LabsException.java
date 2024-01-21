package com.labs.global.errors;

public class LabsException extends RuntimeException {

	protected LabsException(String message) {
		super(message);
	}

	protected LabsException(String message, Throwable cause) {
		super(message, cause);
	}

}
