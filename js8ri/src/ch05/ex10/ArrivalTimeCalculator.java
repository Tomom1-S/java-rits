package ch05.ex10;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ArrivalTimeCalculator {
    public static ZonedDateTime calcArrivalTime(
            final ZoneId departurePlace,
            final ZoneId arrivalPlace,
            final LocalDateTime departureTime,
            final Duration fightTime
            ) {
        final ZonedDateTime zonedTime = ZonedDateTime.of(departureTime, departurePlace);
        return zonedTime.plus(fightTime).withZoneSameInstant(arrivalPlace);
    }
}
