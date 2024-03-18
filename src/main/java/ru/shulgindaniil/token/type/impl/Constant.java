package ru.shulgindaniil.token.type.impl;

import ru.shulgindaniil.token.type.TokenType;

public enum Constant implements TokenType {
    STRING("\"(.*)\""),
    NUMBER("[0-9]+");

    private final String regex;
    Constant(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
