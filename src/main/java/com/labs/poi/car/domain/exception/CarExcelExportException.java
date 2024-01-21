package com.labs.poi.car.domain.exception;

public class CarExcelExportException extends CarException {

	private static final String MESSAGE = "Cannot export excel format from Car.";

	public CarExcelExportException() {
		super(MESSAGE);
	}

}
