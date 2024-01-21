package com.labs.poi.car.excel.file;

import com.labs.poi.car.excel.exception.ExcelProcessingException;
import com.labs.poi.car.excel.resource.ExcelRenderingResource;
import com.labs.poi.car.excel.resource.ExcelRenderingResourceFactory;
import com.labs.poi.car.utils.ReflectionUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class OneSheetExcelFile<T> implements ExcelFile {

	private static final int ROW_START_INDEX = 0;
	private static final int COLUMN_START_INDEX = 0;

	private final SXSSFWorkbook workbook;
	private final Sheet sheet;
	private final ExcelRenderingResource resource;

	/**
	 * @param data  Data to render excel.
	 * @param clazz Class type to be rendered. Only fields annotated with @ExcelColumn will be rendered.
	 */
	public OneSheetExcelFile(List<T> data, Class<T> clazz) {
		this.workbook = new SXSSFWorkbook();
		this.sheet = this.workbook.createSheet();
		this.resource = ExcelRenderingResourceFactory.create(clazz);
		renderExcel(data);
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
	}

	private void renderHeader() {
		Row row = sheet.createRow(ROW_START_INDEX);
		int columnIndex = COLUMN_START_INDEX;

		for (String dataFieldName : resource.getDataFieldNames()) {
			Cell cell = row.createCell(columnIndex++);
			cell.setCellStyle();
			cell.setCellValue(resource.getHeaderName(dataFieldName));
		}
	}

	private void renderContent(List<T> data) {
		int rowIndex = ROW_START_INDEX + 1;

		for (Object oneRowData : data) {
			Row row = sheet.createRow(rowIndex++);
			int columnIndex = COLUMN_START_INDEX;

			for (String fieldName : resource.getDataFieldNames()) {
				Cell cell = row.createCell(columnIndex++);

				try {
					Field field = ReflectionUtils.getField(oneRowData, fieldName);
					field.setAccessible(true);
					Object fieldValue = field.get(oneRowData);
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
