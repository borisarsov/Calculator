package com.calc;

public enum Operator {
	DIVISION("/", 1), MULTIPLICATION("*", 2), SUBTRACTION("-", 3), ADDITION("+", 4), RIGHT_PARENTHESIS(")", -1),
	LEFT_PARENTHESIS("(", -1);

	private String operator;
	private int priority;

	Operator(String string, int i) {
		operator = string;
		priority = i;
	}

	public String getOperator() {
		return operator;
	}

	public int getPriority() {
		return priority;
	}

	public OperandToken execute(OperandToken firstOperand, OperandToken secondOperand) throws WrongInputException {
		switch (this) {
		case ADDITION:
			return new OperandToken(secondOperand.getValue() + firstOperand.getValue());
		case SUBTRACTION:
			return new OperandToken(secondOperand.getValue() - firstOperand.getValue());
		case MULTIPLICATION:
			return new OperandToken(secondOperand.getValue() * firstOperand.getValue());
		case DIVISION:
			if (firstOperand.getValue() == 0) {
				throw new WrongInputException("Cannot devide with zero!");
			}
			return new OperandToken(secondOperand.getValue() / firstOperand.getValue());
		default:
			return null;
		}
	}
}
