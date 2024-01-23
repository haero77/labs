package com.labs.poi.car.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	public List<CarExcelDto> fetchAllAsExcelDto() {
		List<CarEntity> all = carRepository.findAll();
		return CarExcelDto.from(all);
	}

//	public List<CarExcelDto> fetchAll() {
//		return carRepository.findAll();
//	}

}
