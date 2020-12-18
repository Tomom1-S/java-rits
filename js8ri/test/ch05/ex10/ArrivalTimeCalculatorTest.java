package ch05.ex10;

import org.junit.Test;

import java.time.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArrivalTimeCalculatorTest {
    @Test
    public void calcArrivalTimeの正常系() {
        // フランクフルトのタイムゾーンは中央ヨーロッパ標準時
        final ZoneId frankfurt = ZoneId.of("CET");
        final ZonedDateTime actual = ArrivalTimeCalculator.calcArrivalTime(
                ZoneId.of("America/Los_Angeles"), frankfurt,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 05)),
                Duration.ofMinutes(10 * 60 + 50)
        );
        final ZonedDateTime expected = ZonedDateTime.of(
                LocalDate.now().plusDays(1),
                LocalTime.of(10, 55), frankfurt);
        assertThat(actual, is(expected));
    }
}