package com.labs.poi.student.application;

import com.labs.poi.student.application.request.StudentAppendRequest;
import com.labs.poi.student.entity.Student;
import com.labs.poi.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public Long append(StudentAppendRequest request) {
		Student newStudent = studentRepository.save(request.toEntity());
		return newStudent.getId();
	}

}
