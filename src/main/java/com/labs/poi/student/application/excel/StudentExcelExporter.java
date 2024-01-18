package com.labs.poi.student.application.excel;

import com.labs.poi.student.entity.Student;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentExcelExporter {

	public void export(List<Student> source, HttpServletResponse servletResponse) {
		Workbook workbook = new XSSFWorkbook();
	}

}
