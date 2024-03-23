package ru.shulgindaniil.ast.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;

@Data
@RequiredArgsConstructor
public class ParenthesesNode implements ExpressionNode {
    private final ListNode node;

    @Override
    public VisitorData accept(ExpressionNodeVisitor visitor) {
        return visitor.visitParenthesesNode(this);
    }
}