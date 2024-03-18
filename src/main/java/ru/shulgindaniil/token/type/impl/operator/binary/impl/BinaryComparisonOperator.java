package ru.shulgindaniil.token.type.impl.operator.binary.impl;

import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

public enum BinaryComparisonOperator implements BinaryOperator {
    EQUAL("=", 4),
    NOT_EQUEAL("<>", 4),
    GREATER(">", 4),
    LESS("<", 4);
    private final String regex;
    private final int priority;

    BinaryComparisonOperator(String regex, int priority) {
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
