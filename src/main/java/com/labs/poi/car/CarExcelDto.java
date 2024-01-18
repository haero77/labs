package com.labs.poi.car;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CarExcelDto {

	private final String company; // 회사
	private final String name; // 차종
	private final int price; // 가격
	private final double rating; // 평점

	@Builder
	private CarExcelDto(String company, String name, int price, double rating) {
		this.company = company;
		this.name = name;
		this.price = price;
		this.rating = rating;
	}

	public static CarExcelDto from(CarEntity car) {
		return CarExcelDto.builder()
				.company(car.getCompany())
				.name(car.getName())
				.price(car.getPrice())
				.rating(car.getRating())
				.build();
	}

}

