package ch05.ex02;

import java.time.LocalDate;

public class PlusYearTest {
    public static void main(final String[] _args) {
        final LocalDate date = LocalDate.of(2000, 2, 29);

        System.out.println("date: " + date);
        System.out.println("date.plusYears(1): " + date.plusYears(1));
        System.out.println("date.plusYears(4): " + date.plusYears(4));
        System.out.println("date.plusYears(1) x 4: " +
                date.plusYears(1)
                        .plusYears(1)
                        .plusYears(1)
                        .plusYears(1)
        );
    }
}
