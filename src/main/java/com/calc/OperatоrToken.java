package com.calc;

public class OperatоrToken implements ExpressionToken {

	private Operator operation;

	public OperatоrToken(Operator operation) {
		this.operation = operation;
	}

	public Operator getValue() {
		return operation;
	}

	@Override
	public boolean isOperator() {
		return true;
	}
}
