package com.calc;

public class WrongInputException extends Exception{

	private static final long serialVersionUID = -1751680072322001260L;

	public WrongInputException() {
		super();
	}
	
	 public WrongInputException(String errorMessage) {
	        super(errorMessage);
	 }
	 
	 public WrongInputException(Throwable cause) {
	        super(cause);
	   }
}
