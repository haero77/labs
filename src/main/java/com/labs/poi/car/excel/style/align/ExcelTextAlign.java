package com.labs.poi.car.excel.style.align;

 import org.apache.poi.ss.usermodel.HorizontalAlignment;

public enum ExcelTextAlign {

	CENTER(HorizontalAlignment.CENTER),
	RIGHT(HorizontalAlignment.RIGHT);

	private final HorizontalAlignment alignment;

	ExcelTextAlign(HorizontalAlignment alignment) {
		this.alignment = alignment;
	}

	public HorizontalAlignment getAlignment() {
		return alignment;
	}

}
