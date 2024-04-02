package ru.shulgindaniil.parser.impl.handler.impl.parenthesesHandler;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.impl.handler.impl.ParenthesesHandlerAbstract;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.TokenType;
import ru.shulgindaniil.token.type.impl.divider.Parentheses;
import ru.shulgindaniil.token.type.impl.function.Function;

import java.util.Deque;

import static ru.shulgindaniil.token.type.impl.divider.Parentheses.RPAR;

public class RightParenthesesHandler extends ParenthesesHandlerAbstract {
    private final Handler nextHandler;

    private final MatcherToken<TokenType> matcherByType;
    private final ExpressionNodeFactory expressionNodeFactory;

    public RightParenthesesHandler(
            Handler nextHandler,
            MatcherToken<TokenType> matcherByType,
            ExpressionNodeFactory expressionNodeFactory
    ) {
        super(expressionNodeFactory);
        this.nextHandler = nextHandler;
        this.matcherByType = matcherByType;
        this.expressionNodeFactory = expressionNodeFactory;
    }

    /**
     * <b>If the token is a closing parenthesis:</b>
     * <ul>
     *     <li>Process everything before the closing brace to the opening brace - {@link ru.shulgindaniil.parser.impl.handler.impl.ParenthesesHandlerAbstract#addValueToListNode(Deque, Deque)}  addValueToListNode}.</li>
     *     <li>Pop the parenthesis from the stack, but do not add it to the output queue.</li>
     *     <li>If the token at the top of the stack is a function, move it to the output queue.</li>
     * </ul>
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
        if(matcherByType.match(RPAR, currentToken).isPresent()){
            super.addValueToListNode(operatorStack, operandStack);

            if (!operandStack.isEmpty() && operatorStack.peekLast().getType() instanceof Parentheses){
                ListNode listNode = (ListNode) operandStack.pollLast();
                operandStack.addLast(expressionNodeFactory.createParenthesesNode(listNode));

                operatorStack.pollLast();
            } else {
                throw new RequireAnotherTokenException();
            }

            if(!operatorStack.isEmpty() && operatorStack.peekLast().getType() instanceof Function){
                ParenthesesNode args = (ParenthesesNode) operandStack.pollLast();
                operandStack.addLast(expressionNodeFactory.createFunctionNode(operatorStack.pollLast(), args));
            }
        } else if(nextHandler != null) {
            nextHandler.handle(currentToken, operatorStack, operandStack);
        }
    }
}
