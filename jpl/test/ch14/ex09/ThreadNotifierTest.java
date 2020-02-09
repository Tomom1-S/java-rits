package ch14.ex09;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ThreadNotifierTest {

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

    @Test
    public void showでグループの情報を表示() {
        ThreadNotifier tn = new ThreadNotifier(new ThreadGroup("Test").getParent());
        tn.show();

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String[] expected = new String[]{"main has", "Parent: system"};
        assertThat(actual, containsString(expected[0]));
        assertThat(actual, containsString(expected[1]));
    }

    @Test
    public void 複数グループ内に対してshowが正しく表示される() {
        ThreadGroup group = new ThreadGroup("Test");

        Thread notifier = new Thread(new ThreadNotifier(group));
        notifier.start();

        for (int i = 0; i < 10; i++) {
            Hello thread = new Hello(group);
            new Thread(thread).start();
        }

        notifier.interrupt();

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "main has 2 threads." + ls;
        assertThat(actual, is(expected));
    }

}