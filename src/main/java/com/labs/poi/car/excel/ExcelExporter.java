package com.labs.poi.car.excel;

import com.labs.poi.car.excel.exception.ExcelExportException;
import com.labs.poi.car.excel.file.ExcelWritable;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExcelExporter {

	private static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";

	public void export(
			ExcelWritable excelWritable,
			HttpServletResponse response,
			String fileName
	) {
		export(excelWritable, response, fileName, ExcelExportException::new);
	}

	public void export(
			ExcelWritable excelWritable,
			HttpServletResponse response,
			String fileName,
			Supplier<Exception> errorHandler
	) {
		try {
			setExcelContentType(response);
			setFileName(response, fileName);
			excelWritable.write(response.getOutputStream());
		} catch (IOException e) {
			log.error(e.getMessage());
			errorHandler.get();
		}
	}

	private void setExcelContentType(HttpServletResponse response) {
		response.setContentType(EXCEL_CONTENT_TYPE);
	}

	private void setFileName(HttpServletResponse response, String fileName) {
		response.setHeader("Content-Disposition", "attachment;filename=" + encode(fileName) + ".xlsx");
	}

	private String encode(String fileName) {
		return convertPlusSignToSpace(URLEncoder.encode(fileName, StandardCharsets.UTF_8));
	}

	private String convertPlusSignToSpace(String source) {
		return source.replace("+", "%20");
	}

}
