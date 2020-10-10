package ch01.ex06;

import java.util.concurrent.Callable;

public class ExceptionHandler {
    public void main(final String[] args) {
        new Thread(uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        })).start();
        // catch (InterruptedException) が必要ありません！

        // Callable<Void> を使った場合
        new Thread(uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
            return null;
        })).start();
    }

    public static Runnable uncheck(RunnableEx runner) {
        return  () -> {
            try {
                runner.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static Runnable uncheck(Callable<Void> runner) {
        return  () -> {
            try {
                runner.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
