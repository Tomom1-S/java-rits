package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PassedDays {
    public static long countDays(
            final LocalDate from) {
        return from.until(LocalDate.now(), ChronoUnit.DAYS);
    }

    public static long countDays(
            final LocalDate from,
            final LocalDate to) {
        return from.until(to, ChronoUnit.DAYS);
    }
}
