package ch10.ex03;

import ch06.ex01.EnumEx;

public class WorkDayJudgement {
    public boolean isWorkDayIf(EnumEx.DayOfWeek day) {
        if (day == EnumEx.DayOfWeek.MONDAY || day == EnumEx.DayOfWeek.TUESDAY ||
                day == EnumEx.DayOfWeek.WEDNESDAY || day == EnumEx.DayOfWeek.THURSDAY ||
                day == EnumEx.DayOfWeek.FRIDAY) {
            return true;
        } else if (day == EnumEx.DayOfWeek.SATURDAY || day == EnumEx.DayOfWeek.SUNDAY) {
            return false;
        } else {
            throw new IllegalArgumentException(String.valueOf(day));
        }
    }

    public boolean isWorkDaySwitch(EnumEx.DayOfWeek day) {
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return true;
            case SATURDAY:
            case SUNDAY:
                return false;
            default:
                throw new IllegalArgumentException(String.valueOf(day));
        }
    }
}
