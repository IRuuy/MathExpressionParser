package ru.shulgindaniil.ast.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ListNode implements ExpressionNode {
    private List<ExpressionNode> items;
    public ListNode() {
        items = new ArrayList<>();
    }

    @Override
    public VisitorData accept(ExpressionNodeVisitor visitor) {
        return visitor.visitListNode(this);
    }
}
