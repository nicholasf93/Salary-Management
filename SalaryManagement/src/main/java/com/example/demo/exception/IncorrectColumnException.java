package com.example.demo.exception;

public class IncorrectColumnException extends RuntimeException {
	public IncorrectColumnException(String message)
	{
		super(message);
	}
}
