package ru.shulgindaniil.token.enums;

import ru.shulgindaniil.token.type.impl.operator.unary.UnaryOperator;

public enum CustomUnaryOperator implements UnaryOperator {
    CUSTOM_NOT("CUSTOM_NOT");

    private final String regex;

    CustomUnaryOperator(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
