package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "login")
	private String login;
	@Column(name = "name")
	private String name;
	@Column(name = "salary")
	private double salary;
	@Column(name = "startDate")
	private LocalDate startDate;
	
	public Employee(String id, String login, String name, double salary, LocalDate startDate)
	{
		this.id = id;
		this.login = login;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate;
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public String getId()
	{
		return id;
	}

	public void setId(String empId) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String empName) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public String getLogin()
	{
		return login;
	}
	public void setLogin(String empLogin) {
		// TODO Auto-generated method stub
		this.login = login;
	}

	public Double getSalary()
	{
		return salary;
	}
	public void setSalary(Double empSalary) {
		// TODO Auto-generated method stub
		this.salary = salary;
	}
	
	public LocalDate getStartDate()
	{
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		// TODO Auto-generated method stub
		this.startDate = startDate;
	}
	
}
