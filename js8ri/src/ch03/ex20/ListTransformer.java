package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ListTransformer {
    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        // 柴田さん：null チェックをする
        Objects.requireNonNull(list, "list is null");
        Objects.requireNonNull(f, "f is null");

        final List<U> result = new ArrayList<>();
        for (final T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
