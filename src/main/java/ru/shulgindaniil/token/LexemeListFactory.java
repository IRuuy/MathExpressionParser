package ru.shulgindaniil.token;

import ru.shulgindaniil.token.type.impl.Constant;
import ru.shulgindaniil.token.type.impl.Identifier;
import ru.shulgindaniil.token.type.impl.divider.FunctionArgs;
import ru.shulgindaniil.token.type.impl.divider.Indentation;
import ru.shulgindaniil.token.type.impl.function.Function;
import ru.shulgindaniil.token.type.impl.function.FunctionImpl;
import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryArithmeticOperator;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryComparisonOperator;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator;
import ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperator;
import ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperatorImpl;
import ru.shulgindaniil.token.type.impl.divider.Parentheses;
import ru.shulgindaniil.token.type.TokenType;
import ru.shulgindaniil.utils.CombinerLists;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for generating an enumeration list of all possible tokens. <br/>
 * Used in {@link ru.shulgindaniil.token.Lexer Lexer}.
 */
public class LexemeListFactory {

    public static List<TokenType> getLexemeList() {
        return getLexemeList(
                List.of(UnaryOperatorImpl.values()),
                CombinerLists.concatenate(
                        List.of(BinaryArithmeticOperator.values()),
                        List.of(BinaryLogicOperator.values()),
                        List.of(BinaryComparisonOperator.values())
                ),
                List.of(FunctionImpl.values())
        );
    }

    public static List<TokenType> getLexemeListForCustomFunctions(Function[] functions) {
        return getLexemeList(
                List.of(UnaryOperatorImpl.values()),
                CombinerLists.concatenate(
                        List.of(BinaryArithmeticOperator.values()),
                        List.of(BinaryLogicOperator.values()),
                        List.of(BinaryComparisonOperator.values())
                ),
                List.of(functions)
        );
    }

    public static List<TokenType> getLexemeListForCustomBinaryOperators(List<BinaryOperator> operators) {
        return getLexemeList(
                List.of(UnaryOperatorImpl.values()),
                operators,
                List.of(FunctionImpl.values())
        );
    }

    public static List<TokenType> getLexemeListForCustomUnaryOperator(List<UnaryOperator> operators) {
        return getLexemeList(
                operators,
                CombinerLists.concatenate(
                        List.of(BinaryArithmeticOperator.values()),
                        List.of(BinaryLogicOperator.values()),
                        List.of(BinaryComparisonOperator.values())
                ),
                List.of(FunctionImpl.values())
        );
    }

    public static  List<TokenType> getLexemeList(
            List<UnaryOperator> unaryOperators,
            List<BinaryOperator> binaryOperators,
            List<Function> functions
    ){
        List<TokenType> lexemes = new ArrayList<>();

        lexemes.addAll(unaryOperators);
        lexemes.addAll(binaryOperators);
        lexemes.addAll(functions);

        lexemes.addAll(generateStandardLexemes());

        return lexemes;
    }

    public static List<TokenType> generateStandardLexemes() {
        List<TokenType> lexemes = new ArrayList<>();
        lexemes.addAll(List.of(Constant.values()));

        lexemes.addAll(List.of(Parentheses.values()));
        lexemes.addAll(List.of(Indentation.values()));
        lexemes.addAll(List.of(FunctionArgs.values()));

        lexemes.addAll(List.of(Identifier.values()));

        return lexemes;
    }
}
