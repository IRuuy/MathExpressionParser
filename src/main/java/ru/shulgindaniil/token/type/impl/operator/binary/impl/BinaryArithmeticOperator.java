package ru.shulgindaniil.token.type.impl.operator.binary.impl;

import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

public enum BinaryArithmeticOperator implements BinaryOperator {
    PLUS("\\+", 3),
    MINUS("-", 3),
    MULTIPLICATION("\\*", 2),
    DIVISION("\\/", 2),
    REMAINDER("%", 2);
    private final String regex;
    private final int priority;

    BinaryArithmeticOperator(String regex, int priority) {
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