package ru.shulgindaniil.ast.impl.valueNode.impl;

import lombok.Data;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.ast.impl.valueNode.ValueNode;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;
import ru.shulgindaniil.token.Token;

@Data
public class FunctionNode implements ExpressionNode, ValueNode {
    private final Token value;
    private final ParenthesesNode args;

    @Override
    public VisitorData accept(ExpressionNodeVisitor visitor) {
        return visitor.visitFunctionNode(this);
    }
}
