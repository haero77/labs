package com.labs.poi.car.utils;

import com.labs.poi.car.excel.exception.ExcelProcessingException;
import java.lang.reflect.Field;

public class ReflectionUtils {

	private ReflectionUtils() {
	}

	public static Field getField(Object object, String fieldName) {
		try {
			return object.getClass().getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ExcelProcessingException(e.getMessage(), e);
		}
	}

}
