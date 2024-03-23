package ru.shulgindaniil.ast;

import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;

public interface ExpressionNode {
    VisitorData accept(ExpressionNodeVisitor visitor);
}
