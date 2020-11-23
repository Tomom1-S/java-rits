package ch05.ex07;

import java.time.LocalDateTime;

public class TimeInterval {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public TimeInterval(final LocalDateTime from, final LocalDateTime to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("'to' should be the date after 'from'!");
        }

        this.from = from;
        this.to = to;
    }

    public boolean isOverlapped(final TimeInterval that) {
        final LocalDateTime left = from.isAfter(that.from) ? from : that.from;
        final LocalDateTime right = to.isBefore(that.to) ? to : that.to;
        return left.isBefore(right);
    }
}
