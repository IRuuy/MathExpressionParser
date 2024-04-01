package ru.shulgindaniil.parser;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.token.TokenStream;

public interface Parser {
    ExpressionNode parse(TokenStream tokenStream)
            throws RequireAnotherTokenException;
}
