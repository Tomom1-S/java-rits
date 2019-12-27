package ch13.ex06;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class delimitedStringNumberTest {
    private final int digit = 5;

    @Test
    public void digit桁区切りで区切り文字を入れる() {
        String string = "1234567890";
        delimitedStringNumber dsn = new delimitedStringNumber();

        assertThat(dsn.addComma(string, '.', digit), is("12345.67890"));
    }

    @Test
    public void digit桁以下のときは何もしない() {
        String string = "12345";
        delimitedStringNumber dsn = new delimitedStringNumber();

        assertThat(dsn.addComma(string, '.', digit), is("12345"));
    }

    @Test
    public void 数字以外が含まれたら例外を投げる() {
        String string = "123abc456";
        delimitedStringNumber dsn = new delimitedStringNumber();

        try {
            dsn.addComma(string, '.', digit);
        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("Only numbers can be accepted!"));
        }
    }

    @Test
    public void digitが負の数なら例外を投げる() {
        String string = "1234";
        delimitedStringNumber dsn = new delimitedStringNumber();

        try {
            dsn.addComma(string, '.', -3);
        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("Digit should be positive!"));
        }
    }

}