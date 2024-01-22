package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.ExcelCellStyle;
import com.labs.poi.car.excel.ExcelColumn;
import java.awt.Color;
import java.lang.reflect.Field;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class CellStyleFactory {

	private CellStyleFactory() {
	}

	public static CellStyle createHeaderStyle(Field field, Workbook workbook) {
		return create(workbook, getExcelColumn(field).headerStyle());
	}

	public static CellStyle createContentStyle(Field field, Workbook workbook) {
		return create(workbook, getExcelColumn(field).contentStyle());
	}

	private static CellStyle create(Workbook workbook, ExcelCellStyle excelCellStyle) {
		CellStyle style = workbook.createCellStyle();
		fillForegroundColor(style, excelCellStyle.foreGroundColor().getColor());

		// todo add align style

		return style;
	}

	private static void fillForegroundColor(CellStyle style, Color color) {
		style.setFillForegroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
	}

	private static ExcelColumn getExcelColumn(Field field) {
		return field.getAnnotation(ExcelColumn.class);
	}

}
