package ru.shulgindaniil.token.type.impl.divider;

public enum FunctionArgs implements Divider {
    COMMA(",");
    private final String regex;
    FunctionArgs(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
