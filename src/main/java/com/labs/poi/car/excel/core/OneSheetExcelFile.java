package com.labs.poi.car.excel.core;

import com.labs.poi.car.excel.exception.ExcelProcessingException;
import com.labs.poi.car.excel.resource.CellKey;
import com.labs.poi.car.excel.resource.ExcelRenderingResource;
import com.labs.poi.car.excel.resource.ExcelRenderingResourceFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class OneSheetExcelFile<T> implements ExcelWritable {

	private static int rowStartIndex = 0;
	private static int columnStartIndex = 0;

	private final SXSSFWorkbook workbook;
	private final SXSSFSheet sheet;
	private final ExcelRenderingResource resource;

	/**
	 * @param dataType Class type to be rendered. Only fields annotated with @ExcelColumn will be rendered.
	 * @param data     Data to render excel.
	 */
	public OneSheetExcelFile(Class<T> dataType, List<T> data) {
		this.workbook = new SXSSFWorkbook();
		this.sheet = this.workbook.createSheet();
		this.sheet.trackAllColumnsForAutoSizing();
		this.resource = ExcelRenderingResourceFactory.create(dataType, this.workbook);
		renderExcel(data);
	}

	public OneSheetExcelFile(SXSSFWorkbook workbook, SXSSFSheet sheet, ExcelRenderingResource resource, List<T> data) {
		this.workbook = workbook;
		this.sheet = sheet;
		this.resource = resource;
		renderExcel(data);
	}

	public static OneSheetExcelFile<?> of(Class<?> dataType, List<?> data) {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = workbook.createSheet();
		sheet.trackAllColumnsForAutoSizing();
		ExcelRenderingResource resource = ExcelRenderingResourceFactory.create(dataType, workbook);
		return new OneSheetExcelFile<>(workbook, sheet, resource, data);
	}

	public static OneSheetExcelFile<?> withAdditionalOrderingColumn(Class<?> dataType, List<?> data) {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = workbook.createSheet();
		sheet.trackAllColumnsForAutoSizing();

		addOrderingAtFirstColumn(data, sheet);

		ExcelRenderingResource resource = ExcelRenderingResourceFactory.create(dataType, workbook);
		return new OneSheetExcelFile<>(workbook, sheet, resource, data);
	}

	private static void addOrderingAtFirstColumn(List<?> data, SXSSFSheet sheet) {
		Row firstRow = sheet.createRow(0);
		Cell firstCell = firstRow.createCell(0);
		firstCell.setCellValue("번혼");

		for (int i = 1; i <= data.size(); i++) {
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(i);
		}
		columnStartIndex++;
	}

	@Override
	public void write(OutputStream stream) throws IOException {
		workbook.write(stream);
		workbook.close();
		workbook.dispose();
		stream.close();
	}

	private void renderExcel(List<T> data) {
		renderHeader();

		if (data.isEmpty()) {
			return;
		}

		renderContent(data);

		// auto sizing column
		for (int columnIndex = 0; columnIndex < data.size(); columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	private void renderHeader() {
		Row row = sheet.createRow(rowStartIndex);
		int columnIndex = columnStartIndex;

		for (String dataFieldName : resource.getDataFieldNames()) {
			Cell cell = row.createCell(columnIndex++);
			cell.setCellStyle(resource.getCellStyle(new CellKey(dataFieldName, ExcelRenderingLocation.HEADER)));
			cell.setCellValue(resource.getHeaderName(dataFieldName));
		}
	}

	private void renderContent(List<T> data) {
		int rowIndex = rowStartIndex + 1;

		for (Object oneRowData : data) {
			Row row = sheet.createRow(rowIndex++);
			int columnIndex = columnStartIndex;

			for (String fieldName : resource.getDataFieldNames()) {
				Cell cell = row.createCell(columnIndex++);

				try {
					Field field = ReflectionUtils.getField(oneRowData, fieldName);
					field.setAccessible(true);
					Object fieldValue = field.get(oneRowData);
					cell.setCellStyle(resource.getCellStyle(new CellKey(fieldName, ExcelRenderingLocation.CONTENT)));
					renderCellValue(cell, fieldValue);
				} catch (Exception e) {
					throw new ExcelProcessingException(e.getMessage(), e);
				}
			}
		}
	}

	private void renderCellValue(Cell cell, Object cellValue) {
		if (cellValue instanceof Number) {
			cell.setCellValue(((Number) cellValue).doubleValue());
			return;
		}
		cell.setCellValue(cellValue == null ? "" : cellValue.toString());
	}

}
