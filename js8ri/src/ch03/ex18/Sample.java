package ch03.ex18;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class Sample {
    public static void main(final String[] args) {
        // p.74
        Supplier<String> supplier = unchecked(() -> new String(Files.readAllBytes(
                Paths.get("/etc/passwd")),
                StandardCharsets.UTF_8
        ));
        System.out.println(supplier.get());

        Function<String, String> function = unchecked(s ->
                new String(Files.readAllBytes(
                        Paths.get(s)),
                        StandardCharsets.UTF_8
                ));
        System.out.println(function.apply("/etc/passwd"));
    }

    public static <T, U> Function<T, U> unchecked(final ThrowableFunction<T, U> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable throwable) {
                // 柴田さん：Throwable も扱う
                throw throwable;
            }
        };
    }

    // p.73
    public static <T> Supplier<T> unchecked(final Callable<T> f) {
        return () -> {
            try {
                return f.call();
            } catch (final Exception e) {
                throw new RuntimeException(e);
            } catch (final Throwable t) {
                throw t;
            }
        };
    }
}
