package com.labs.poi.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Car")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Long id;

	private String company; // 회사

	private String name; // 차종

	private int price; // 가격

	private double rating; // 평점

	@Builder
	private CarEntity(String company, String name, int price, double rating) {
		this.company = company;
		this.name = name;
		this.price = price;
		this.rating = rating;
	}

}
