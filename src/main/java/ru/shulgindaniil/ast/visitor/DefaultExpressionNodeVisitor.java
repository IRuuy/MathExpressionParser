package ru.shulgindaniil.ast.visitor;

import lombok.NoArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.FunctionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.ValueNodeImpl;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.BinaryOperationNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.UnaryOperationNode;
import ru.shulgindaniil.exception.NullTokensException;
import ru.shulgindaniil.token.Token;

import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

import static ru.shulgindaniil.token.type.impl.Identifier.IDENTIFIER;
import static ru.shulgindaniil.token.type.impl.function.FunctionImpl.*;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryArithmeticOperator.*;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator.*;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.*;
import static ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl.NOT;
import static ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl.UNARY_MINUS;

@NoArgsConstructor
public class DefaultExpressionNodeVisitor implements ExpressionNodeVisitor {
    Map<String, VisitorData> data;

    public DefaultExpressionNodeVisitor(Map<String, VisitorData> data) {
        this.data = data;
    }
    @Override
    public VisitorData visitBinaryNode(BinaryOperationNode node) {
        VisitorData leftNodeAccept = node.getLeftNode().accept(this);
        VisitorData rightNodeAccept = node.getRightNode().accept(this);

        return switch (node.getOperator()) {
            case AND -> new VisitorData(leftNodeAccept.toBoolean() && rightNodeAccept.toBoolean());
            case OR -> new VisitorData(leftNodeAccept.toBoolean() || rightNodeAccept.toBoolean());
            case XOR -> new VisitorData(leftNodeAccept.toBoolean() ^ rightNodeAccept.toBoolean());

            case PLUS -> new VisitorData(leftNodeAccept.toDouble() + rightNodeAccept.toDouble());
            case MINUS -> new VisitorData(leftNodeAccept.toDouble() - rightNodeAccept.toDouble());
            case MULTIPLICATION -> new VisitorData(leftNodeAccept.toDouble() * rightNodeAccept.toDouble());
            case DIVISION -> new VisitorData(leftNodeAccept.toDouble() / rightNodeAccept.toDouble());
            case REMAINDER -> new VisitorData(leftNodeAccept.toDouble() % rightNodeAccept.toDouble());

            case EQUAL -> new VisitorData(leftNodeAccept.equals(rightNodeAccept));
            case NOT_EQUEAL -> new VisitorData(!leftNodeAccept.equals(rightNodeAccept));
            case GREATER -> new VisitorData(leftNodeAccept.toDouble() > rightNodeAccept.toDouble());
            case LESS -> new VisitorData(leftNodeAccept.toDouble() < rightNodeAccept.toDouble());

            default -> throw new IllegalStateException("Unexpected value: " + node.getOperator());
        };
    }

    @Override
    public VisitorData visitUnaryNode(UnaryOperationNode node) {
        VisitorData operandAccept = node.getOperand().accept(this);

        return switch (node.getOperator()) {
            case NOT -> {
                operandAccept.setValue(!operandAccept.toBoolean());
                yield operandAccept;
            }
            case UNARY_MINUS -> {
                operandAccept.setValue(0 - operandAccept.toDouble());
                yield operandAccept;
            }
            default -> throw new IllegalStateException("Unexpected value: " + node.getOperator());
        };
    }

    @Override
    public VisitorData visitFunctionNode(FunctionNode node) {
        List<ExpressionNode> args = node.getArgs().getNode().getItems();
        double result;

        if (args.isEmpty()) {
            throw new IllegalArgumentException("Function " + node.getValue() + " requires arguments.");
        }

        DoubleStream doubleStream = args.stream().mapToDouble(arg -> arg.accept(this).toDouble());

        result = switch (node.getValue().getType()) {
            case AVG -> doubleStream.average().orElse(0);
            case MAX -> doubleStream.max().orElse(0);
            case MIN -> doubleStream.min().orElse(0);
            default -> throw new IllegalStateException("Unexpected value: " + node.getValue() + ".");
        };

        return new VisitorData(String.valueOf(result));
    }

    @Override
    public VisitorData visitParenthesesNode(ParenthesesNode node) {
        return node.getNode().accept(this);
    }

    @Override
    public VisitorData visitListNode(ListNode node) {
        if (node.getItems().size() > 1)
            throw new IllegalArgumentException("List node should contain only one item.");
        return node.getItems().getFirst().accept(this);
    }

    @Override
    public VisitorData visitValueNode(ValueNodeImpl node) {
        Token value = node.getValue();
        if(value.getType().equals(IDENTIFIER)){
            if(data != null && data.containsKey(value.getValue())){
                return data.get(value.getValue());
            } else {
                throw new NullPointerException();
            }
        }
        return new VisitorData(value.getValue());
    }
}
