package ch06.ex04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class NumberSearch {
    private static final int THREAD_NUM = 100;
    private static final int MAX_COUNT = 1000;

    public static long findMax(final long[] values) {
        final LongAccumulator accumulator = new LongAccumulator(Math::max, Long.MIN_VALUE);
        final CountDownLatch latch = new CountDownLatch(THREAD_NUM);

        final ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            final int offset = i * MAX_COUNT;
            pool.submit(() -> {
                for (int j = offset; j < Math.min(offset + MAX_COUNT, values.length); j++) {
                    accumulator.accumulate(values[j]);
                }
            });
            latch.countDown();
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

        return accumulator.get();
    }

    public static long findMin(final long[] values) {
        // 柴田さん: values をリストに変換してストリームにする。
        // forEach で accumulator.accumulate を呼び出せば同じことができる。
        // また、スレッドプールを閉じる処理も省けるので、コードがシンプルになる。

        final LongAccumulator accumulator = new LongAccumulator(Math::min, Long.MAX_VALUE);
        final CountDownLatch latch = new CountDownLatch(THREAD_NUM);

        final ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            final int offset = i * MAX_COUNT;
            pool.submit(() -> {
                for (int j = offset; j < Math.min(offset + MAX_COUNT, values.length); j++) {
                    accumulator.accumulate(values[j]);
                }
            });
            latch.countDown();
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

        return accumulator.get();
    }
}
