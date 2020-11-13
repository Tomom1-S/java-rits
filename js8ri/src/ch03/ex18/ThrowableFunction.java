package ch03.ex18;

@FunctionalInterface
public interface ThrowableFunction<T, U> {
    U apply(T t) throws Exception;
}
