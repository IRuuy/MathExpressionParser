package ru.shulgindaniil.parser;

import org.junit.jupiter.api.Test;
import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.ast.impl.ListNode;
import ru.shulgindaniil.ast.impl.ParenthesesNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.FunctionNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.ValueNodeImpl;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.BinaryOperationNode;
import ru.shulgindaniil.ast.impl.valueNode.impl.operatorNode.impl.UnaryOperationNode;
import ru.shulgindaniil.exception.NotStatementException;
import ru.shulgindaniil.exception.RequireAnotherTokenException;
import ru.shulgindaniil.parser.impl.MathExpressionParserFactory;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.TokenStream;
import ru.shulgindaniil.token.Lexer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.shulgindaniil.token.type.impl.Constant.NUMBER;
import static ru.shulgindaniil.token.type.impl.Identifier.IDENTIFIER;
import static ru.shulgindaniil.token.type.impl.function.FunctionImpl.MAX;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryArithmeticOperator.*;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator.EQUAL;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.AND;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.OR;
import static ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl.NOT;

class ParserBinaryOperationTest {
    private final Lexer lexer;
    private final Parser parser;

    public ParserBinaryOperationTest() throws NotStatementException {
        this.parser = new MathExpressionParserFactory().create();

        lexer = mock(Lexer.class);

        Map<String, TokenStream> moksMap = new HashMap<>();

        moksMap.put("NOT", MockTokenStream.unaryOperationWithIncorrectOperand());
        moksMap.put("AND", MockTokenStream.binaryOperationWithIncorrectAllOperand());
        moksMap.put("AND 4", MockTokenStream.binaryOperationWithIncorrectRightOperand());
        moksMap.put("4 AND", MockTokenStream.binaryOperationWithIncorrectLeftOperand());
        moksMap.put("(", MockTokenStream.leftParenthesesWithoutRightParentheses());
        moksMap.put(")", MockTokenStream.rightParenthesesWithoutLeftParentheses());
        moksMap.put("MAX(,)", MockTokenStream.funcWithCommaAndWithoutArgs());
        moksMap.put("(123, AND 3)", MockTokenStream.dividerArgsWithIncorrectBinaryOperation());
        moksMap.put("MAX(,2)", MockTokenStream.funcWithCommaAndIncorrectArgs());

        moksMap.put("f1=1 OR f2=2 AND f3=3 OR f4=4", MockTokenStream.orAndOr());
        moksMap.put("f1=1 OR f2=2 AND f3=3 AND f4=4 OR f5=5", MockTokenStream.orAndAndOr());
        moksMap.put("f1=1 AND f2=2 OR f3=3", MockTokenStream.andOr());
        moksMap.put("f1=1 AND (f2=2 OR f3=3)", MockTokenStream.andLparOrRpar());
        moksMap.put("f1=1 AND NOT (f2=2 OR f3=3)", MockTokenStream.andNotLparOrRpar());
        moksMap.put("f1=1 AND NOT f2=2 OR f3=3", MockTokenStream.andNotOr());
        moksMap.put("(NOT f1=1) AND f2=2", MockTokenStream.lparNotRparAnd());
        moksMap.put("MAX(1,2)", MockTokenStream.funcWithArgs());
        moksMap.put("MAX(1=3,2 AND 5, 5 OR 9)", MockTokenStream.funcWithOperationInArgs());
        moksMap.put("3+4*5/7-8", MockTokenStream.arithmeticOperator());


        for(var entry : moksMap.entrySet()) {
            when(lexer.getTokenStream(entry.getKey())).thenReturn(entry.getValue());
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>NOT</b>
     */
    @Test
    void testUnaryOperationWithIncorrectOperand() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("NOT");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>AND</b>
     */
    @Test
    void testBinaryOperationWithIncorrectAllOperand() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("AND");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>AND</b> 4
     */
    @Test
    void testBinaryOperationWithIncorrectLeftOperand() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("AND 4");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>4 <b>AND</b>
     */
    @Test
    void testBinaryOperationWithIncorrectRightOperand() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("4 AND");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>(</b>
     */
    @Test
    void testLeftParenthesesWithoutRightParentheses() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("(");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>)</b>
     */
    @Test
    void testRightParenthesesWithoutLeftParentheses() {
        try {
            TokenStream tokenStream = lexer.getTokenStream(")");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(,)</b>
     */
    @Test
    void testFuncWithCommaAndWithoutArgs() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("MAX(,)");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>(</b>123, <b>AND</b> 3<b>)</b>
     */
    @Test
    void testDividerArgsWithIncorrectBinaryOperation() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("(123, AND 3)");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(,2)</b>
     */
    @Test
    void testFuncWithCommaAndIncorrectArgs() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("MAX(,2)");

            assertThrows(RequireAnotherTokenException.class, () -> parser.parse(tokenStream));
        } catch (NotStatementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>OR</b> f2=2 <b>AND</b> f3=3 <b>OR</b> f4=4
     */
    @Test
    void testOrAndOr() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("f1=1 OR f2=2 AND f3=3 OR f4=4");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new BinaryOperationNode(
                                    new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                                    new Token(EQUAL, "=", null),
                                    new ValueNodeImpl(new Token(NUMBER, "1", null))
                            ),
                            new Token(OR, "OR", null),
                            new BinaryOperationNode(
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                                            new Token(EQUAL, "=", null),
                                            new ValueNodeImpl(new Token(NUMBER, "2", null))
                                    ),
                                    new Token(AND, "AND", null),
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(IDENTIFIER, "f3", null)),
                                            new Token(EQUAL, "=", null),
                                            new ValueNodeImpl(new Token(NUMBER, "3", null))
                                    )
                            )
                    ),
                    new Token(OR, "OR", null),
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER,"f4",null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "4", null))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>OR</b> f2=2 <b>AND</b> f3=3 <b>AND</b> f4=4 <b>OR</b> f5=5
     */
    @Test
    void testOrAndAndOr() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("f1=1 OR f2=2 AND f3=3 AND f4=4 OR f5=5");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new BinaryOperationNode(
                                    new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                                    new Token(EQUAL, "=", null),
                                    new ValueNodeImpl(new Token(NUMBER, "1", null))
                            ),
                            new Token(OR, "OR", null),
                            new BinaryOperationNode(
                                    new BinaryOperationNode(
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "2", null))
                                            ),
                                            new Token(AND, "AND", null),
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f3", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "3", null))
                                            )
                                    ),
                                    new Token(AND, "AND", null),
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(IDENTIFIER, "f4", null)),
                                            new Token(EQUAL, "=", null),
                                            new ValueNodeImpl(new Token(NUMBER, "4", null))
                                    )
                            )
                    ),
                    new Token(OR, "OR", null),
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER, "f5", null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "5", null))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND</b> f2=2 <b>OR</b> f3=3
     */
    @Test
    void testAndOr() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("f1=1 AND f2=2 OR f3=3");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new BinaryOperationNode(
                                    new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                                    new Token(EQUAL, "=", null),
                                    new ValueNodeImpl(new Token(NUMBER, "1", null))
                            ),
                            new Token(AND, "AND", null),
                            new BinaryOperationNode(
                                    new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                                    new Token(EQUAL, "=", null),
                                    new ValueNodeImpl(new Token(NUMBER, "2", null))
                            )
                    ),
                    new Token(OR, "OR", null),
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER, "f3", null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "3", null))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND<b/> <b>(</b>f2=2 <b>OR</b> f3=3<b>)</b>
     */
    @Test
    void testAndLparOrRpar() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("f1=1 AND (f2=2 OR f3=3)");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "1", null))
                    ),
                    new Token(AND, "AND", null),
                    new ParenthesesNode(
                            new ListNode(List.of(
                                    new BinaryOperationNode(
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "2", null))
                                            ),
                                            new Token(OR, "OR", null),
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f3", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "3", null))
                                            )
                                    )
                            ))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND</b> <b>NOT</b> <b>(</b>f2=2 <b>OR</b> f3=3<b>)</b>
     */
    @Test
    void testAndNotLparOrRpar() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("f1=1 AND NOT (f2=2 OR f3=3)");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "1", null))
                    ),
                    new Token(AND, "AND", null),
                    new UnaryOperationNode(
                            new Token(NOT, "NOT", null),
                            new ParenthesesNode(new ListNode(List.of(
                                    new BinaryOperationNode(
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "2", null))
                                            ),
                                            new Token(OR, "OR", null),
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f3", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "3", null))
                                            )
                                    )
                            )))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND</b> <b>NOT</b> f2=2 <b>OR</b> f3=3
     */
    @Test
    void testAndNotOr() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("f1=1 AND NOT f2=2 OR f3=3");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new BinaryOperationNode(
                                    new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                                    new Token(EQUAL, "=", null),
                                    new ValueNodeImpl(new Token(NUMBER, "1", null))
                            ),
                            new Token(AND, "AND", null),
                            new UnaryOperationNode(
                                    new Token(NOT, "NOT", null),
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                                            new Token(EQUAL, "=", null),
                                            new ValueNodeImpl(new Token(NUMBER, "2", null))
                                    )
                            )
                    ),
                    new Token(OR, "OR", null),
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER, "f3", null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "3", null))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>(NOT</b> f1=1<b>) AND</b> f2=2
     */
    @Test
    void testLparNotRparAnd() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("(NOT f1=1) AND f2=2");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new ParenthesesNode(
                            new ListNode(List.of(
                                    new UnaryOperationNode(
                                            new Token(NOT, "NOT", null),
                                            new BinaryOperationNode(
                                                    new ValueNodeImpl(new Token(IDENTIFIER, "f1", null)),
                                                    new Token(EQUAL, "=", null),
                                                    new ValueNodeImpl(new Token(NUMBER, "1", null))
                                            )
                                    )
                            ))
                    ),
                    new Token(AND, "AND", null),
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(IDENTIFIER, "f2", null)),
                            new Token(EQUAL, "=", null),
                            new ValueNodeImpl(new Token(NUMBER, "2", null))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(</b>1,2<b>)</b>
     */
    @Test
    void testFuncWithArgs() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("MAX(1,2)");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new FunctionNode(
                    new Token(MAX, "MAX", null),
                    new ParenthesesNode(
                            new ListNode(
                                    List.of(
                                            new ValueNodeImpl(new Token(NUMBER, "1", null)),
                                            new ValueNodeImpl(new Token(NUMBER, "2", null))
                                    )
                            )
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(</b>1=3,2 <b>AND</b> 5, 5 <b>OR</b> 9<b>)</b>
     */
    @Test
    void testFuncWithOperationInArgs() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("MAX(1=3,2 AND 5, 5 OR 9)");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new FunctionNode(
                    new Token(MAX, "MAX", null),
                    new ParenthesesNode(
                            new ListNode(List.of(
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(NUMBER, "1", null)),
                                            new Token(EQUAL, "=", null),
                                            new ValueNodeImpl(new Token(NUMBER, "3", null))
                                    ),
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(NUMBER, "2", null)),
                                            new Token(AND, "AND", null),
                                            new ValueNodeImpl(new Token(NUMBER, "5", null))
                                    ),
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(NUMBER, "5", null)),
                                            new Token(OR, "OR", null),
                                            new ValueNodeImpl(new Token(NUMBER, "9", null))
                                    )
                            ))
                    )
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <i><b>STATEMENT: </b></i>3<b>+</b>4<b>*</b>5<b>/</b>7<b>-</b>8
     */
    @Test
    void testArithmeticOperation() {
        try {
            TokenStream tokenStream = lexer.getTokenStream("3+4*5/7-8");

            ExpressionNode ast = parser.parse(tokenStream);

            ExpressionNode assertedAST = new BinaryOperationNode(
                    new BinaryOperationNode(
                            new ValueNodeImpl(new Token(NUMBER, "3", null)),
                            new Token(PLUS, "+", null),
                            new BinaryOperationNode(
                                    new BinaryOperationNode(
                                            new ValueNodeImpl(new Token(NUMBER, "4", null)),
                                            new Token(MULTIPLICATION, "*", null),
                                            new ValueNodeImpl(new Token(NUMBER, "5", null))
                                    ),
                                    new Token(DIVISION, "/", null),
                                    new ValueNodeImpl(new Token(NUMBER, "7", null))
                            )
                    ),
                    new Token(MINUS, "-", null),
                    new ValueNodeImpl(new Token(NUMBER, "8", null))
            );

            assertEquals(ast, assertedAST);
        } catch (NotStatementException | RequireAnotherTokenException e) {
            throw new RuntimeException(e);
        }
    }
}