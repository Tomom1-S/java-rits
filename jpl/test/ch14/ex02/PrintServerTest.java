package ch14.ex02;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class PrintServerTest {
    private final int MAX_JOB = 10;
    private final PrintJob job = new PrintJob() {
        @Override
        public Graphics getGraphics() {
            return null;
        }

        @Override
        public Dimension getPageDimension() {
            return null;
        }

        @Override
        public int getPageResolution() {
            return 0;
        }

        @Override
        public boolean lastPageFirst() {
            return false;
        }

        @Override
        public void end() {

        }
    };

    @Test
    public void コンストラクタで生成されたスレッドだけがrunを実行できる() {
        Thread thread = new Thread();
        thread.start();
        PrintServer ps = new PrintServer();
        for (int i = 0; i < MAX_JOB; i++) {
            ps.print(job);
        }

    }

    @Test
    public void コンストラクタで生成されていないスレッドはrunを実行できない() {
        PrintServer ps = new PrintServer();
        Thread thread = new Thread(ps);
        for (int i = 0; i < MAX_JOB; i++) {
            ps.print(job);
        }

        try {
            thread.run();
        } catch (RuntimeException expected) {
            assertThat(expected.getMessage(), equalTo("This thread cannot execute run()"));
        }
    }

}