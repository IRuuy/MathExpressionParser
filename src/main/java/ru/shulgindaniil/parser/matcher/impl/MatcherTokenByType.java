package ru.shulgindaniil.parser.matcher.impl;

import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.Token;
import ru.shulgindaniil.token.type.TokenType;

import java.util.Optional;

public class MatcherTokenByType implements MatcherToken<TokenType> {
    /**
     * Match the type consistency of a token with another token type.
     * @param option Option in last match.
     * @param token Comparable token.
     * @return <b>Optional of token</b>, If the token does not match the corresponding option, return <b>Optional of null</b>.
     */
    @Override
    public Optional<Token> match(TokenType option, Token token) {
        if(option == null || token == null)
            return Optional.empty();

        if(option.equals(token.getType()))
            return Optional.of(token);

        return Optional.empty();
    }
}
