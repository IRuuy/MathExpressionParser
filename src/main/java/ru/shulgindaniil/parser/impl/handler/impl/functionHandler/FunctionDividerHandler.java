package ru.shulgindaniil.parser.impl.handler.impl.functionHandler;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.impl.handler.impl.ParenthesesHandlerAbstract;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.TokenType;

import java.util.Deque;

import static ru.shulgindaniil.token.type.impl.divider.FunctionArgs.COMMA;

public class FunctionDividerHandler extends ParenthesesHandlerAbstract {
    private final Handler nextHandler;
    private final MatcherToken<TokenType> matcherToken;

    public FunctionDividerHandler(
            Handler nextHandler,
            MatcherToken<TokenType> matcherToken,
            ExpressionNodeFactory expressionNodeFactory
    ) {
        super(expressionNodeFactory);
        this.nextHandler = nextHandler;
        this.matcherToken = matcherToken;
    }

    /**
     * <b>If the token is a function argument separator (for example, a comma):</b>
     * <li>Process everything from commas to opening parentheses - {@link ru.shulgindaniil.parser.impl.handler.impl.ParenthesesHandlerAbstract#addValueToListNode(Deque, Deque)}}.</li>
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
        if(matcherToken.match(COMMA, currentToken).isPresent()) {
            super.addValueToListNode(operatorStack, operandStack);
        } else if(nextHandler != null) {
            nextHandler.handle(currentToken, operatorStack, operandStack);
        }
    }
}
