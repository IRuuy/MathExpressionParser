package ru.shulgindaniil.token;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.shulgindaniil.exception.NullTokensException;

import java.util.Iterator;
import java.util.List;

@EqualsAndHashCode
@ToString
public class TokenStream implements Iterator<Token> {
    private List<Token> tokens;
    private int indexNextToken;

    public TokenStream(List<Token> tokens) {
        this.tokens = tokens;
        this.indexNextToken = 0;
    }

    @Override
    public boolean hasNext() {
        return indexNextToken < tokens.size();
    }

    @Override
    public Token next() {
        if(!hasNext())
            throw new NullTokensException();
        return tokens.get(indexNextToken++);
    }
}
