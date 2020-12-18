package ch05.ex09;

import java.time.*;
import java.util.Set;
import java.util.stream.Stream;

public class FilteredTimeOffset {
    public static void main(final String[] args) {
        final LocalDateTime now = LocalDateTime.now();
        final ZonedDateTime utc = ZonedDateTime.of(now, ZoneOffset.UTC);

        final Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        final Stream<String> stream = zoneIds.stream();
        stream.map(id -> ZoneId.of(id))
                .map(zone -> ZonedDateTime.of(now, zone))
                // 分以下の情報が含まれるものだけを残す
                .filter(time -> Duration.between(time, utc).toMinutes() % 60 != 0)
                .peek(time ->
                        System.out.print(time.getZone().getId() + ": ")
                )
                .map(time -> Duration.between(time, utc))
                .map(diff -> formatDuration(diff))
                .forEach(System.out::println);
    }

    private static String formatDuration(final Duration duration) {
        return String.format("%d:%2d",
                duration.toHours(), Math.abs(duration.toMinutes() % 60));
    }
}
