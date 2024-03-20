package ru.shulgindaniil.matcher;

import org.junit.jupiter.api.Test;
import ru.shulgindaniil.parser.matcher.impl.MatcherTokenByClass;
import ru.shulgindaniil.token.Range;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator;
import ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MatcherTokenByClassTest {
    @Test
    void isMatchWitchListTokensIsNull() {
        MatcherTokenByClass matcher = new MatcherTokenByClass();
        Token token = new Token(BinaryLogicOperator.AND, "AND", new Range(0, 3));
        assertTrue(matcher.match(UnaryOperator.class, token).isEmpty());
    }

    @Test
    void isMatch() {
        MatcherTokenByClass matcher = new MatcherTokenByClass();
        Token token = new Token(BinaryLogicOperator.AND, "AND", new Range(0, 3));
        assertTrue(matcher.match(BinaryLogicOperator.class, token).isPresent());
    }
}
