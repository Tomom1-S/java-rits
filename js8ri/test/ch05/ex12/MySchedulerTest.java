package ch05.ex12;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.*;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MySchedulerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    final LocalDateTime now = LocalDateTime.now();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void addとgetの正常系() {
        final MyScheduler scheduler = new MyScheduler();
        final LocalDateTime fooTime = now.plusHours(1);
        scheduler.addAppointment("Foo",
                ZonedDateTime.of(fooTime, ZoneId.systemDefault())
        );
        final LocalDateTime barTime = now.plusMinutes(30);
        scheduler.addAppointment("Bar",
                ZonedDateTime.of(barTime, ZoneId.systemDefault())
        );
        final LocalDateTime bazTime = now.plusDays(7);
        scheduler.addAppointment("Baz",
                ZonedDateTime.of(bazTime, ZoneId.systemDefault())
        );
        final Set<Appointment> appointments = scheduler.getAppointments();
        appointments.stream().map(Appointment::toString).forEach(System.out::println);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = String.format(
                "%d-%02d-%02d %02d:%02d Bar" + ls
                        + "%d-%02d-%02d %02d:%02d Foo" + ls
                        + "%d-%02d-%02d %02d:%02d Baz" + ls,
                barTime.getYear(), barTime.getMonth().getValue(), barTime.getDayOfMonth(),
                barTime.getHour(), barTime.getMinute(),
                fooTime.getYear(), fooTime.getMonth().getValue(), fooTime.getDayOfMonth(),
                fooTime.getHour(), fooTime.getMinute(),
                bazTime.getYear(), bazTime.getMonth().getValue(), bazTime.getDayOfMonth(),
                bazTime.getHour(), bazTime.getMinute()
        );
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void addに過去の時間を渡すと例外となる() {
        final MyScheduler scheduler = new MyScheduler();
        final LocalDateTime fooTime = now.minusHours(1);

        try {
            scheduler.addAppointment("Foo",
                    ZonedDateTime.of(fooTime, ZoneId.systemDefault())
            );
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(),
                    is("time should be future time!"));
        }
    }

    @Test
    public void systemDefaultのタイムゾーンの時刻で登録したスケジュールをstartNotificationで通知() {
        final MyScheduler scheduler = new MyScheduler();
        final LocalDateTime fooTime = now.plusHours(1);
        scheduler.addAppointment("Foo",
                ZonedDateTime.of(fooTime, ZoneId.systemDefault())
        );
        final LocalDateTime barTime = now.plusMinutes(30);
        scheduler.addAppointment("Bar",
                ZonedDateTime.of(barTime, ZoneId.systemDefault())
        );

        scheduler.startNotification();

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = String.format(
                "%d-%02d-%02d %02d:%02d Bar" + ls,
                barTime.getYear(), barTime.getMonth().getValue(), barTime.getDayOfMonth(),
                barTime.getHour(), barTime.getMinute()
        );
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void systemDefaultでないタイムゾーンの時刻で登録したスケジュールをstartNotificationで通知() {
        final MyScheduler scheduler = new MyScheduler();
        final LocalDateTime fooTime = now.plusHours(1);
        scheduler.addAppointment("Foo",
                ZonedDateTime.of(fooTime, ZoneOffset.UTC)
        );
        final LocalDateTime barTime = now.minusHours(9).plusMinutes(30);
        scheduler.addAppointment("Bar",
                ZonedDateTime.of(barTime, ZoneOffset.UTC)
        );

        scheduler.startNotification();

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = String.format(
                "%d-%02d-%02d %02d:%02d Bar" + ls,
                barTime.getYear(), barTime.getMonth().getValue(), barTime.getDayOfMonth(),
                barTime.getHour(), barTime.getMinute()
        );
        assertThat(outContent.toString(), is(expected));
    }
}