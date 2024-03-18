package ru.shulgindaniil.token.enums;

import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

public enum CustomBinaryLogicOperator implements BinaryOperator {
    CUSTOM_AND("CUSTOM_AND", 2),
    CUSTOM_OR("CUSTOM_OR", 3);
    private final String regex;
    private final int priority;

    CustomBinaryLogicOperator(String regex, int priority) {
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
