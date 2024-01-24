package com.labs.poi.car.excel.core;

import com.labs.poi.car.excel.style.align.ExcelTextAlign;
import com.labs.poi.car.excel.style.color.RgbColor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

	String headerName() default "";

	// header 의 color & align 가능 해야함
	ExcelCellStyle headerStyle() default @ExcelCellStyle(foreGroundColor = RgbColor.NONE, align = ExcelTextAlign.CENTER);

	ExcelCellStyle contentStyle() default @ExcelCellStyle(foreGroundColor = RgbColor.NONE, align = ExcelTextAlign.RIGHT);

}
