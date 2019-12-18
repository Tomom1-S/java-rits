package ch13.ex02;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringCounterTest {

    @Test
    public void 文字の出現回数を返す() throws Exception {
        String str = "If you can dream it, you can do it.";
        StringCounter sc = new StringCounter(str);

        assertThat(sc.countString("you"), is(2));
    }

    @Test
    public void 重複のある文字列内の文字の出現回数を返す() throws Exception {
        String str = "popopopopop";
        StringCounter sc = new StringCounter(str);

        assertThat(sc.countString("pop"), is(5));
    }

    @Test
    public void 見つからないときは例外を出す() throws Exception {
        String str = "Happy Birthday!";
        StringCounter sc = new StringCounter(str);

        try {
            sc.countString("abc");

        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("abc is not found in Happy Birthday!"));
        }
    }

}