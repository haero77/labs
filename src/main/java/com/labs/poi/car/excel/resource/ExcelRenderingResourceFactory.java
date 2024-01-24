package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.core.ExcelColumn;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRenderingResourceFactory {

	private ExcelRenderingResourceFactory() {
	}

	public static ExcelRenderingResource create(Class<?> dataType, Workbook workbook) {
		List<Field> fields = Arrays.asList(dataType.getDeclaredFields());

		return new ExcelRenderingResource(
				extractFieldNames(fields),
				generateHeaderNames(fields),
				CellStyleHolder.of(fields, workbook)
		);
	}

	private static Map<String, String> generateHeaderNames(List<Field> fields) {
		return ExcelColumnFieldFilter.filter(fields)
				.stream()
				.collect(Collectors.toMap(
						Field::getName,
						field -> field.getAnnotation(ExcelColumn.class).headerName()
				));
	}

	private static List<String> extractFieldNames(List<Field> fields) {
		return ExcelColumnFieldFilter.filter(fields)
				.stream()
				.map(Field::getName)
				.collect(Collectors.toList());
	}

}
