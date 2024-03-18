package ru.shulgindaniil.token;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Range {
    private final int from;
    private final int to;
}
