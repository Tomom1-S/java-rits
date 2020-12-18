package ch05.ex11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightTimeCalculator {
    public static Duration calcFlightTime(
            final ZoneId departurePlace,
            final ZoneId arrivalPlace,
            final LocalDateTime departureTime,
            final LocalDateTime arrivalTime
    ) {
        final ZonedDateTime localDepartureTime = ZonedDateTime.of(departureTime, departurePlace);
        final ZonedDateTime localArrivalTime = ZonedDateTime.of(arrivalTime,
                arrivalPlace).withZoneSameInstant(departurePlace);
        return Duration.between(localDepartureTime, localArrivalTime);
    }
}
