package com.labs.poi.car.excel;

import com.labs.poi.car.excel.style.color.RgbColor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCellStyle {

	RgbColor foreGroundColor() default ;

	// align

}
