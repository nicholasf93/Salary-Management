package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.helper.CSVHelper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Optional<Employee> findEmployeeById(int id)
	{
		return employeeRepository.findById(id);
	}
	
	
	public List<Employee> findAllEmployee()
	{
		return employeeRepository.findAll();
	}
	
	public Employee saveEmployee(Employee employee) 
	{
		return employeeRepository.save(employee);
	}
	
	
	
	public void save(MultipartFile file)
	{
		try {
			List<Employee> employees = CSVHelper.csvToEmployee(file.getInputStream());
			employeeRepository.saveAll(employees);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
	
	public List<Employee> getSelectedEmployee(double minSalary, double maxSalary)
	{
		return employeeRepository.findSelectedEmployee(minSalary, maxSalary);
	}
}
