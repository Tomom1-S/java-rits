package ch05.ex08;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeOffset {
    public static void main(final String[] _args) {
        ZoneId.getAvailableZoneIds().stream()
                .sorted()   // ID を昇順に並び替え
                .peek(id -> System.out.print(id + " "))
                .map(id -> ZoneId.of(id))
                .map(zone -> ZonedDateTime.now(zone))
                .map(time -> time.getOffset())
                .forEach(System.out::println);
    }
}
