package ru.shulgindaniil.parser.impl;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.Parser;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.token.*;
import ru.shulgindaniil.token.type.impl.divider.Parentheses;

import java.util.*;

@Setter
@AllArgsConstructor
public class MathExpressionParser implements Parser {
    private final Handler hundlerChain;
    private final ExpressionNodeFactory nodeFactory;
    @Override
    public ExpressionNode parse(TokenStream tokenStream) throws RequireAnotherTokenException {
        Deque<Token> operatorStack =  new ArrayDeque<>();
        Deque<ExpressionNode> operandStack =  new ArrayDeque<>();

        Token current = null;
        while (tokenStream.hasNext()) {
            current = tokenStream.next();
            hundlerChain.handle(current, operatorStack, operandStack);
        }

        while (!operatorStack.isEmpty()) {
            Token token = operatorStack.pollLast();

            if (token.getType() instanceof Parentheses)
                throw new RequireAnotherTokenException();

            operandStack.addLast(nodeFactory.createOperationNode(token, operandStack));
        }

        if(operandStack.size() == 1)
            return operandStack.pollLast();

        throw new RequireAnotherTokenException();
    }
}
