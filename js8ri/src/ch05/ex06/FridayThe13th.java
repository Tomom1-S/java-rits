package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class FridayThe13th {
    public static void main(final String[] args) {
        YearMonth month = YearMonth.of(1901, Month.JANUARY);
        final YearMonth lastMonth = YearMonth.of(2000, Month.DECEMBER);

        while (!month.isAfter(lastMonth)) {
            final LocalDate day = month.atDay(13);
            if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(day);
            }
            month = month.plusMonths(1);
        }
    }
}
