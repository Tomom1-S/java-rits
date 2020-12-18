package ch05.ex12;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppointmentTest {
    @Test
    public void toStringのテスト() {
        final Appointment appointment = new Appointment("Java Course", ZonedDateTime.of(
                LocalDate.of(2020, 12, 18), LocalTime.of(9, 30),
                ZoneId.systemDefault()));
        final String actual = appointment.toString();

        assertThat(actual, is("2020-12-18 9:30 Java Course"));
    }

    @Test
    public void updateNameのテスト() {
        final Appointment appointment = new Appointment("Java Course", ZonedDateTime.of(
                LocalDate.of(2020, 12, 18), LocalTime.of(9, 30),
                ZoneId.systemDefault()));
        appointment.updateName("Java Course 15");
        final String actual = appointment.toString();

        assertThat(actual, is("2020-12-18 9:30 Java Course 15"));
    }

    @Test
    public void updateTimeのテスト() {
        final Appointment appointment = new Appointment("Java Course", ZonedDateTime.of(
                LocalDate.of(2020, 12, 18), LocalTime.of(9, 30),
                ZoneId.systemDefault()));
        appointment.updateTime(ZonedDateTime.of(
                LocalDate.of(2021, 1, 15), LocalTime.of(9, 30),
                ZoneId.systemDefault()));
        final String actual = appointment.toString();

        assertThat(actual, is("2021-01-15 9:30 Java Course"));
    }
}