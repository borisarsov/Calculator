package com.calc;

import java.util.Stack;

public interface IReversePolishNotationEvaluator {
	double evaluate(Stack<ExpressionToken> RPN) throws WrongInputException;
}
