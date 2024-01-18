package com.labs.poi.student.application.excel;

import com.labs.global.annotation.ExcelColumn2;
import com.labs.poi.student.entity.Student;
import lombok.Builder;

public class StudentExcelViewDto {

	@ExcelColumn2(headerName = "아이디")
	private final Long id;

	@ExcelColumn2(headerName = "이름")
	private final String name;

	@Builder
	private StudentExcelViewDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static StudentExcelViewDto from(Student student) {
		return StudentExcelViewDto.builder()
				.id(student.getId())
				.name(student.getName())
				.build();
	}

}
