package ru.shulgindaniil.parser;

import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.TokenStream;

import java.util.List;

import static ru.shulgindaniil.token.type.impl.Constant.NUMBER;
import static ru.shulgindaniil.token.type.impl.Identifier.IDENTIFIER;
import static ru.shulgindaniil.token.type.impl.divider.FunctionArgs.COMMA;
import static ru.shulgindaniil.token.type.impl.divider.Parentheses.LPAR;
import static ru.shulgindaniil.token.type.impl.divider.Parentheses.RPAR;
import static ru.shulgindaniil.token.type.impl.function.FunctionImpl.MAX;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryArithmeticOperator.*;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator.EQUAL;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.AND;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.OR;
import static ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl.NOT;


public class MockTokenStream {

    /**
     * <i><b>STATEMENT: </b></i><b>NOT</b>
     */
    public static TokenStream unaryOperationWithIncorrectOperand(){
        return new TokenStream(List.of(
                new Token(NOT, "NOT", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>AND</b>
     */
    public static TokenStream binaryOperationWithIncorrectAllOperand(){
        return new TokenStream(List.of(
                new Token(AND, "AND", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>AND</b> 4
     */
    public static TokenStream binaryOperationWithIncorrectLeftOperand(){
        return new TokenStream(List.of(
                new Token(AND, "AND", null),
                new Token(NUMBER, "4", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i>4 <b>AND</b>
     */
    public static TokenStream binaryOperationWithIncorrectRightOperand(){
        return new TokenStream(List.of(
                new Token(NUMBER, "4", null),
                new Token(AND, "AND", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>(</b>
     */
    public static TokenStream leftParenthesesWithoutRightParentheses(){
        return new TokenStream(List.of(
                new Token(LPAR, "(", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>)</b>
     */
    public static TokenStream rightParenthesesWithoutLeftParentheses(){
        return new TokenStream(List.of(
                new Token(RPAR, ")", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(,)</b>
     */
    public static TokenStream funcWithCommaAndWithoutArgs(){
        return new TokenStream(List.of(
                new Token(MAX, "MAX", null),
                new Token(LPAR, "(", null),
                new Token(COMMA, ",", null),
                new Token(RPAR, ")", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>(</b>123, <b>AND</b> 3<b>)</b>
     */
    public static TokenStream dividerArgsWithIncorrectBinaryOperation(){
        return new TokenStream(List.of(
                new Token(LPAR, "(", null),
                new Token(NUMBER, "123", null),
                new Token(COMMA, ",", null),
                new Token(AND, "AND", null),
                new Token(NUMBER, "3", null),
                new Token(RPAR, ")", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(,2)</b>
     */
    public static TokenStream funcWithCommaAndIncorrectArgs(){
        return new TokenStream(List.of(
                new Token(MAX, "MAX", null),
                new Token(LPAR, "(", null),
                new Token(COMMA, ",", null),
                new Token(NUMBER, "2", null),
                new Token(RPAR, ")", null)
        ));
    }


    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>OR</b> f2=2 <b>AND</b> f3=3 <b>OR</b> f4=4
     */
    public static TokenStream orAndOr(){
        return new TokenStream(List.of(
                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null),

                new Token(AND, "AND", null),

                new Token(IDENTIFIER, "f3", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f4", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "4", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>OR</b> f2=2 <b>AND</b> f3=3 <b>AND</b> f4=4 <b>OR</b> f5=5
     */
    public static TokenStream orAndAndOr(){
        return new TokenStream(List.of(
                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null),

                new Token(AND, "AND", null),

                new Token(IDENTIFIER, "f3", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null),

                new Token(AND, "AND", null),

                new Token(IDENTIFIER, "f4", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "4", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f5", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "5", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND</b> f2=2 <b>OR</b> f3=3
     */
    public static TokenStream andOr(){
        return new TokenStream(List.of(
                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(AND, "AND", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f3", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND<b/> <b>(</b>f2=2 <b>OR</b> f3=3<b>)</b>
     */
    public static TokenStream andLparOrRpar(){
        return new TokenStream(List.of(
                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(AND, "AND", null),

                new Token(LPAR, "(", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f3", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null),

                new Token(RPAR, ")", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND</b> <b>NOT</b> <b>(</b>f2=2 <b>OR</b> f3=3<b>)</b>
     */
    public static TokenStream andNotLparOrRpar(){
        return new TokenStream(List.of(
                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(AND, "AND", null),

                new Token(NOT, "NOT", null),

                new Token(LPAR, "(", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f3", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null),

                new Token(RPAR, ")", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i>f1=1 <b>AND</b> <b>NOT</b> f2=2 <b>OR</b> f3=3
     */
    public static TokenStream andNotOr() {
        return new TokenStream(List.of(
                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(AND, "AND", null),

                new Token(NOT, "NOT", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null),

                new Token(OR, "OR", null),

                new Token(IDENTIFIER, "f3", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>(NOT</b> f1=1<b>) AND</b> f2=2
     */
    public static TokenStream lparNotRparAnd(){
        return new TokenStream(List.of(
                new Token(LPAR, "(", null),

                new Token(NOT, "NOT", null),

                new Token(IDENTIFIER, "f1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "1", null),

                new Token(RPAR, ")", null),

                new Token(AND, "AND", null),

                new Token(IDENTIFIER, "f2", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "2", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(</b>1,2<b>)</b>
     */
    public static TokenStream funcWithArgs(){
        return new TokenStream(List.of(
                new Token(MAX, "MAX", null),

                new Token(LPAR, "(", null),

                new Token(NUMBER, "1", null),

                new Token(COMMA, ",", null),

                new Token(NUMBER, "2", null),

                new Token(RPAR, ")", null)
        ));
    }

    /**
     * <i><b>STATEMENT: </b></i><b>MAX(</b>1=3,2 <b>AND</b> 5, 5 <b>OR</b> 9<b>)</b>
     */
    public static TokenStream funcWithOperationInArgs(){
        return new TokenStream(List.of(
                new Token(MAX, "MAX", null),

                new Token(LPAR, "(", null),

                new Token(NUMBER, "1", null),
                new Token(EQUAL, "=", null),
                new Token(NUMBER, "3", null),

                new Token(COMMA, ",", null),

                new Token(NUMBER, "2", null),
                new Token(AND, "AND", null),
                new Token(NUMBER, "5", null),

                new Token(COMMA, ",", null),

                new Token(NUMBER, "5", null),
                new Token(OR, "OR", null),
                new Token(NUMBER, "9", null),

                new Token(RPAR, ")", null)
         ));
    }

    /**
     * <i><b>STATEMENT: </b></i>3<b>+</b>4<b>*</b>5<b>/</b>7<b>-</b>8
     */
    public static TokenStream arithmeticOperator(){
        return new TokenStream(List.of(
                new Token(NUMBER, "3", null),
                new Token(PLUS, "+", null),
                new Token(NUMBER, "4", null),
                new Token(MULTIPLICATION, "*", null),
                new Token(NUMBER, "5", null),
                new Token(DIVISION, "/", null),
                new Token(NUMBER, "7", null),
                new Token(MINUS, "-", null),
                new Token(NUMBER, "8", null)
        ));
    }
}
