package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query(value = "SELECT u FROM User u WHERE u.salary >=:minSalary AND u.salary <=:maxSalary")
	List<Employee> findSelectedEmployee(@Param("minSalary") double minSalary, @Param("maxSalary") double maxSalary);
	
	List<Employee> findByLogin(String login);
	
	List<Employee> findByName(String name);
	
	Employee findById(String id);
}
