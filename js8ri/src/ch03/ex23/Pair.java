package ch03.ex23;

import java.util.function.Function;

public class Pair<T> {
    public final T t1;
    public final T t2;

    public Pair(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public <U> Pair<U> flatMap(Function<T, U> mapper) {
        return new Pair<>(mapper.apply(t1), mapper.apply(t2));
    }

    public boolean equals(Pair<T> pair) {
        return t1.equals(pair.t1) && t2.equals(pair.t2);
    }
}
