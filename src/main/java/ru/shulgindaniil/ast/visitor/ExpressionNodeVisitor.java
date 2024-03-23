package ru.shulgindaniil.ast.visitor;

import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.FunctionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.ValueNodeImpl;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.BinaryOperationNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.UnaryOperationNode;

public interface ExpressionNodeVisitor {
    VisitorData visitBinaryNode(BinaryOperationNode node);
    VisitorData visitUnaryNode(UnaryOperationNode node);
    VisitorData visitFunctionNode(FunctionNode node);
    VisitorData visitParenthesesNode(ParenthesesNode node);
    VisitorData visitListNode(ListNode node);
    VisitorData visitValueNode(ValueNodeImpl node);
}
