package ch24.ex01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GlobalHelloTest {
    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

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


    // ListResourceBundle で設定したロケール
    @Test
    public void PropertyResourceListのen_AUでGoodbye() {
        Locale.setDefault(new Locale("en", "AU"));
        GlobalHello.main(new String[]{"foo"});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("G'day" + ls));
    }

    @Test
    public void PropertyResourceListのen_AUでHello() {
        Locale.setDefault(new Locale("en", "AU"));
        GlobalHello.main(new String[]{});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("Hello" + ls));
    }

    @Test
    public void PropertyResourceListのen_USでGoodbye() {
        Locale.setDefault(new Locale("en", "US"));
        GlobalHello.main(new String[]{"foo"});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("Goodbye" + ls));
    }

    @Test
    public void PropertyResourceListのen_USでHello() {
        Locale.setDefault(new Locale("en", "US"));
        GlobalHello.main(new String[]{});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("Hello" + ls));
    }

    @Test
    public void PropertyResourceListのsq_ALでGoodbye() {
        Locale.setDefault(new Locale("sq", "AL"));
        GlobalHello.main(new String[]{"foo"});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("Ciao" + ls));
    }

    @Test
    public void PropertyResourceListのsq_ALでHello() {
        Locale.setDefault(new Locale("sq", "AL"));
        GlobalHello.main(new String[]{});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("Ciao" + ls));
    }

    // .properties ファイルで設定したロケール
    @Test
    public void propertiesファイルのja_JPでGoodbye() {
        Locale.setDefault(new Locale("ja", "JP"));
        GlobalHello.main(new String[]{"foo"});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("さようなら" + ls));
    }

    @Test
    public void propertiesファイルのja_JPでHello() {
        Locale.setDefault(new Locale("ja", "JP"));
        GlobalHello.main(new String[]{});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("こんにちは" + ls));
    }

    // TODO ここから下のテストが通らないので修正
    // ResourceBundle のサブクラスで設定したロケール
    @Test
    public void propertiesファイルのzh_TWでGoodbye() {
        Locale.setDefault(new Locale("zh", "TW"));
        GlobalHello.main(new String[]{"foo"});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("さようなら" + ls));
    }

    @Test
    public void propertiesファイルのzh_TWでHello() {
        Locale.setDefault(new Locale("zh", "TW"));
        GlobalHello.main(new String[]{});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, is("こんにちは" + ls));
    }
}