package ru.shulgindaniil.token.type.impl.divider;

public enum Parentheses implements Divider {
    LPAR("\\("),
    RPAR("\\)");

    private final String regex;
    Parentheses(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
