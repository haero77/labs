package com.labs.poi.student.config;

import com.labs.poi.student.application.StudentService;
import com.labs.poi.student.application.request.StudentAppendRequest;
import com.labs.poi.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentDatabaseInit {

	private final StudentService studentService;
	private final StudentRepository studentRepository;

//	@PostConstruct
	public void init() {
		studentRepository.deleteAllInBatch();

		for (int i = 1; i <= 30; i++) {
			studentService.append(createRequest(i));
		}
	}

	private StudentAppendRequest createRequest(int i) {
		return StudentAppendRequest.builder()
				.name("name" + i)
				.build();
	}

}
