package ru.shulgindaniil.token.enums;

import ru.shulgindaniil.token.type.impl.operator.binary.BinaryOperator;

public enum CustomBinaryComparisonOperator implements BinaryOperator {
    CUSTOM_EQUAL("CUSTOM_EQUAL"),
    CUSTOM_NOT_EQUAL("CUSTOM_NOT_EQUAL");
    private final String regex;

    CustomBinaryComparisonOperator(String regex) {
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
