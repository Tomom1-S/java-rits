package ch03.ex03;

import java.util.function.BooleanSupplier;

public class AssertionSample {
    public static void myAssert(final BooleanSupplier condition) {
        if (!Boolean.getBoolean("java.enable.assertions") || condition.getAsBoolean()) {
            return;
        }
        throw new AssertionError();
    }
}
