package ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl;

import lombok.*;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.OperationNode;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.impl.operator.Operator;

@Data
@RequiredArgsConstructor
public class BinaryOperationNode implements OperationNode {
    private final ExpressionNode leftNode;
    private final Token value;
    private final ExpressionNode rightNode;
    @Override
    public Operator getOperator() {
        return (Operator) value.getType();
    }

    @Override
    public VisitorData accept(ExpressionNodeVisitor visitor) {
        return visitor.visitBinaryNode(this);
    }
}
