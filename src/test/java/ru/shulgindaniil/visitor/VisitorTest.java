package ru.shulgindaniil.visitor;

import org.junit.jupiter.api.Test;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.FunctionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.ValueNodeImpl;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.BinaryOperationNode;
import ru.shulgindaniil.ast.visitor.DefaultExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.ExpressionNodeVisitor;
import ru.shulgindaniil.ast.visitor.VisitorData;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.facade.MathExpressionParserFacade;
import ru.shulgindaniil.parser.Parser;
import ru.shulgindaniil.parser.impl.MathExpressionParserFactory;
import ru.shulgindaniil.token.LexemeListFactory;
import ru.shulgindaniil.token.Lexer;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.shulgindaniil.token.type.impl.Constant.NUMBER;
import static ru.shulgindaniil.token.type.impl.Constant.STRING;
import static ru.shulgindaniil.token.type.impl.Identifier.IDENTIFIER;
import static ru.shulgindaniil.token.type.impl.function.FunctionImpl.MAX;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator.EQUAL;
import static ru.shulgindaniil.visitor.CustomFunction.LOG;

public class VisitorTest {
    /**
     * <i><b>STATEMENT: </b></i><b>LOG</b>(2, 4) <b>=</b> 2
     */
    @Test
    public void testCustomVisitor(){
        ParenthesesNode args = new ParenthesesNode(new ListNode(List.of(
                new ValueNodeImpl(new Token(NUMBER, "2", null)),
                new ValueNodeImpl(new Token(NUMBER, "4", null))
        )));

        ExpressionNode node = new BinaryOperationNode(
                new FunctionNode(new Token(LOG, "LOG", null), args),
                new Token(EQUAL, "=", null),
                new ValueNodeImpl(new Token(NUMBER, "2.0", null))
        );

        VisitorData result = node.accept(new VisitorImpl());
        assertTrue(result.toBoolean());
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX</b>(<b>MAX</b>(1, 2), 0)
     */
    @Test
    void testVisitorForFunctionInsideFunction() {
        ParenthesesNode args2 = new ParenthesesNode(new ListNode(List.of(
                new ValueNodeImpl(new Token(NUMBER, "1", null)),
                new ValueNodeImpl(new Token(NUMBER, "2", null))
        )));
        ExpressionNode fun2 = new FunctionNode(new Token(MAX, "MAX", null), args2);

        ParenthesesNode args1 = new ParenthesesNode(new ListNode(List.of(
                fun2,
                new ValueNodeImpl(new Token(NUMBER, "0", null))
        )));

        ExpressionNode fun1 = new FunctionNode(new Token(MAX, "MAX", null), args1);

        VisitorData accept = fun1.accept(new DefaultExpressionNodeVisitor());
        assertEquals(accept.toDouble(), 2.0);
    }

    /**
     * <i><b>STATEMENT: </b></i>variable <b>=</b> string
     */
    @Test
    void testVisitorWithIncorrectVariable() {
        Map<String, VisitorData> data = new HashMap<>();
        data.put("name", new VisitorData("Mike"));

        ExpressionNode node = new BinaryOperationNode(
                new ValueNodeImpl(new Token(IDENTIFIER, "incorrect_variable", null)),
                new Token(EQUAL, "=", null),
                new ValueNodeImpl(new Token(STRING, "Mike", null))
        );

        assertThrows(NullPointerException.class, () -> node.accept(new DefaultExpressionNodeVisitor(data)));
    }

    /**
     * <i><b>STATEMENT: </b></i>variable <b>=</b> string
     */
    @Test
    void testVisitorWithVariable() {
        Map<String, VisitorData> data = new HashMap<>();
        data.put("name", new VisitorData("Mike"));

        ExpressionNode node = new BinaryOperationNode(
                new ValueNodeImpl(new Token(IDENTIFIER, "name", null)),
                new Token(EQUAL, "=", null),
                new ValueNodeImpl(new Token(STRING, "Mike", null))
        );

        VisitorData accept = node.accept(new DefaultExpressionNodeVisitor(data));
        System.out.println(accept.toBoolean());
    }
}
