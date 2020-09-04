package com.calc;

public class PatternGenerator {

	static final String BEGIN_PATTERN = "^";
	static final String END_PATTERN = "$";
	static final String NEGATIVE_SIGN = "-";
	static final String ZERO_OR_MORE_TIMES = "*";

	static String generateOperatorPattern() {
		String operators = "";
		for (Operator operator : Operator.values()) {
			operators += "\\";
			operators += operator.getOperator();
		}
		return BEGIN_PATTERN + characterSequence(operators) + exactlyRepeatTimes(1) + END_PATTERN;
	}

	static String generateExpressionPattern() {
		String operators = "";
		for (Operator operator : Operator.values()) {
			if (operator.getPriority() > 0) {
				operators += "\\";
				operators += operator.getOperator();
			}
		}
		return BEGIN_PATTERN
				+ optional(NEGATIVE_SIGN) + characterClass(generateNumberPattern() + characterSequence(operators)
						+ exactlyRepeatTimes(1) + optional(NEGATIVE_SIGN))
				+ ZERO_OR_MORE_TIMES + generateNumberPattern() + END_PATTERN;
	}

	static String generateNumberPattern() {
		return "\\d+";
	}

	private static String characterSequence(String pattern) {
		return "[" + pattern + "]";
	}

	private static String exactlyRepeatTimes(int i) {
		return "{" + String.valueOf(i) + "}";
	}

	private static String optional(String expr) {
		return "(" + expr + ")?";
	}

	private static String characterClass(String expr) {
		return "(" + expr + ")";
	}

}
