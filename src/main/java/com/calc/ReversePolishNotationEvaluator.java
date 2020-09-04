package com.calc;

import java.util.Collections;
import java.util.Stack;

public class ReversePolishNotationEvaluator implements IReversePolishNotationEvaluator {

	private Stack<OperandToken> stack = new Stack<>();

	public ReversePolishNotationEvaluator() {
	}

	@Override
	public double evaluate(Stack<ExpressionToken> RPN) throws WrongInputException {
		return evalRPN(RPN);
	}

	private double evalRPN(Stack<ExpressionToken> tokensRPN) throws WrongInputException {

		Collections.reverse(tokensRPN);

		while (!tokensRPN.isEmpty()) {
			if (tokensRPN.peek().isOperator()) {
				addEvaluatedOperationToStack((OperatоrToken) tokensRPN.pop());
			} else {
				addNumberToStack((OperandToken) tokensRPN.pop());
			}
		}
		return stack.pop().getValue();
	}

	private void addEvaluatedOperationToStack(OperatоrToken operator) throws WrongInputException {
		stack.add(operator.getValue().execute(stack.pop(), stack.pop()));
	}

	private void addNumberToStack(OperandToken object) {
		stack.push(object);
	}

}
