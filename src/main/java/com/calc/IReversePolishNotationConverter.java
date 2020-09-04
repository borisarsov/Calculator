package com.calc;

import java.util.List;
import java.util.Stack;

public interface IReversePolishNotationConverter {
	Stack<ExpressionToken> convert(List<ExpressionToken> tokens) throws WrongInputException;
}
