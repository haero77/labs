package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.ExcelRenderingLocation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

public class CellStyleHolder {

	private final Map<CellKey, CellStyle> cellStyles;

	public CellStyleHolder(Map<CellKey, CellStyle> cellStyles) {
		this.cellStyles = cellStyles;
	}

	public static CellStyleHolder of(List<Field> fields, Workbook workbook) {
		Map<CellKey, CellStyle> cellStyles = new HashMap<>();

		for (Field field : fields) {
			cellStyles.put(
					new CellKey(field.getName(), ExcelRenderingLocation.HEADER),
					CellStyleFactory.createHeaderStyle(field, workbook)
			);
			cellStyles.put(
					new CellKey(field.getName(), ExcelRenderingLocation.CONTENT),
					CellStyleFactory.createContentStyle(field, workbook)
			);
		}

		return new CellStyleHolder(cellStyles);
	}

	public CellStyle getStyle(CellKey cellKey) {
		return this.cellStyles.get(cellKey);
	}

}
