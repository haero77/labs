package com.labs.poi.car.excel;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class ExcelExporter {

	public static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";

	public void export(
			ExcelFile excelFile,
			HttpServletResponse response,
			String fileName
	) throws IOException {
		setExcelContentType(response);
		setFileName(response, fileName);
		excelFile.write(response.getOutputStream());
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
