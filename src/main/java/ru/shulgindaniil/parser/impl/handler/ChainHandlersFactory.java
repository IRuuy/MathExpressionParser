package ru.shulgindaniil.parser.impl.handler;

import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.type.TokenType;
import ru.shulgindaniil.token.type.impl.operator.PriorityComparator;

public interface ChainHandlersFactory {
    Handler createChainOfHandlers(
            MatcherToken<TokenType> matcherByType,
            MatcherToken<Class<?>> matcherByClass,
            PriorityComparator priorityComparator,
            ExpressionNodeFactory expressionNodeFactory
    );
}
