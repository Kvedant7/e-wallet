package com.capgemini.exception;

public class UserNotFoundException extends Exception{

	@Override
	public String toString() {
		return "User Not Found";
	}
	
	
}
