package ch05.ex07;

import java.time.LocalDateTime;

public class TimeInterval {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public TimeInterval(final LocalDateTime from, final LocalDateTime to) {
        // 柴田さん：from, to の null チェックが必要
        if (from == null) {
            throw new IllegalArgumentException("'from' is null!");
        }
        if (to == null) {
            throw new IllegalArgumentException("'to' is null!");
        }

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("'to' should be the date after 'from'!");
        }

        this.from = from;
        this.to = to;
    }

    // 柴田さん：境界が含まれるのかを仕様として明記しておくことが大事
    // 境界のテストを忘れずに
    public boolean isOverlapped(final TimeInterval that) {
        final LocalDateTime left = from.isAfter(that.from) ? from : that.from;
        final LocalDateTime right = to.isBefore(that.to) ? to : that.to;
        return left.isBefore(right);
    }
}
