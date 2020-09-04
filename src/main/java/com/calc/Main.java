package com.calc;

public class Main {

	public static void main(String args[]) {

		IValidator validator = new Validator();
		IParser parser = new Parser();
		IReversePolishNotationConverter converter = new ReversePolishNotationConverter();
		IReversePolishNotationEvaluator evaluator = new ReversePolishNotationEvaluator();
		ICalculator calculator = new Calculator(validator, parser, converter, evaluator);
		try {
			System.out.println(calculator.calculate(args[0]));
		} catch (WrongInputException e) {
			System.out.println("Invalid parameters: " + e.getMessage());
			usage();
		}
	}

	static void usage() {
		System.out.println("Usage: java -jar calculator.jar");
	}
}
