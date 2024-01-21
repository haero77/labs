package com.labs.poi.car.excel.style;

import com.labs.poi.car.excel.style.color.RgbColor;

public class ExcelCellStyle {

	// 헤더 또는 바디 전부 쓰일 수 있음.
	private final RgbColor color;

	// align

	public ExcelCellStyle(RgbColor color) {
		this.color = color;
	}

}
