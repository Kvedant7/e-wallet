package com.capgemini.exception;

public class UserExistsException extends Exception{
	public UserExistsException(Exception e,String msg){
		super(msg, e);
	}
		
}
