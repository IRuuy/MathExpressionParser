package ru.shulgindaniil.matcher;

import org.junit.jupiter.api.Test;
import ru.shulgindaniil.parser.matcher.impl.MatcherTokenByType;
import ru.shulgindaniil.token.Range;
import ru.shulgindaniil.token.Token;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.shulgindaniil.token.type.impl.operator.binary.impl.BinaryLogicOperator.AND;

class MatcherTokenByTypeTest {
    @Test
    void isMatchWitchListTokensIsNull() {
        MatcherTokenByType optionTokenTypeMatcher = new MatcherTokenByType();
        assertTrue(optionTokenTypeMatcher.match(AND, null).isEmpty());
    }

    @Test
    void isMatch() {
        MatcherTokenByType optionTokenTypeMatcher = new MatcherTokenByType();
        Token token = new Token(AND, "AND", new Range(0, 3));
        assertTrue(optionTokenTypeMatcher.match(AND, token).isPresent());
    }
}