package ru.shulgindaniil.parser.impl.handler.impl.parenthesesHandler;

import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.TokenType;

import java.util.Deque;

import static ru.shulgindaniil.token.type.impl.divider.Parentheses.LPAR;

@RequiredArgsConstructor
public class LeftParenthesesHandler implements Handler {
    private final Handler nextHandler;

    private final MatcherToken<TokenType> matcherToken;

    /**
     * <b>If the token is an opening parenthesis, then put it on the stack.</b>
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
        if(matcherToken.match(LPAR, currentToken).isPresent()) {
            operatorStack.addLast(currentToken);
            operandStack.addLast(new ListNode());
        } else if(nextHandler != null) {
            nextHandler.handle(currentToken, operatorStack, operandStack);
        }
    }
}
