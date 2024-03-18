package ru.shulgindaniil.utils;

import java.util.ArrayList;
import java.util.List;

public class CombinerLists {
    @SafeVarargs
    public static <T> List<T> concatenate(List<T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<T> list : lists) {
            result.addAll(list);
        }
        return result;
    }
}
