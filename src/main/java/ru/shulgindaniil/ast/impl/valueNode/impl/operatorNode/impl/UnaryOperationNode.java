package ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.OperationNode;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.impl.operator.Operator;

@Data
@AllArgsConstructor
public class UnaryOperationNode implements OperationNode {
    private Token value;
    private ExpressionNode operand;

    @Override
    public Operator getOperator() {
        return (Operator) value.getType();
    }

    @Override
    public VisitorData accept(ExpressionNodeVisitor visitor) {
        return visitor.visitUnaryNode(this);
    }
}
