package com.labs.poi.car.excel;

import com.labs.poi.car.ExcelColumn;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelRenderResource {

	private final Map<String, String> headerNames; // <FieldName, HeaderName>

	private ExcelRenderResource(Map<String, String> headerNames) {
		this.headerNames = headerNames;
	}

	public static ExcelRenderResource fromClass(Class<?> clazz) {
		return new ExcelRenderResource(generateHeaderNames(clazz));
	}

	private static Map<String, String> generateHeaderNames(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();

		return Arrays.stream(fields)
				.filter(field -> field.isAnnotationPresent(ExcelColumn.class))
				.collect(Collectors.toMap(
						Field::getName,
						field -> field.getAnnotation(ExcelColumn.class).headerName()
				));
	}

	public String getHeaderName(String fieldName) {
		return this.headerNames.getOrDefault(fieldName, "");
	}

}
