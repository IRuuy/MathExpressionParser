package ru.shulgindaniil.visitor;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.FunctionNode;
import ru.shulgindaniil.ast.visitor.DefaultExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;

import java.util.List;
import java.util.Map;

import static ru.shulgindaniil.visitor.CustomFunction.LOG;

public class VisitorImpl extends DefaultExpressionNodeVisitor {
    public VisitorImpl(Map<String, VisitorData> data) {
        super(data);
    }

    @Override
    public VisitorData visitFunctionNode(FunctionNode node) {
        List<ExpressionNode> args = node.getArgs().getNode().getItems();
        double result;

        if (args.size() != 2) {
            throw new IllegalArgumentException("Function " + node.getValue() + " requires 2 argument.");
        }

        double base = args.getFirst().accept(this).toDouble();
        double number = args.getLast().accept(this).toDouble();

        result = switch (node.getValue().getType()) {
            case LOG -> Math.log(number) / Math.log(base);
            default -> throw new IllegalStateException("Unexpected value: " + node.getValue() + ".");
        };

        return new VisitorData(String.valueOf(result));
    }
}
