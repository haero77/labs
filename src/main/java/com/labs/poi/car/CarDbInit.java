package com.labs.poi.car;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarDbInit {

	private final CarRepository carRepository;

	@PostConstruct
	public void init() {
		for (int i = 1; i <= 30; i++) {
			CarEntity newCarEntity = CarEntity.builder()
					.company("company" + i)
					.carType(i % 2 == 0 ? CarType.SMALL : CarType.BIG)
					.name("name" + i)
					.price(i * 1000)
					.rating(i * 10)
					.build();
			carRepository.save(newCarEntity);
		}
	}

}
