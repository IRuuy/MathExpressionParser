package ru.shulgindaniil.token.type.impl.divider;

public enum Indentation implements Divider {
    SPACE("[\\s\\t]");
    private final String regex;
    Indentation(String regex) {
        this.regex = regex;
    }
    @Override
    public String getRegex() {
        return regex;
    }
}
