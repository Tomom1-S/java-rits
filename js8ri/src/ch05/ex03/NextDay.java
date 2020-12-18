package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

public class NextDay {
    public static TemporalAdjuster next(
            final Predicate<LocalDate> predicate) {
        return temporal -> {
            LocalDate result = (LocalDate) temporal;
            while (!predicate.test(result)) {
                result = result.plusDays(1);
            }
            return result;
        };
    }
}
