package ru.shulgindaniil.token;

import lombok.Setter;
import ru.shulgindaniil.exception.NotStatementException;
import ru.shulgindaniil.token.type.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.max;
import static ru.shulgindaniil.token.type.impl.divider.Indentation.SPACE;

/**
 * This is a class that converts a statement into a list of tokens.<br/>
 * It is responsible for the lexical analysis of the statement.
 */
@Setter
public class Lexer {
    private List<TokenType> lexemeList;

    public Lexer() {
        lexemeList = LexemeListFactory.getLexemeList();
    }
    public Lexer(List<TokenType> lexemeList) {
        this.lexemeList = lexemeList;
    }

    public TokenStream getTokenStream(String statement) throws NotStatementException {
        List<Token> tokens = new ArrayList<>();
        AtomicInteger position = new AtomicInteger(0);

        while (position.get() < statement.length())
            addToken(statement, position, tokens);

        tokens.removeIf(token -> token.getType().equals(SPACE));

        return new TokenStream(tokens);
    }

    private void addToken(String statement, AtomicInteger position, List<Token> tokens)
            throws NotStatementException
    {
        for (TokenType tokenOption: lexemeList) {
            Pattern pattern = Pattern.compile('^' + tokenOption.getRegex());
            Matcher matcher = pattern.matcher(statement.substring(position.get()));
            if(matcher.find()){
                var value = matcher.group(matcher.groupCount() > 0 ? 1: 0);
                if(value.isEmpty())
                   continue;

                int oldPosition = position.get();
                position.getAndAdd(matcher.group(0).length());

                Range range = new Range(oldPosition, position.get());
                tokens.add(new Token(tokenOption, value, range));

                return;
            }
        }
        throw new NotStatementException();
    }
}
