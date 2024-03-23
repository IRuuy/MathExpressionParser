package ru.shulgindaniil.ast;

import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.ast.impl.valueNode.ValueNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.FunctionNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.ValueNodeImpl;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.BinaryOperationNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.UnaryOperationNode;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

import java.util.Deque;

public class ExpressionNodeFactory {
    public ExpressionNode createParenthesesNode(ListNode listNode) {
        return new ParenthesesNode(listNode);
    }

    public ExpressionNode createValueNode(Token token) {
        return new ValueNodeImpl(token);
    }

    public ExpressionNode createFunctionNode(Token value, ParenthesesNode args) {
        return new FunctionNode(value, args);
    }

    public ExpressionNode createOperationNode(Token token, Deque<ExpressionNode> operandStack)
            throws RequireAnotherTokenException {
        if (token.getType() instanceof BinaryOperator) {
            ExpressionNode rightNode = getOperand(operandStack);
            ExpressionNode leftNode = getOperand(operandStack);

            return new BinaryOperationNode(leftNode, token, rightNode);
        } else {
            return new UnaryOperationNode(token, getOperand(operandStack));
        }
    }

    private ExpressionNode getOperand(Deque<ExpressionNode> operandStack)
            throws RequireAnotherTokenException
    {
        checkOperandStack(operandStack);
        return operandStack.pollLast();
    }

    private void checkOperandStack(Deque<ExpressionNode> operandStack)
            throws RequireAnotherTokenException
    {
        if (operandStack.isEmpty()) {
            throw new RequireAnotherTokenException();
        }

        ExpressionNode top = operandStack.peekLast();
        if (!(top instanceof ValueNode) && !(top instanceof ParenthesesNode)) {
            throw new RequireAnotherTokenException();
        }
    }
}
