package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.ExcelColumn;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRenderingResourceFactory {

	private ExcelRenderingResourceFactory() {
	}

	public static ExcelRenderingResource create(Class<?> dataType, Workbook workbook) {
		List<Field> fields = Arrays.asList(dataType.getDeclaredFields());

		return new ExcelRenderingResource(
				generateHeaderNames(fields),
				extractFieldNames(fields),
				generateCellStyles(fields, workbook)
		);
	}

	private static Map<String, String> generateHeaderNames(List<Field> fields) {
		return fields.stream()
				.filter(fieldAnnotatedWithExcelColumn())
				.collect(Collectors.toMap(
						Field::getName,
						field -> field.getAnnotation(ExcelColumn.class).headerName()
				));
	}

	private static List<String> extractFieldNames(List<Field> fields) {
		return fields.stream()
				.filter(fieldAnnotatedWithExcelColumn())
				.map(Field::getName)
				.collect(Collectors.toList());
	}

	private static Map<CellKey, CellStyle> generateCellStyles(List<Field> fields) {
		List<Field> excelColumnExists = filterExcelColumnExists(fields);
		Map<CellKey, CellStyle> cellStyles = new HashMap<>();

		// 반복문 돌려서 작업 진행
	}

	private static List<Field> filterExcelColumnExists(List<Field> fields) {
		return fields.stream()
				.filter(fieldAnnotatedWithExcelColumn())
				.collect(Collectors.toList());
	}

	private static Predicate<Field> fieldAnnotatedWithExcelColumn() {
		return field -> field.isAnnotationPresent(ExcelColumn.class);
	}

}
