package ru.shulgindaniil.parser.impl.handler.impl;

import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.TokenType;

import java.util.Deque;

import static ru.shulgindaniil.token.type.impl.Constant.NUMBER;
import static ru.shulgindaniil.token.type.impl.Constant.STRING;
import static ru.shulgindaniil.token.type.impl.Identifier.IDENTIFIER;

@RequiredArgsConstructor
public class ValueHandler implements Handler {
    private final MatcherToken<TokenType> matcherToken;
    private final ExpressionNodeFactory expressionNodeFactory;

    /**
     * <b>If the token is a number, string or identifier, then add it to the output queue.</b>
     * @param currentToken
     * @param operatorStack
     * @param operandStack
     */
    @Override
    public void handle(
            Token currentToken,
            Deque<Token> operatorStack,
            Deque<ExpressionNode> operandStack
    ) {
        var expectedTypes = new TokenType[] {IDENTIFIER, STRING, NUMBER};

        for (var type : expectedTypes){
            if(matcherToken.match(type, currentToken).isPresent()) {
                operandStack.addLast(expressionNodeFactory.createValueNode(currentToken));
                break;
            }
        }
    }
}
