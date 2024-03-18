package ru.shulgindaniil.token.type.impl.function;

public enum FunctionImpl implements Function {
    MAX("\\bMAX\\b"),
    MIN("\\bMIN\\b"),
    AVG("\\bAVG\\b");
    private final String regex;
    FunctionImpl(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
