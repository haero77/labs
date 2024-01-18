package com.labs.poi.car;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	public List<CarExcelDto> fetchAllAsExcelDto() {
		return carRepository.findAll()
				.stream()
				.map(car -> CarExcelDto.from(car))
				.collect(Collectors.toList());
	}

//	public List<CarExcelDto> fetchAll() {
//		return carRepository.findAll();
//	}

}
