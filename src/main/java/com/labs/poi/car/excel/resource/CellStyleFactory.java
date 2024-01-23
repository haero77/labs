package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.ExcelCellStyle;
import com.labs.poi.car.excel.ExcelColumn;
import com.labs.poi.car.excel.style.align.ExcelTextAlign;
import com.labs.poi.car.excel.style.color.RgbColor;
import java.lang.reflect.Field;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
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
		fillForegroundColor(style, excelCellStyle.foreGroundColor());
		setAlignment(style, excelCellStyle.align());
		return style;
	}

	private static void setAlignment(CellStyle style, ExcelTextAlign align) {
		style.setAlignment(align.getAlignment());
	}

	private static void fillForegroundColor(CellStyle style, RgbColor rgbColor) {
		style.setFillForegroundColor(new XSSFColor(rgbColor.getColor(), new DefaultIndexedColorMap()));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}

	private static ExcelColumn getExcelColumn(Field field) {
		return field.getAnnotation(ExcelColumn.class);
	}

}
