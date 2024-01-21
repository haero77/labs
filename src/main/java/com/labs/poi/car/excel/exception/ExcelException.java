package com.labs.poi.car.excel.exception;

public class ExcelException extends RuntimeException {

	public ExcelException() {
	}

	public ExcelException(String message) {
		super(message);
	}

	protected ExcelException(String message, Throwable cause) {
		super(message, cause);
	}

}
