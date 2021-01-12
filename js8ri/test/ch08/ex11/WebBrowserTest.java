package ch08.ex11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WebBrowserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void readWithAuthorizationの正常系() throws Exception {
        WebBrowser.readWithAuthorization(
                "http://leggiero.sakura.ne.jp/xxxxbasic_auth_testxxxx/secret/kaiin_page_top.htm",
                "kaiin", "naisho");

        System.out.flush();
        final String expected =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + ls
                        + "<html lang=\"ja\">" + ls
                        + "<head>" + ls
                        + "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=Shift_JIS\">" + ls
                        + "\t<title>秘密の会員ページトップ</title>" + ls
                        + "</head>" + ls
                        + "<body>" + ls
                        + "秘密の会員ページへようこそ！" + ls + ls
                        + "</body>" + ls
                        + "</html>" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 不正なURLを指定するとIOExceptionが発生() throws Exception {
        final String url = "example.com";
        try {
            WebBrowser.readWithAuthorization(
                    url,
                    "username", "password");
        } catch (IOException expected) {
            assertThat(expected.getMessage(),
                    is("no protocol: example.com"));
        }
    }


    @Test
    public void ユーザ名を間違えるとIOExceptionが発生() throws Exception {
        final String url = "http://leggiero.sakura.ne.jp/xxxxbasic_auth_testxxxx/secret/kaiin_page_top.htm";
        try {
            WebBrowser.readWithAuthorization(
                    url,
                    "username", "naisho");
        } catch (IOException expected) {
            assertThat(expected.getMessage(),
                    is("Server returned HTTP response code: 401 for URL: " + url));
        }
    }

    @Test
    public void パスワードを間違えるとIOExceptionが発生() throws Exception {
        final String url = "http://leggiero.sakura.ne.jp/xxxxbasic_auth_testxxxx/secret/kaiin_page_top.htm";
        try {
            WebBrowser.readWithAuthorization(
                    url,
                    "kaiin", "password");
        } catch (IOException expected) {
            assertThat(expected.getMessage(),
                    is("Server returned HTTP response code: 401 for URL: " + url));
        }
    }
}