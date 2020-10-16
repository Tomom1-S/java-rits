package ch22.ex02;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ImprovedWhichCharsTest {

    @Test
    public void toStringの正常系() {
        final String str = "Testing 1 2 3";
        final ImprovedWhichChars whichChars = new ImprovedWhichChars(str);

        final String actual = whichChars.toString();
        final String expected = "[ 123Teginst]";
        assertThat(actual, is(expected));
    }

}