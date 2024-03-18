package ru.shulgindaniil.token;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.shulgindaniil.token.type.TokenType;

@Data
@RequiredArgsConstructor
public class Token {
    private final TokenType type;
    private final String value;
    private final Range range;
}
