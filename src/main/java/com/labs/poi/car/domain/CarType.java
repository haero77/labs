package com.labs.poi.car.domain;

public enum CarType implements Describable{

	BIG("대형"),
	SMALL("소형");

	private final String description;

	CarType(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
