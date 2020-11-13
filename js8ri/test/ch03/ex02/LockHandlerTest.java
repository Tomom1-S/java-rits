package ch03.ex02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LockHandlerTest {

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
    public void withLockの正常系() {
        final ReentrantLock lock = new ReentrantLock();
        LockHandler.withLock(lock, () -> {
            System.out.println("Do something");
        });

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "Do something" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    // TODO このテストが通らない
    @Test
    public void withLock() {
        final ReentrantLock lock = new ReentrantLock();

        new Thread(() -> lock.lock()).start();

        new Thread(() -> {
            LockHandler.withLock(lock, () -> {
                System.out.println("Do something");
            });
        }).start();

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "Do something" + ls;
        assertThat(outContent.toString(), is(expected));
    }

}