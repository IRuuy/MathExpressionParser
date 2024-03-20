package ru.shulgindaniil.parser.matcher.impl;

import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;

import java.util.Optional;

public class MatcherTokenByClass implements MatcherToken<Class<?>> {
    /**
     * Match that the token type and class match.
     * @param option Option in last match.
     * @param token Comparable token.
     * @return <b>Optional of token</b>, If the token does not match the corresponding option, return <b>Optional of null</b>.
     */
    @Override
    public Optional<Token> match(Class<?> option, Token token) {
        if(option == null || token == null)
            return Optional.empty();

        if(option.isInstance(token.getType()))
            return Optional.of(token);

        return Optional.empty();
    }
}
