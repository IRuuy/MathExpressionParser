package ru.shulgindaniil.token.type.impl.operator;

import java.util.Comparator;

public class PriorityComparator implements Comparator<HavingPriority> {
    @Override
    public int compare(HavingPriority o1, HavingPriority o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
