package com.labs.poi.car.excel.resource;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelRenderingResource {

	private final List<String> dataFieldNames;
	private final Map<String, String> headerNames; // <dataFieldName, headerName>
	private final CellStyleHolder cellStyleHolder;

	public ExcelRenderingResource(
			List<String> dataFieldNames,
			Map<String, String> headerNames,
			CellStyleHolder cellStyleHolder
	) {
		this.dataFieldNames = dataFieldNames;
		this.headerNames = headerNames;
		this.cellStyleHolder = cellStyleHolder;
	}

	public String getHeaderName(String fieldName) {
		return this.headerNames.get(fieldName);
	}

	public List<String> getDataFieldNames() {
		return List.copyOf(this.dataFieldNames);
	}

	public CellStyle getCellStyle(CellKey cellKey) {
		return this.cellStyleHolder.getStyle(cellKey);
	}

}
