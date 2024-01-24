package com.labs.poi.car.excel.resource;

import com.labs.poi.car.excel.core.ExcelRenderingLocation;
import java.util.Objects;

public class CellKey {

	private final String dataFieldName;
	private final ExcelRenderingLocation location;

	public CellKey(String dataFieldName, ExcelRenderingLocation location) {
		this.dataFieldName = dataFieldName;
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CellKey that = (CellKey) o;
		return Objects.equals(dataFieldName, that.dataFieldName) && location == that.location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFieldName, location);
	}

}
