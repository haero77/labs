package com.labs.poi.car.domain;


import com.labs.poi.car.excel.ExcelCellStyle;
import com.labs.poi.car.excel.ExcelColumn;
import com.labs.poi.car.excel.style.align.ExcelTextAlign;
import com.labs.poi.car.excel.style.color.RgbColor;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CarExcelDto {

	@ExcelColumn(headerName = "번호")
	private int orderNo;

	@ExcelColumn(headerName = "회사", headerStyle = @ExcelCellStyle(foreGroundColor = RgbColor.PASTEL_RED))
	private final String company; // 회사

	@ExcelColumn(headerName = "차종")
	private final String name; // 차종

	@ExcelColumn(headerName = "차종-enum")
	private final CarType type;

	@ExcelColumn(headerName = "가격")
	private final int price; // 가격

	@ExcelColumn(headerName = "평점",
			headerStyle = @ExcelCellStyle(foreGroundColor = RgbColor.GREY, align = ExcelTextAlign.CENTER),
			contentStyle = @ExcelCellStyle(foreGroundColor = RgbColor.NONE, align = ExcelTextAlign.RIGHT)
	)
	private final double rating; // 평점

	@Builder
	public CarExcelDto(int orderNo, String company, String name, CarType type, int price, double rating) {
		this.orderNo = orderNo;
		this.company = company;
		this.name = name;
		this.type = type;
		this.price = price;
		this.rating = rating;
	}

	public static CarExcelDto from(CarEntity car) {
		return CarExcelDto.builder()
				.type(car.getCarType())
				.company(car.getCompany())
				.name(car.getName())
				.price(car.getPrice())
				.rating(car.getRating())
				.build();
	}

	public static List<CarExcelDto> from(List<CarEntity> carEntities) {
		return carEntities.stream()
				.map(carEntity -> from(carEntity))
				.collect(Collectors.toList());
	}

}

