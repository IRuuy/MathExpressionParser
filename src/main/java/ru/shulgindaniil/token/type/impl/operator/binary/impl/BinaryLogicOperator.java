package ru.shulgindaniil.token.type.impl.operator.binary.impl;

import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

public enum BinaryLogicOperator implements BinaryOperator {
    AND("AND", 6),
    OR("OR", 7),
    XOR("XOR", 7);
    private final String regex;
    private final int priority;
    BinaryLogicOperator(String regex, int priority) {
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
