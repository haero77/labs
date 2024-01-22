package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.ExcelColumn;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelColumnFieldFilter {

	private ExcelColumnFieldFilter() {
	}

	public static List<Field> filter(List<Field> fields) {
		return fields.stream()
				.filter(field -> field.isAnnotationPresent(ExcelColumn.class))
				.collect(Collectors.toList());
	}

}
