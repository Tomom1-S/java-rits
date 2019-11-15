package ch10.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import ch06.ex01.EnumEx;
import org.junit.Test;

public class WorkDayJudgementTest {

    @Test
    public void isWorkDayIfが正しい値を返す() {
        WorkDayJudgement wdj = new WorkDayJudgement();

        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.MONDAY), is(true));
        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.TUESDAY), is(true));
        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.WEDNESDAY), is(true));
        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.THURSDAY), is(true));
        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.FRIDAY), is(true));
        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.SATURDAY), is(false));
        assertThat(wdj.isWorkDayIf(EnumEx.DayOfWeek.SUNDAY), is(false));
    }

    @Test
    public void isWorkDaySwitchが正しい値を返す() {
        WorkDayJudgement wdj = new WorkDayJudgement();

        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.MONDAY), is(true));
        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.TUESDAY), is(true));
        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.WEDNESDAY), is(true));
        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.THURSDAY), is(true));
        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.FRIDAY), is(true));
        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.SATURDAY), is(false));
        assertThat(wdj.isWorkDaySwitch(EnumEx.DayOfWeek.SUNDAY), is(false));
    }
}