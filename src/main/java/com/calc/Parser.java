package com.calc;

import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser {

	static final String OPERATOR_PATTERN = PatternGenerator.generateOperatorPattern();

	private List<ExpressionToken> tokens = new ArrayList<>();

	public Parser() {
	}

	@Override
	public List<ExpressionToken> stringToExpressionTokens(String expr) {

		for (int i = 0; i < expr.length();) {
			if (isOperator(expr.charAt(i)) && !isNegativeDigit(expr, i)) {
				i += addOperatorToList(expr, i);
			} else {
				i += addNumberToList(expr, i);
			}
		}
		return tokens;
	}

	private int addNumberToList(String expr, int index) {
		String number = "";
		if (isNegativeDigit(expr, index)) {
			number += "-";
			number += getWholeNumber(expr, index + 1);
		} else {
			number += getWholeNumber(expr, index);
		}
		tokens.add(new OperandToken(Integer.valueOf(number)));
		return number.length();
	}

	private int addOperatorToList(String expr, int i) {
		expr = expr.substring(i, i + 1);
		for (Operator o : Operator.values()) {
			if (expr.equals(o.getOperator())) {
				tokens.add(new OperatоrToken(o));
			}
		}
		return 1;
	}

	private String getWholeNumber(String expr, int i) {
		String number = "";
		while (i < expr.length() && isDigit(expr.charAt(i))) {
			number += expr.charAt(i++);
		}
		return number;
	}

	private boolean isNegativeDigit(String expr, int i) {
		return (expr.charAt(i) == '-')
				? (i == 0 || expr.charAt(i - 1) == '*' || expr.charAt(i - 1) == '/' || expr.charAt(i - 1) == '(')
				: false;
	}

	private boolean isOperator(char c) {
		return Character.toString(c).matches(OPERATOR_PATTERN);
	}

	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

}
