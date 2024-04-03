package ru.shulgindaniil.parser.impl.handler;

import ru.shulgindaniil.ast.ExpressionNodeFactory;
import ru.shulgindaniil.parser.impl.handler.impl.OperatorHandler;
import ru.shulgindaniil.parser.impl.handler.impl.ValueHandler;
import ru.shulgindaniil.parser.impl.handler.impl.functionHandler.FunctionDividerHandler;
import ru.shulgindaniil.parser.impl.handler.impl.functionHandler.FunctionHandler;
import ru.shulgindaniil.parser.impl.handler.impl.parenthesesHandler.LeftParenthesesHandler;
import ru.shulgindaniil.parser.impl.handler.impl.parenthesesHandler.RightParenthesesHandler;
import ru.shulgindaniil.parser.matcher.MatcherToken;
import ru.shulgindaniil.token.type.TokenType;
import ru.shulgindaniil.token.type.impl.operator.PriorityComparator;

public class ChainHandlersFactoryImpl implements ChainHandlersFactory {
    public Handler createChainOfHandlers(
            MatcherToken<TokenType> matcherByType,
            MatcherToken<Class<?>> matcherByClass,
            PriorityComparator priorityComparator,
            ExpressionNodeFactory expressionNodeFactory
    ) {
        Handler terminalHandler = new ValueHandler(matcherByType, expressionNodeFactory);

        Handler rightParanthesesHandler = new RightParenthesesHandler(
                terminalHandler, matcherByType, expressionNodeFactory
        );

        Handler leftParanthesesHandler = new LeftParenthesesHandler(
                rightParanthesesHandler, matcherByType
        );

        Handler operatorHandler = new OperatorHandler(
                leftParanthesesHandler, matcherByClass, priorityComparator, expressionNodeFactory
        );

        Handler functioDividerHandler = new FunctionDividerHandler(
                operatorHandler, matcherByType, expressionNodeFactory
        );

        return new FunctionHandler(functioDividerHandler, matcherByClass);
    }
}
