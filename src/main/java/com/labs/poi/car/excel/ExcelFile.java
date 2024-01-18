package com.labs.poi.car.excel;

import java.util.List;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelFile<T> {

	private static final SpreadsheetVersion SUPPLY_EXCEL_VERSION = SpreadsheetVersion.EXCEL2007;
	private static final int ROW_START_INDEX = 0;
	private static final int COLUMN_START_INDEX = 0;

	private final SXSSFWorkbook workbook;
	private final Sheet sheet;
	private final ExcelRenderResource resource;

	private ExcelFile(SXSSFWorkbook workbook, Sheet sheet, ExcelRenderResource resource) {
		this.workbook = workbook;
		this.sheet = sheet;
		this.resource = resource;
	}

	public static <T> ExcelFile<T> of(List<T> data, Class<T> clazz) {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		ExcelRenderResource resource = ExcelRenderResource.fromClass(clazz);

		return new ExcelFile<>(workbook, sheet, resource);
	}

}
