package ch05.ex11;

import ch05.ex10.ArrivalTimeCalculator;
import org.junit.Test;

import java.time.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FlightTimeCalculatorTest {
    @Test
    public void calcFlightTimeの正常系() {
        // フランクフルトのタイムゾーンは中央ヨーロッパ標準時
        final ZoneId frankfurt = ZoneId.of("CET");
        final Duration actual = FlightTimeCalculator.calcFlightTime(
                frankfurt, ZoneId.of("America/Los_Angeles"),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 05)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 40))
        );
        final Duration expected = Duration.ofMinutes(11 * 60 + 35);
        assertThat(actual, is(expected));
    }
}