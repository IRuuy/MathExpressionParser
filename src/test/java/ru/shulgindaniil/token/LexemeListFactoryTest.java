package ru.shulgindaniil.token;

import org.junit.jupiter.api.Test;
import ru.shulgindaniil.token.enums.CustomBinaryComparisonOperator;
import ru.shulgindaniil.token.enums.CustomBinaryLogicOperator;
import ru.shulgindaniil.token.enums.CustomUnaryOperator;
import ru.shulgindaniil.token.type.impl.Constant;
import ru.shulgindaniil.token.type.impl.Identifier;
import ru.shulgindaniil.token.type.impl.divider.FunctionArgs;
import ru.shulgindaniil.token.type.impl.divider.Indentation;
import ru.shulgindaniil.token.type.impl.function.FunctionImpl;
import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryArithmeticOperator;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator;
import ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl;
import ru.shulgindaniil.token.type.impl.divider.Parentheses;
import ru.shulgindaniil.token.type.TokenType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexemeListFactoryTest {

    @Test
    void testGetTokenOptionsWithDefaultOptionsTest() {
        TokenType[] tokenOptions = new TokenType[]{
                UnaryOperatorImpl.NOT,
                UnaryOperatorImpl.UNARY_MINUS,

                BinaryArithmeticOperator.PLUS,
                BinaryArithmeticOperator.MINUS,
                BinaryArithmeticOperator.MULTIPLICATION,
                BinaryArithmeticOperator.DIVISION,

                BinaryLogicOperator.AND,
                BinaryLogicOperator.OR,
                BinaryLogicOperator.XOR,

                BinaryComparisonOperator.EQUAL,
                BinaryComparisonOperator.NOT_EQUEAL,
                BinaryComparisonOperator.GREATER,
                BinaryComparisonOperator.LESS,

                FunctionImpl.MAX,
                FunctionImpl.MIN,
                FunctionImpl.AVG,

                Constant.STRING,
                Constant.NUMBER,

                Parentheses.LPAR,
                Parentheses.RPAR,

                Indentation.SPACE,
                FunctionArgs.COMMA,

                Identifier.IDENTIFIER,
        };

        assertEquals(Arrays.asList(tokenOptions), LexemeListFactory.getLexemeList());
    }

    @Test
    void testGetTokenOptionsWithCustomOptionsTest() {
        TokenType[] tokenOptions = new TokenType[]{
                CustomUnaryOperator.CUSTOM_NOT,

                CustomBinaryLogicOperator.CUSTOM_AND,
                CustomBinaryLogicOperator.CUSTOM_OR,
                CustomBinaryComparisonOperator.CUSTOM_EQUAL,
                CustomBinaryComparisonOperator.CUSTOM_NOT_EQUAL,

                Constant.STRING,
                Constant.NUMBER,

                Parentheses.LPAR,
                Parentheses.RPAR,

                Indentation.SPACE,
                FunctionArgs.COMMA,

                Identifier.IDENTIFIER,
        };


        List<BinaryOperator> binaryOperations = new ArrayList<>();
        binaryOperations.addAll(List.of(CustomBinaryLogicOperator.values()));
        binaryOperations.addAll(List.of(CustomBinaryComparisonOperator.values()));

        assertEquals(
            Arrays.asList(tokenOptions), LexemeListFactory.getLexemeList(
                List.of(CustomUnaryOperator.values()),
                binaryOperations,
                new ArrayList<>()
            )
        );
    }
}