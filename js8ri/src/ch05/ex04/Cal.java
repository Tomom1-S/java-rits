package ch05.ex04;

import java.time.LocalDate;
import java.time.YearMonth;

public class Cal {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("2 arguments are needed!");
        }

        final YearMonth yearMonth = YearMonth.of(
                Integer.parseInt(args[1]),
                Integer.parseInt(args[0])
        );
        LocalDate day = yearMonth.atDay(1);

        // 1の開始位置までスペースで埋める
        for (int i = 0; i < day.getDayOfWeek().getValue() - 1; i++) {
            System.out.print("   ");
        }

        while (!day.isAfter(yearMonth.atEndOfMonth())) {
            System.out.printf("%2d", day.getDayOfMonth());

            if (day.getDayOfWeek().getValue() == 7) {
                System.out.println();
            } else {
                System.out.print(" ");
            }

            day = day.plusDays(1);
        }
        System.out.println();
    }
}
