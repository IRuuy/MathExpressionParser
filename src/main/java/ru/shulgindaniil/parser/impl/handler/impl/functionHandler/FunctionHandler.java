package ru.shulgindaniil.parser.impl.handler.impl.functionHandler;

import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.impl.function.Function;

import java.util.Deque;

@RequiredArgsConstructor
public class FunctionHandler implements Handler {
    private final Handler nextHandler;
    private final MatcherToken<Class<?>> matcherToken;

    /**
     * <b>If the token is a function, then put it on the stack.</b>
     * @param currentToken
     * @param operatorStack
     * @param operandStack
     * @throws RequireAnotherTokenException
     */
    @Override
    public void handle(
            Token currentToken,
            Deque<Token> operatorStack,
            Deque<ExpressionNode> operandStack
    ) throws RequireAnotherTokenException {
        if(matcherToken.match(Function.class, currentToken).isPresent()) {
            operatorStack.addLast(currentToken);
        } else if(nextHandler != null) {
            nextHandler.handle(currentToken, operatorStack, operandStack);
        }
    }
}
