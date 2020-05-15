package ch14.ex02;

import java.awt.*;

public class PrintServer implements Runnable {
    private final PrintQueue requests = new PrintQueue();
    private final Thread thread;

    public PrintServer() {
        thread = new Thread(this);
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    public void print(PrintJob job) {
        requests.add(job);
    }

    @Override
    public void run() {
        if (thread != Thread.currentThread()) {
            // 柴田さん：run が RunTimeException をスローする
            // テストするときは別のスレッドを立ち上げて直接 run を呼ぶ
            throw new java.lang.RuntimeException("This thread cannot execute run()");
        }

        for (; ; ) {
            try {
                realPrint(requests.remove());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void realPrint(PrintJob job) {
        // 印刷の実際の処理を行う
    }
}
