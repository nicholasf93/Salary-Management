package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Employee;
import com.example.demo.helper.CSVHelper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@PostMapping("/users/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file)
	{
		String message = "";
		if (CSVHelper.hasCSVFormat(file))
		{
			try {
		        employeeService.save(file);
		        message = "Uploaded the file successfully: " + file.getOriginalFilename();
		        // 200 - Success but no data updated
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload a csv file!";
		// 400 - Bad input - parsing error, duplicate row, invalid salary
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}	
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllEmployee()
	{
		try {
			List<Employee> employees = employeeService.findAllEmployee();
			if (employees.isEmpty())
			{
				ResponseMessage message = new ResponseMessage("Database is empty");
				return new ResponseEntity<ResponseMessage>(message, HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<>(employeeService.findAllEmployee(), HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>("Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
