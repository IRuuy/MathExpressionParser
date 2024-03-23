package ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.valueNode.ValueNode;
import ru.shulgindaniil.token.type.impl.operator.Operator;

public interface OperationNode extends ExpressionNode, ValueNode {
    Operator getOperator();
}
