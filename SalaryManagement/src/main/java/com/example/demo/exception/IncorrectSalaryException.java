package com.example.demo.exception;

public class IncorrectSalaryException extends RuntimeException {
	public IncorrectSalaryException(String message)
	{
		super(message);
	}
}
