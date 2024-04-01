package ru.shulgindaniil.parser.impl.handler;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.token.Token;

import java.util.Deque;

public interface Handler {
    void handle(
            Token currentToken,
            Deque<Token> operatorStack,
            Deque<ExpressionNode> operandStack
    ) throws RequireAnotherTokenException;
}
