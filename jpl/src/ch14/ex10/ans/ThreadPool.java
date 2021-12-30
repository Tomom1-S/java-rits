/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */

package ch14.ex10.ans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 *
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

    private final List<Runnable> queue;
    private final Runnable END = new Runnable() {
        public void run() {
        }
    };
    private final Thread[] executors;
    private boolean started = false;

    private class Executor implements Runnable {

        public void run() {
            while (true) {
                Runnable r = null;
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    r = queue.remove(0);
                }
                if (r == END) {
                    return;
                }

                r.run();
            }

        }
    }

    /**
     * Constructs ThreadPool.
     *
     * @param queueSize the max size of queue
     * @param numberOfThreads the number of threads in this pool.
     *
     * @throws IllegalArgumentException if either queueSize or numberOfThreads
     * is less than 1
     */
    public ThreadPool(int queueSize, int numberOfThreads) {
        if (queueSize < 1) {
            throw new IllegalArgumentException(String.format("queueSize(%d) must be greater than 0", queueSize));
        }

        if (numberOfThreads < 1) {
            throw new IllegalArgumentException(String.format("numberOfThreads(%d) must be greater than 0", numberOfThreads));
        }

        queue = new ArrayList<>(queueSize);
        executors = new Thread[numberOfThreads];
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public synchronized void start() {
        if (started) {
            throw new IllegalStateException("Alreay started");
        }

        for (int i=0; i < executors.length; i++) {
            executors[i] = new Thread(new Executor());
            executors[i].start();
        }
        started = true;
    }

    /**
     * Stop all threads and wait for their terminations.
     *
     * @throws IllegalStateException if threads has not been started.
     */
    public synchronized void stop() {
        if (!started) {
            throw new IllegalStateException("Not Started");
        }

        for (int i= 0; i < executors.length; i++) {
            synchronized(queue) {
                queue.add(END);
                queue.notifyAll();
            }
        }

        for (Thread t: executors) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        started = false;
    }

    /**
     * Executes the specified Runnable object, using a thread in the pool. run()
     * method will be invoked in the thread. If the queue is full, then this
     * method invocation will be blocked until the queue is not full.
     *
     * @param runnable Runnable object whose run() method will be invoked.
     *
     * @throws NullPointerException if runnable is null.
     * @throws IllegalStateException if this pool has not been started yet.
     */
    public synchronized void dispatch(Runnable runnable) {
        if (!started) {
            throw new IllegalStateException("Not Started Yet");
        }
        if (runnable == null)
            throw new NullPointerException("runnable is null");

        synchronized (queue) {
            queue.add(runnable);
            queue.notifyAll();
        }
    }
}

