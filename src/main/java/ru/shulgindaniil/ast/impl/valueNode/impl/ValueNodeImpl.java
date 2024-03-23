package ru.shulgindaniil.ast.impl.valueNode.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.valueNode.ValueNode;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;
import ru.shulgindaniil.token.Token;

@Data
@RequiredArgsConstructor
public class ValueNodeImpl implements ExpressionNode, ValueNode {
    private final Token value;

    @Override
    public VisitorData accept(ExpressionNodeVisitor visitor) {
        return visitor.visitValueNode(this);
    }
}
