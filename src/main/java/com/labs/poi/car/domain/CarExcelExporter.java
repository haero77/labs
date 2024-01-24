package com.labs.poi.car.domain;

import com.labs.poi.car.domain.exception.CarExcelExportException;
import com.labs.poi.car.excel.core.ExcelExporter;
import com.labs.poi.car.excel.core.ExcelWritable;
import com.labs.poi.car.excel.core.OneSheetExcelFile;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarExcelExporter {

	private final CarService carService;
	private final ExcelExporter excelExporter;

	public void exportAllCarInfo(HttpServletResponse response) throws IOException {
		List<CarExcelDto> carExcelDtos = carService.fetchAllAsExcelDto();
		ExcelWritable carExcelWritable = OneSheetExcelFile.withAdditionalOrderingColumn(CarExcelDto.class, carExcelDtos);
		excelExporter.export(carExcelWritable, response, "자동차 엑셀", CarExcelExportException::new);
	}

}
