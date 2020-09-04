package com.calc;

public interface ICalculator {
	public double calculate(String expr) throws WrongInputException;
}
