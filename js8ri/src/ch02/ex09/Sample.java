package ch02.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Sample {
    public static <T> ArrayList<T> toArrayList1(final Stream<ArrayList<T>> listStream) {
        ArrayList<T> result = listStream.reduce(
                new ArrayList<>(),
                (l, e) -> {
                    l.addAll(e);
                    return l;
                }
        );
        return result;
    }

    public static <T> ArrayList<T> toArrayList2(final Stream<ArrayList<T>> listStream) {
        ArrayList<T> result = listStream.reduce(
                (l, e) -> {
                    l.addAll(e);
                    return l;
                }
        ).orElse(new ArrayList<>());    // listStream が空のときは、空のリストを返す
        return result;
    }

    public static <T> ArrayList<T> toArrayList3(final Stream<ArrayList<T>> listStream) {
        ArrayList<T> result = listStream.reduce(
                new ArrayList<>(),
                (l, e) -> {
                    l.addAll(e);
                    return l;
                },
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );
        return result;
    }
}
