package ch05.ex05;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PassedDaysTest {
    final LocalDate from = LocalDate.of(1936, 2, 6);
    // expected の値は下記サイトで算出
    // https://www.nannichime.net

    @Test
    public void countDaysで会社の設立日からの日数を数える() {
        final long actual = PassedDays.countDays(from);
        final Duration duration = Duration.between(
                from.atTime(0, 0, 0),
                LocalDateTime.now());
        assertThat(actual, is(duration.toDays()));
    }

    @Test
    public void countDaysで自分の誕生日からJava研修の日までの日数を数える() {
        final long actual = PassedDays.countDays(from,
                LocalDate.of(2020, 12, 18));
        assertThat(actual, is(30997L));
    }
}