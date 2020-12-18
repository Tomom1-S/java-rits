package ch05.ex03;

import org.junit.Test;

import java.time.LocalDate;

import static ch05.ex03.NextDay.next;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NextDayTest {

    @Test
    public void nextの正常系() {
        // 金曜日
        final LocalDate today = LocalDate.of(2020, 11, 21);
        final LocalDate actual = today.with(next(w -> w.getDayOfWeek().getValue() < 6));

        // 月曜日
        assertThat(actual, is(LocalDate.of(2020, 11, 23)));
    }

}