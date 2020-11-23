package ch05.ex07;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TimeIntervalTest {
    @Test
    public void fromもtoも中にあるときisOverlappedがtrueになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertTrue(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 12, 00, 00),
                LocalDateTime.of(2020, 12, 18, 13, 00, 00)
        )));
    }

    @Test
    public void fromが外でtoが中にあるときisOverlappedがtrueになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertTrue(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 8, 00, 00),
                LocalDateTime.of(2020, 12, 18, 13, 00, 00)
        )));
    }

    @Test
    public void fromが中でtoが外にあるときisOverlappedがtrueになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertTrue(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 12, 00, 00),
                LocalDateTime.of(2020, 12, 18, 20, 00, 00)
        )));
    }

    @Test
    public void fromもtoも外にあるときisOverlappedがtrueになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertTrue(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 17, 17, 00, 00),
                LocalDateTime.of(2020, 12, 18, 20, 00, 00)
        )));
    }

    @Test
    public void 重ならない時間帯を渡すとisOverlappedがfalseになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertFalse(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 17, 9, 30, 00),
                LocalDateTime.of(2020, 12, 17, 17, 30, 00)
        )));
    }

    @Test
    public void 元のfromと渡されたtoが同じときにisOverlappedがfalseになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertFalse(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 17, 17, 30, 00),
                LocalDateTime.of(2020, 12, 18, 9, 30, 00)
        )));
    }

    @Test
    public void 元のtoと渡されたfromが同じときにisOverlappedがfalseになる() {
        final TimeInterval timeInterval = new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                LocalDateTime.of(2020, 12, 18, 17, 30, 00)
        );

        assertFalse(timeInterval.isOverlapped(new TimeInterval(
                LocalDateTime.of(2020, 12, 18, 17, 30, 00),
                LocalDateTime.of(2020, 12, 19, 9, 30, 00)
        )));
    }

    @Test
    public void fromより早い時刻をtoに渡すとコンストラクタが例外を投げる() {
        try {
            new TimeInterval(
                    LocalDateTime.of(2020, 12, 18, 17, 30, 00),
                    LocalDateTime.of(2020, 12, 18, 9, 30, 00)
            );
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(),
                    is("'to' should be the date after 'from'!"));
        }
    }

    @Test
    public void fromと同じ時刻をtoに渡すとコンストラクタが例外を投げる() {
        try {
            new TimeInterval(
                    LocalDateTime.of(2020, 12, 18, 9, 30, 00),
                    LocalDateTime.of(2020, 12, 18, 9, 30, 00)
            );
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(),
                    is("'to' should be the date after 'from'!"));
        }
    }
}