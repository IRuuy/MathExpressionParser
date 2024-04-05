package ru.shulgindaniil.facade;

import ru.shulgindaniil.ast.ExpressionNode;
import ru.shulgindaniil.parser.Parser;
import ru.shulgindaniil.parser.impl.MathExpressionParserFactory;
import ru.shulgindaniil.token.Lexer;
import ru.shulgindaniil.token.TokenStream;
import ru.shulgindaniil.token.type.TokenType;

import java.util.List;
public class MathExpressionParserFacade {
    private Parser parser;
    private Lexer lexer;

    public MathExpressionParserFacade() {
        this.lexer = new Lexer();
        this.parser = new MathExpressionParserFactory().create();
    }

    public MathExpressionParserFacade(Lexer lexer) {
        this.lexer = lexer;
    }

    public MathExpressionParserFacade(Parser parser) {
        this.parser = parser;
    }

    public MathExpressionParserFacade(Parser parser, Lexer lexer) {
        this.parser = parser;
        this.lexer = lexer;
    }

    public MathExpressionParserFacade(List<TokenType> lexemeList) {
        this.lexer = new Lexer(lexemeList);
        this.parser = new MathExpressionParserFactory().create();
    }

    public ExpressionNode parse(String statement) {
        TokenStream tokenStream = lexer.getTokenStream(statement);
        return parser.parse(tokenStream);
    }
}
