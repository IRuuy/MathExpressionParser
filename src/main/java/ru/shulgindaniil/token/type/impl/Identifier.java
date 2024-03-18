package ru.shulgindaniil.token.type.impl;

import ru.shulgindaniil.token.type.TokenType;

public enum Identifier implements TokenType {
    IDENTIFIER("([A-Za-zА-Яа-я0-9_]*)[\\s]*");

    private final String regex;
    Identifier(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
