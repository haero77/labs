package com.labs.poi.car;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CarExcelController {

	private final CarService carService;

	@GetMapping("/api/v1/excel/car")
	public void downloadCarInfo(HttpServletResponse response) throws IOException {
		// 엑셀 파일 하나를 만듭니다
		Workbook workbook = new SXSSFWorkbook();
		// 엑셀 파일 내부에 Sheet 를 하나 생성합니다 (엑셀 파일 하나에는 여러 Sheet가 있을 수 있습니다)
		Sheet sheet = workbook.createSheet();

		// 엑셀 렌더링에 필요한 DTO를 가져옵니다
		List<CarExcelDto> carExcelDtos = carService.fetchAllAsExcelDto();

		// 헤더를 생성합니다
		int rowIndex = 0;
		Row headerRow = sheet.createRow(rowIndex++); // headerRow=0
		Cell headerCell1 = headerRow.createCell(0);
		headerCell1.setCellValue("회사");

		Cell headerCell2 = headerRow.createCell(1);
		headerCell2.setCellValue("차종");

		Cell headerCell3 = headerRow.createCell(2);
		headerCell3.setCellValue("가격");

		Cell headerCell4 = headerRow.createCell(3);
		headerCell4.setCellValue("평점");

		// 바디에 데이터를 넣어줍니다
		for (CarExcelDto dto : carExcelDtos) {
			Row bodyRow = sheet.createRow(rowIndex++); // 시작 row=1 (row=0 은 header row.)

			Cell bodyCell1 = bodyRow.createCell(0); // 컬럼[0] 셀
			bodyCell1.setCellValue(dto.getCompany());

			Cell bodyCell2 = bodyRow.createCell(1); // 컬럼[1] 셀
			bodyCell2.setCellValue(dto.getName());

			Cell bodyCell3 = bodyRow.createCell(2);
			bodyCell3.setCellValue(dto.getPrice());

			Cell bodyCell4 = bodyRow.createCell(3);
			bodyCell4.setCellValue(dto.getRating());
		}

		response.setContentType("application/vnd.ms-excel");
		String fileName = "파일 네임 얍얍";
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20")
						+ ".xlsx");

		workbook.write(response.getOutputStream());
		workbook.close();
	}

}
