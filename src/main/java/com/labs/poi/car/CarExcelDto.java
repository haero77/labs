package com.labs.poi.car;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CarExcelDto {

	@ExcelColumn(headerName = "회사")
	private final String company; // 회사

	@ExcelColumn(headerName = "차종")
	private final String name; // 차종

	@ExcelColumn(headerName = "차종")
	private final CarType type;

	private final int price; // 가격
	private final double rating; // 평점

	@Builder
	private CarExcelDto(String company, String name, CarType type, int price, double rating) {
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

}

