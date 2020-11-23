package ch05.ex01;

import java.time.LocalDate;

public class ProgrammersDay {
    static final int days = 256;

    public static void main(final String[] args) {
        final int year;
        if (args.length > 0) {
            year = Integer.parseInt(args[0]);
        } else {
            // 引数がなければ今年のプログラマーの日を計算
            year = LocalDate.now().getYear();
        }

        System.out.println("Programmer's Day: " + calcProgrammersDay(year));
    }

    private static LocalDate calcProgrammersDayByPlusDays(final int inYear) {
        return LocalDate.of(inYear, 1, 1).plusDays(days - 1);
    }

    private static LocalDate calcProgrammersDay(final int inYear) {
        return LocalDate.of(inYear, 1, 1).withDayOfYear(256);
    }
}
