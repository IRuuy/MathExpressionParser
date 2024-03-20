package ru.shulgindaniil.parser.matcher;

import ru.shulgindaniil.token.Token;

import java.util.Optional;

public interface MatcherToken<T> {
    /**
     * Match whether the token matches the corresponding option.
     * @param option Option in last match.
     * @param token Comparable token.
     * @return <b>Optional of token</b>, If the token does not match the corresponding option, return <b>Optional of null</b>.
     */
    Optional<Token> match(T option, Token token);
}
