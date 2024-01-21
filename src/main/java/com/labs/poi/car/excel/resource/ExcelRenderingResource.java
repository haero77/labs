package com.labs.poi.car.excel.resource;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelRenderingResource {

	private final List<String> dataFieldNames;
	private final Map<CellKey, CellStyle> cellStyles;
	private final Map<String, String> headerNames; // <dataFieldName, headerName>

	public ExcelRenderingResource(
			Map<String, String> headerNames,
			List<String> dataFieldNames,
			Map<CellKey, CellStyle> cellStyles
	) {
		this.dataFieldNames = dataFieldNames;
		this.headerNames = headerNames;
		this.cellStyles = cellStyles;
	}

	public String getHeaderName(String fieldName) {
		return this.headerNames.get(fieldName);
	}

	public List<String> getDataFieldNames() {
		return List.copyOf(this.dataFieldNames);
	}

	public CellStyle getCellStyle(CellKey cellKey) {
		return this.cellStyles.get(cellKey);
	}

}
