package ch22.ex14;

import ch20.ex01.TranslateByteExecutor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FloatNumberSumTest {

    @Test
    public void 浮動小数点数字を合計する() {
        final float expected = new FloatNumberSum().add("12345.6789 9.87654321 5678.90123");
        final float actual = 18034.457F;

        assertThat(actual, is(expected));
    }

    @Test
    public void 不正な入力で例外() {
        try {
            new FloatNumberSum().add("12345.6789 foo 5678.90123");
        } catch (NumberFormatException expected) {
            assertThat(expected.getMessage(), is("For input string: \"foo\""));
        }
    }

}