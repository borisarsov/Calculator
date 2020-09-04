package com.calc;

public class OperandToken implements ExpressionToken {

	private double operand;

	public OperandToken(double operand) {
		this.operand = operand;
	}

	public double getValue() {
		return operand;
	}

	@Override
	public boolean isOperator() {
		return false;
	}
}
