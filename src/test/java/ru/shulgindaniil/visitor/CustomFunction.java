package ru.shulgindaniil.visitor;

import ru.shulgindaniil.token.type.impl.function.Function;

public enum CustomFunction implements Function {
    LOG("LOG");
    private final String regex;
    CustomFunction(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
