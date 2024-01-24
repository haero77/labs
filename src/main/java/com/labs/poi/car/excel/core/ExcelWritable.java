package com.labs.poi.car.excel.core;

import java.io.IOException;
import java.io.OutputStream;

public interface ExcelWritable {

	void write(OutputStream stream) throws IOException;

}
