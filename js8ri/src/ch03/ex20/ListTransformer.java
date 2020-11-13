package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListTransformer {
    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        final List<U> result = new ArrayList<>();
        for (final T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
