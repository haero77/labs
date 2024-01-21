package com.labs.poi.car.excel.style.color;

import java.awt.Color;

public enum RgbColor {

	PASTEL_RED(new Color(255, 153, 153)),
	GREY(new Color(224, 224, 224)),
	WHITE(new Color(255, 255, 255));

	private final Color color;

	RgbColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
