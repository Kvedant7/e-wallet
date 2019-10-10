package com.capgemini.exception;

public class TechnicalException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TechnicalException(Exception e,String msg){
		super(msg, e);
	}
}
