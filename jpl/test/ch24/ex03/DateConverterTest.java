package ch24.ex03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DateConverterTest {
    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    public void setUp() {
        defaultPrintStream = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(defaultPrintStream);
    }

    @Test
    public void mainの結果を表示() {
        try {
            DateConverter.main(new String[]{"2020/06/19"});
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("6/19/20\nJun 19, 2020\nJune 19, 2020\nFriday, June 19, 2020\n"));
    }

    @Test
    public void isLenientがtrueのとき存在しない日付に対してmainの結果を表示() {
        try {
            DateConverter.main(new String[]{"2020/13/19"});
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("1/19/21\nJan 19, 2021\nJanuary 19, 2021\nTuesday, January 19, 2021\n"));
    }

    @Test
    public void isLenientがfalseのとき存在しない日付に対してmainの結果を表示() {
        try {
            DateConverter.main(new String[]{"2020/13/19", "false"});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Unparseable date: \"2020/13/19\""));
        }
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            DateConverter.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("One or two arguments are needed!"));
        }
    }

}