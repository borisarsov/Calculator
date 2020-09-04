package com.calc;

import java.util.List;
import java.util.Stack;

public class ReversePolishNotationConverter implements IReversePolishNotationConverter {

	static final String PARENTHESIS = "()";

	Stack<OperatоrToken> operators = new Stack<OperatоrToken>();
	Stack<ExpressionToken> reversePolishStack = new Stack<ExpressionToken>();

	public ReversePolishNotationConverter() {

	}

	@Override
	public Stack<ExpressionToken> convert(List<ExpressionToken> tokens) throws WrongInputException {
		return convertToReversePolish(tokens);
	}

	private Stack<ExpressionToken> convertToReversePolish(List<ExpressionToken> tokens) {

		tokens.forEach((token) -> addToStack(token));

		addOperatorsToStack();

		return reversePolishStack;
	}

	private void addToStack(ExpressionToken token) {
		if (operatorIsParenthesis(token)) {
			addParanthesisToStack((OperatоrToken) token);
		} else if (isOperator(token)) {
			addOperatorToStack((OperatоrToken) token);
		} else {
			addNumberToStack(token);
		}
	}

	private void addOperatorToStack(OperatоrToken operation) {
		addOperatorsWithPrecedenceOverToStack(operation);
		operators.add((OperatоrToken) operation);
	}

	private void addParanthesisToStack(OperatоrToken operation) {
		addLeftParenthesisOntoStack(operation);
		addOperatorWithinParenthesis(operation);

	}

	private void addNumberToStack(ExpressionToken token) {
		reversePolishStack.add(token);
	}

	private void addOperatorsWithPrecedenceOverToStack(OperatоrToken operation) {
		if (!operators.isEmpty()) {
			while (operatorAtTopOfStackHasPrecedence(operation)) {
				reversePolishStack.add(operators.pop());
			}
		}
	}

	private boolean operatorAtTopOfStackHasPrecedence(OperatоrToken operation) {
		return !(operators.isEmpty() || operatorIsParenthesis(operators.peek())
				|| !stackOperatorHasEqualOrBiggerPrecedence(operation));
	}

	private boolean stackOperatorHasEqualOrBiggerPrecedence(OperatоrToken operation) {
		return operators.peek().getValue().getPriority() <= operation.getValue().getPriority();
	}

	private void addOperatorWithinParenthesis(OperatоrToken operation) {
		if (operation.getValue().equals(Operator.RIGHT_PARENTHESIS)) {
			while (!operators.peek().getValue().equals(Operator.LEFT_PARENTHESIS)) {
				reversePolishStack.add(operators.pop());
			}
			operators.pop();
		}
	}

	private void addLeftParenthesisOntoStack(OperatоrToken operation) {
		if (operation.getValue().equals(Operator.LEFT_PARENTHESIS)) {
			operators.push(operation);
		}

	}

	private void addOperatorsToStack() {
		while (!operators.isEmpty()) {
			reversePolishStack.push(operators.pop());
		}
	}

	private boolean isOperator(ExpressionToken token) {
		return token.isOperator();
	}

	private boolean operatorIsParenthesis(ExpressionToken token) {
		return token.isOperator()
				? (((OperatоrToken) token).getValue().equals(Operator.LEFT_PARENTHESIS)
						|| ((OperatоrToken) token).getValue().equals(Operator.RIGHT_PARENTHESIS))
				: false;

	}
}
