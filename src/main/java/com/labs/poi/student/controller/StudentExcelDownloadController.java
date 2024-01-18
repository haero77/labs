//package com.labs.poi.student.controller;
//
//import com.labs.poi.student.application.StudentReader;
//import com.labs.poi.student.application.excel.StudentExcelViewDto;
//import com.labs.poi.student.entity.Student;
//import java.util.List;
//import java.util.stream.Collectors;
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/students")
//public class StudentExcelDownloadController {
//
//	private final StudentReader studentReader;
//
//	/**
//	 * 엑셀 다운로드 form 출력
//	 */
//	@GetMapping("/excel/download-form")
//	public String getExcelExportForm() {
//		return "";
//	}
//
//	/*
//	 *   엑셀 다운로드
//	 *   @param HttpServletResponse
//	 *   @throws IOException
//	 *   @throws RuntimeException
//	 * */
//	@GetMapping("/excel/download")
//	public void excelDownLoad(HttpServletResponse response) {
//
//		// 엑셀로 출력할 학생 리스트 조회
//		List<Student> students = studentReader.fetchAll();
//
//		// 학생 EntityList를 DtoList로 변환
//		List<StudentExcelViewDto> excelViews = students.stream()
//				.map(StudentExcelViewDto::from)
//				.collect(Collectors.toList());
//
//		// 엑셀 다운로드 로직 실행
//		excelUtils.studentExcelDownload(studentDtoList, response);
//	}
//
//}
