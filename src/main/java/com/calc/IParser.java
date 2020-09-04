package com.calc;

import java.util.List;

public interface IParser {
	List<ExpressionToken> stringToExpressionTokens(String expr);
}
