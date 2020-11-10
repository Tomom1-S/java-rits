package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

public class LockHandler {

    public static void withLock(final ReentrantLock lock, final Runnable runner) {
        lock.lock();
        try {
            runner.run();
        } finally {
            lock.unlock();
        }

    }

}
