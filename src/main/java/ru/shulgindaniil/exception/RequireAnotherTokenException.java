package ru.shulgindaniil.exception;

import ru.shulgindaniil.token.Token;

public class RequireAnotherTokenException extends RuntimeException {
    public RequireAnotherTokenException() {}

    public RequireAnotherTokenException(Token token){
        super(String.format(
                    "Require another token in the range from %d to %d",
                    token.getRange().getFrom(),
                    token.getRange().getTo()
        ));
    }
}
