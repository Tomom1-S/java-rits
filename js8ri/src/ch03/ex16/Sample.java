package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Sample {
    public static <T> void doInOrderSync(
            final Supplier<T> first,
            final BiConsumer<T, Throwable> second) {
        final Thread t = new Thread(() -> {
            T result = null;
            try {
                result = first.get();
                second.accept(result, null);
            } catch (final Throwable throwable) {
                second.accept(result, throwable);
            }
        });
        t.start();
    }

    // p.72
    public static void doInOrder(final Runnable first, final Runnable second) {
        first.run();
        second.run();
    }

    // p.72
    public static void doInOrderSync(final Runnable first, final Runnable second) {
        final Thread t = new Thread(() -> {
            first.run();
            second.run();
        });
        t.start();
    }

    // p.72
    public static void doInOrderSync(
            final Runnable first,
            final Runnable second,
            final Consumer<Throwable> handler) {
        final Thread t = new Thread(() -> {
            try {
                first.run();
                second.run();
            } catch (final Throwable throwable) {
                handler.accept(throwable);
            }
        });
        t.start();
    }

    // p.73
    public static <T> void doInOrderSync(
            final Supplier<T> first,
            final Consumer<T> second,
            final Consumer<Throwable> handler) {
        final Thread t = new Thread(() -> {
            try {
                final T result = first.get();
                second.accept(result);
            } catch (final Throwable throwable) {
                handler.accept(throwable);
            }
        });
        t.start();
    }

}
