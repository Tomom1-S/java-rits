package ch05.ex12;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements Comparable<Appointment> {
    private String name;
    private ZonedDateTime time;

    public Appointment(final String name, final ZonedDateTime time) {
        this.name = name;
        this.time = time;
    }

    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return String.format("%s %d:%02d %s", formatter.format(time), time.getHour(), time.getMinute(), name);
    }

    public String getName() {
        return this.name;
    }

    public ZonedDateTime getTime() {
        return this.time;
    }

    public void updateName(final String name) {
        this.name = name;
    }

    public void updateTime(final ZonedDateTime time) {
        this.time = time;
    }

    @Override
    public int compareTo(Appointment that) {
        return time.compareTo(that.time);
    }
}
