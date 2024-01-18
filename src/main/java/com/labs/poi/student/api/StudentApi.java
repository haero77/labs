package com.labs.poi.student.api;

import com.labs.poi.student.application.StudentService;
import com.labs.poi.student.application.request.StudentAppendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentApi {

	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<Void> append(@RequestBody StudentAppendRequest request) {
		studentService.append(request);
		return ResponseEntity.ok().build();
	}

}
