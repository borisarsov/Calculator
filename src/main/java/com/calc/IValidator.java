package com.calc;

public interface IValidator {
	void validate(String expr) throws WrongInputException;
}
