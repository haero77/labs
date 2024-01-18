package com.labs.poi.student.application;

import com.labs.poi.student.entity.Student;
import com.labs.poi.student.exception.StudentNotFoundException;
import com.labs.poi.student.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentReader {

	private final StudentRepository studentRepository;

	public Student fetchOne(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(StudentNotFoundException::new);
	}

	public List<Student> fetchAll() {
		return studentRepository.findAll();
	}

}
