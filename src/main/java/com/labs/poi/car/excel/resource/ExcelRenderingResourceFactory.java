package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.ExcelColumn;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExcelRenderingResourceFactory {

	private ExcelRenderingResourceFactory() {
	}

	public static ExcelRenderingResource create(Class<?> clazz) {
		List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
		return new ExcelRenderingResource(generateHeaderNames(fields), extractFieldNames(fields));
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

	private static Predicate<Field> fieldAnnotatedWithExcelColumn() {
		return field -> field.isAnnotationPresent(ExcelColumn.class);
	}

}
