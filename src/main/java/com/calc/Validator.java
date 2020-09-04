package com.calc;

import java.util.Stack;

public class Validator implements IValidator {

	static final String EXPRESSION_PATTERN = PatternGenerator.generateExpressionPattern();
	static final String NUMBER_PATTERN = PatternGenerator.generateNumberPattern();

	@Override
	public void validate(String expr) throws WrongInputException {

		if (expr == null || expr.isEmpty()) {
			throw new WrongInputException("Expression cannot be empty String");
		}

		if (expr.contains("(") || expr.contains(")")) {

			if (!areParanthesBalanced(expr)) {
				throw new WrongInputException("Wrong input");
			}
		}

		if (!isValid(expr.replaceAll("\\)|\\(", ""))) {
			throw new WrongInputException("Wrong input");
		}

	}

	public boolean isNumber(String expr) {
		return expr.matches(NUMBER_PATTERN);
	}

	private boolean isValid(String expr) {
		return (expr.matches(EXPRESSION_PATTERN) || isNumber(expr));
	}

	private boolean areParanthesBalanced(String expr) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < expr.length(); i++) {
			char x = expr.charAt(i);
			if (x == '(') {
				stack.push(x);
				continue;
			} else if (x == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}
		return (stack.isEmpty());
	}

}
