package ch03.ex17;

import java.util.function.Consumer;

public class Sample {
    public static void doInParallelAsync(
            final Runnable first,
            final Runnable second,
            final Consumer<Throwable> handler) {
        new Thread(() -> {
            try {
                first.run();
            } catch (Throwable t) {
                handler.accept(t);
            }
        }).start();
        new Thread(() -> {
            try {
                second.run();
            } catch (Throwable t) {
                handler.accept(t);
            }
        }).start();
    }
}
