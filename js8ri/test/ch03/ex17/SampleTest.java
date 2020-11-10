package ch03.ex17;

import ch03.ex16.ExceptionWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class SampleTest {

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
    public void doInParallelAsyncの正常系() {
        final Runnable first = () -> System.out.println("This is first");
        final Runnable second = () -> System.out.println("This is second");
        final Consumer<Throwable> handler = throwable -> System.out.println(throwable.getMessage());
        Sample.doInParallelAsync(first, second, handler);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected1 = "This is first" + ls;
        final String expected2 = "This is second" + ls;
        assertTrue(outContent.toString().contains(expected1));
        assertTrue(outContent.toString().contains(expected2));
    }

    @Test
    public void doInParallelAsyncでfirstが例外となったとき() {
        final Runnable first = createThrowableRunnable(() -> {
            throw new Exception("This is first error");
        });
        final Runnable second = () -> System.out.println("This is second");
        final Consumer<Throwable> handler = throwable -> System.out.println(throwable.getMessage());
        Sample.doInParallelAsync(first, second, handler);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected1 = "java.lang.Exception: This is first error" + ls;
        final String expected2 = "This is second" + ls;
        assertTrue(outContent.toString().contains(expected1));
        assertTrue(outContent.toString().contains(expected2));
    }

    @Test
    public void doInParallelAsyncでsecondが例外となったとき() {
        final Runnable second = () -> System.out.println("This is first");
        final Runnable first = createThrowableRunnable(() -> {
            throw new Exception("This is second error");
        });
        final Consumer<Throwable> handler = throwable -> System.out.println(throwable.getMessage());
        Sample.doInParallelAsync(first, second, handler);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected1 = "This is first" + ls;
        final String expected2 = "java.lang.Exception: This is second error" + ls;
        assertTrue(outContent.toString().contains(expected1));
        assertTrue(outContent.toString().contains(expected2));
    }

    public Runnable createThrowableRunnable(ThrowableRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new ExceptionWrapper(e);
            }
        };
    }

}