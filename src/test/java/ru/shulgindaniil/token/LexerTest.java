package ru.shulgindaniil.token;

import org.junit.jupiter.api.Test;
import ru.shulgindaniil.exception.NotStatementException;
import ru.shulgindaniil.token.enums.CustomBinaryComparisonOperator;
import ru.shulgindaniil.token.enums.CustomBinaryLogicOperator;
import ru.shulgindaniil.token.enums.CustomUnaryOperator;
import ru.shulgindaniil.token.type.TokenType;
import ru.shulgindaniil.token.type.impl.Constant;
import ru.shulgindaniil.token.type.impl.Identifier;
import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.shulgindaniil.token.enums.CustomBinaryComparisonOperator.CUSTOM_EQUAL;
import static ru.shulgindaniil.token.enums.CustomUnaryOperator.CUSTOM_NOT;
import static ru.shulgindaniil.token.type.impl.Constant.NUMBER;
import static ru.shulgindaniil.token.type.impl.Constant.STRING;
import static ru.shulgindaniil.token.type.impl.Identifier.IDENTIFIER;
import static ru.shulgindaniil.token.type.impl.divider.FunctionArgs.COMMA;
import static ru.shulgindaniil.token.type.impl.divider.Parentheses.LPAR;
import static ru.shulgindaniil.token.type.impl.divider.Parentheses.RPAR;
import static ru.shulgindaniil.token.type.impl.function.FunctionImpl.MAX;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryArithmeticOperator.MINUS;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator.EQUAL;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.AND;
import static ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl.NOT;
import static ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl.UNARY_MINUS;

class LexerTest {
    @Test
    void getTokensWithWrongStatementTest() {
        String statement = "NOT ASDF ? name_field = \"value\"";

        Lexer lexer = new Lexer();
        assertThrows(NotStatementException.class, () -> lexer.getTokenStream(statement));
    }

    @Test
    void getTokensWithDefaultOptionsTest() {
        String statement = "NOT name_field = \"value\" AND MAX(~1, 3-3)";

        try {
            Lexer lexer = new Lexer();
            TokenStream tokenStream = lexer.getTokenStream(statement);
            TokenStream assertedTokenStream = new TokenStream(
                    List.of(
                            new Token(NOT, "NOT", new Range(0, 3)),
                            new Token(IDENTIFIER, "name_field", new Range(4, 15)),
                            new Token(EQUAL, "=", new Range(15,16)),
                            new Token(STRING, "value", new Range(17, 24)),
                            new Token(AND, "AND", new Range(25, 28)),
                            new Token(MAX, "MAX", new Range(29, 32)),
                            new Token(LPAR, "(", new Range(32, 33)),
                            new Token(UNARY_MINUS, "~", new Range(33, 34)),
                            new Token(NUMBER, "1", new Range(34, 35)),
                            new Token(COMMA, ",", new Range(35, 36)),
                            new Token(NUMBER, "3", new Range(37, 38)),
                            new Token(MINUS, "-", new Range(38, 39)),
                            new Token(NUMBER, "3", new Range(39, 40)),
                            new Token(RPAR, ")", new Range(40, 41))
                    )
            );

            assertEquals(tokenStream, assertedTokenStream);
        }
        catch (NotStatementException e){
            e.printStackTrace();
        }
    }

    @Test
    void getTokensWithCustomOptionsTest() throws NotStatementException {
        String statement = "CUSTOM_NOT name_field CUSTOM_EQUAL \"value\"";
        try {
            List<BinaryOperator> binaryOperators = new ArrayList<>();
            binaryOperators.addAll(List.of(CustomBinaryLogicOperator.values()));
            binaryOperators.addAll(List.of(CustomBinaryComparisonOperator.values()));

            List<TokenType> lexemeList = LexemeListFactory.getLexemeList(
                    List.of(CustomUnaryOperator.values()),
                    binaryOperators,
                    new ArrayList<>()
            );

            Lexer lexer = new Lexer(lexemeList);

            TokenStream tokenStream = lexer.getTokenStream(statement);

            TokenStream assertedTokensStream = new TokenStream(
                    List.of(
                            new Token(CUSTOM_NOT, "CUSTOM_NOT", new Range(0,10)),
                            new Token(Identifier.IDENTIFIER, "name_field", new Range(11,22)),
                            new Token(CUSTOM_EQUAL, "CUSTOM_EQUAL", new Range(22,34)),
                            new Token(Constant.STRING, "value", new Range(35,42))
                    )
            );

            assertEquals(tokenStream, assertedTokensStream);
        }
        catch (NotStatementException e){
            e.printStackTrace();
        }
    }
}