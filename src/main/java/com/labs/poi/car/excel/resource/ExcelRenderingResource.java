package com.labs.poi.car.excel.resource;

import java.util.List;
import java.util.Map;

public class ExcelRenderingResource {

	private final List<String> dataFieldNames;
	private final Map<String, String> headerNames; // <DataFieldName, HeaderName>

	public ExcelRenderingResource(Map<String, String> headerNames, List<String> dataFieldNames) {
		this.headerNames = headerNames;
		this.dataFieldNames = dataFieldNames;
	}

	public String getHeaderName(String fieldName) {
		return this.headerNames.get(fieldName);
	}

	public List<String> getDataFieldNames() {
		return List.copyOf(this.dataFieldNames);
	}

}
