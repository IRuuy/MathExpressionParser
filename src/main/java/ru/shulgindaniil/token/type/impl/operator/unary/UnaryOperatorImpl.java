package ru.shulgindaniil.token.type.impl.operator.unary;

public enum UnaryOperatorImpl implements UnaryOperator {
    NOT("NOT", 5),
    UNARY_MINUS("~", 2);
    private final String regex;
    private final int priority;

    UnaryOperatorImpl(String regex, int priority) {
        this.regex = regex;
        this.priority = priority;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
