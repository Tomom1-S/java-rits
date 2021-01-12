package ch08.ex07;

import java.util.Comparator;

public class MyComparator {
    public static Comparator reservedNullsFirst() {
        return Comparator.nullsLast(Comparator.reverseOrder());
    }
}
