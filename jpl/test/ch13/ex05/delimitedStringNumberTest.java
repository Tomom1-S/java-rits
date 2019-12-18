package ch13.ex05;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class delimitedStringNumberTest {

    @Test
    public void 三桁区切りでカンマを入れる() {
        String string = "1234567890";
        delimitedStringNumber dsn = new delimitedStringNumber();

        assertThat(dsn.addComma(string), is("1,234,567,890"));
    }

    @Test
    public void 三桁以下のときは何もしない() {
        String string = "123";
        delimitedStringNumber dsn = new delimitedStringNumber();

        assertThat(dsn.addComma(string), is("123"));
    }

    @Test
    public void 数字以外が含まれたら例外を投げる() {
        String string = "123abc456";
        delimitedStringNumber dsn = new delimitedStringNumber();

        try {
            dsn.addComma(string);
        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("Only numbers can be accepted!"));
        }
    }

}