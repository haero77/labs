package com.labs.poi.student.application.request;

import com.labs.poi.student.entity.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudentAppendRequest {

	private String name;

	private StudentAppendRequest() {
	}

	@Builder
	private StudentAppendRequest(String name) {
		this.name = name;
	}

	public Student toEntity() {
		return Student.builder()
				.name(this.name)
				.build();
	}

}
