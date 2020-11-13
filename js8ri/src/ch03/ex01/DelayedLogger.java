package ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DelayedLogger {
    public static void main(final String[] args) {
        final int i = 10;
        final int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        logIf(Level.FINEST, () -> {
            System.out.println("condition");
            return i == 10;
        }, () -> {
            System.out.println("supplier");
            return "a[10] = " + a[10];
        });
    }

    public static void info(final Logger logger, final Supplier<String> message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(message);
        }
    }

    public static void logIf(
            final Level level,
            final BooleanSupplier condition,
            final Supplier<String> message) {
        if (level == Level.FINEST || !condition.getAsBoolean()) {
            return;
        }

        System.out.println(message.get());

    }
}
