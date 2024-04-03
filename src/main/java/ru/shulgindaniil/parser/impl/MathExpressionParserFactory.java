package ru.shulgindaniil.parser.impl;

import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.parser.Parser;
import ru.shulgindaniil.parser.impl.handler.ChainHandlersFactory;
import ru.shulgindaniil.parser.impl.handler.ChainHandlersFactoryImpl;
import ru.shulgindaniil.parser.impl.handler.Handler;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.parser.matcher.impl.MatcherTokenByClass;
import ru.shulgindaniil.parser.matcher.impl.MatcherTokenByType;
import ru.shulgindaniil.token.type.TokenType;
import ru.shulgindaniil.token.type.impl.operator.PriorityComparator;

public class MathExpressionParserFactory {
    private final ChainHandlersFactory chainHandlersFactory;

    public MathExpressionParserFactory() {
        chainHandlersFactory = new ChainHandlersFactoryImpl();
    }

    public MathExpressionParserFactory(ChainHandlersFactory chainHandlersFactory) {
        this.chainHandlersFactory = chainHandlersFactory;
    }

    public Parser create() {
        MatcherToken<TokenType> matcherByType = new MatcherTokenByType();
        MatcherToken<Class<?>> matcherByClass = new MatcherTokenByClass();

        PriorityComparator priorityComparator = new PriorityComparator();
        ExpressionNodeFactory expressionNodeFactory = new ExpressionNodeFactory();

        Handler operatorHandler = chainHandlersFactory.createChainOfHandlers(
                matcherByType, matcherByClass,
                priorityComparator, expressionNodeFactory
        );

        return new MathExpressionParser(operatorHandler, expressionNodeFactory);
    }
}
