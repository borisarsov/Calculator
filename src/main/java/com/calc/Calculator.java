package com.calc;

import java.util.List;
import java.util.Stack;

public class Calculator implements ICalculator {

	IValidator validator;
	IParser parser;
	IReversePolishNotationConverter converter;
	IReversePolishNotationEvaluator evaluator;

	Calculator(IValidator validator, IParser parser, IReversePolishNotationConverter converter,
			IReversePolishNotationEvaluator evaluator) {
		this.validator = validator;
		this.parser = parser;
		this.converter = converter;
		this.evaluator = evaluator;
	}

	@Override
	public double calculate(String expr) throws WrongInputException {

		validator.validate(expr);

		List<ExpressionToken> tokens = parser.stringToExpressionTokens(expr);

		Stack<ExpressionToken> RPN = converter.convert(tokens);

		return evaluator.evaluate(RPN);

	}

}
