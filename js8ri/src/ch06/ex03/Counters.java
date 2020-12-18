package ch06.ex03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Counters {
    private static final int THREAD_NUM = 1000;
    private static final int MAX_COUNT = 100000;

    public static void main(final String[] _args) {
        System.out.println("-----AtomicLongを使ったカウンタ-----");
        countWithAtomicLong();

        System.out.println();

        System.out.println("-----LongAdderを使ったカウンタ-----");
        countWithLongAdder();
    }

    public static void countWithAtomicLong() {
        final AtomicLong atomicLong = new AtomicLong();
        final CountDownLatch latch = new CountDownLatch(THREAD_NUM);

        final ExecutorService pool = Executors.newCachedThreadPool();
        final long start = System.nanoTime();
        for (int i = 0; i < THREAD_NUM; i++) {
            pool.submit(() -> {
                for (int j = 0; j < MAX_COUNT; j++) {
                    atomicLong.incrementAndGet();
                }
                latch.countDown();
            });
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("経過時間: " + (System.nanoTime() - start) / 1E9 + " sec");
        System.out.println("結果: " + atomicLong.get());
    }

    public static void countWithLongAdder() {
        final LongAdder longAdder = new LongAdder();
        final CountDownLatch latch = new CountDownLatch(THREAD_NUM);

        final ExecutorService pool = Executors.newCachedThreadPool();
        final long start = System.nanoTime();
        for (int i = 0; i < THREAD_NUM; i++) {
            pool.submit(() -> {
                for (int j = 0; j < MAX_COUNT; j++) {
                    longAdder.increment();
                }
                latch.countDown();
            });
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("経過時間: " + (System.nanoTime() - start) / 1E9 + " sec");
        System.out.println("結果: " + longAdder.sum());
    }
}
