package ru.shulgindaniil.parser.impl.handler.impl;

import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.impl.operator.Operator;
import ru.shulgindaniil.token.type.impl.operator.PriorityComparator;

import java.util.Deque;

@RequiredArgsConstructor
public class OperatorHandler implements Handler {
    private final Handler nextHandler;

    private final MatcherToken<Class<?>> matcherToken;
    private final PriorityComparator priorityComparator;
    private final ExpressionNodeFactory expressionNodeFactory;

    /**
     * <b>If the token is the op1 operator, then:</b>
     * <ul>
     *     <li>
     *      While there is a token operator op2 at the top of the stack, whose priority is higher than or equal to the priority of op1, and if the priorities are equal, op1 is left associative:
     *      <ul>
     *          <li>Переложить op2 из стека в выходную очередь</li>
     *      </ul>
     *     </li>
     *     <li>
     *         Put op1 on the stack
     *     </li>
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
        if(matcherToken.match(Operator.class, currentToken).isPresent()){
            Operator op1 = (Operator) currentToken.getType();

            while (!operatorStack.isEmpty() &&
                    operatorStack.peekLast().getType() instanceof Operator op2 &&
                    priorityComparator.compare(op1, op2) >= 0
            ) {
                Token token = operatorStack.pollLast();
                operandStack.addLast(expressionNodeFactory.createOperationNode(token, operandStack));
            }

            operatorStack.addLast(currentToken);
        } else if(nextHandler != null) {
            nextHandler.handle(currentToken, operatorStack, operandStack);
        }
    }
}
