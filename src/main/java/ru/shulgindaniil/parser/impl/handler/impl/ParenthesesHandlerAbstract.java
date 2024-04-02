package ru.shulgindaniil.parser.impl.handler.impl;

import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.token.Token;

import java.util.Deque;

import static ru.shulgindaniil.token.type.impl.divider.Parentheses.LPAR;
@RequiredArgsConstructor
public abstract class ParenthesesHandlerAbstract implements Handler {
    private final ExpressionNodeFactory expressionNodeFactory;

    /**
     * <ul>
     *     <li><b>If the object at the top of the queue is not {@link ru.shulgindaniil.ast.impl.ListNode} - an error.</b></li>
     *     <li>Handle all operations - {@link #handleOperations(Deque, Deque) handleOperations}.</li>
     *     <li>
     *         Consider that the penultimate object is {@link ru.shulgindaniil.ast.impl.ListNode}.<br/>
     *         Add the last element in the queue to the items collection of the object - {@link ru.shulgindaniil.ast.impl.ListNode}
     *     </li>
     * </ul>
     *
     * @param operatorStack
     * @param operandStack
     * @throws RequireAnotherTokenException
     */
    protected void addValueToListNode(
            Deque<Token> operatorStack,
            Deque<ExpressionNode> operandStack
    ) throws RequireAnotherTokenException {
        if(operandStack.peekLast() instanceof ListNode)
            throw new RequireAnotherTokenException();

        handleOperations(operatorStack, operandStack);

        try {
            ExpressionNode tempOperand = operandStack.pollLast();
            ListNode node = (ListNode) operandStack.peekLast();
            node.getItems().add(tempOperand);
        } catch (NullPointerException e){
            throw new RequireAnotherTokenException();
        }
    }

    /**
     * <b>As long as the token at the top of the stack is not a left open parenthesis: </b>
     * <ul>
     *     <li>Push a statement from the stack to the output queue.</li>
     * </ul>
     * @param operatorStack
     * @param operandStack
     * @throws RequireAnotherTokenException
     */
    private void handleOperations(
            Deque<Token> operatorStack,
            Deque<ExpressionNode> operandStack
    ) throws RequireAnotherTokenException {
        while (!operatorStack.isEmpty() && !operatorStack.peekLast().getType().equals(LPAR)) {
            Token token = operatorStack.pollLast();
            operandStack.addLast(expressionNodeFactory.createOperationNode(token, operandStack));
        }
    }
}
