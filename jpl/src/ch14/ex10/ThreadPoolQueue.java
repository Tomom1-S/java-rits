package ch14.ex10;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolQueue {
    private LinkedBlockingQueue<Runnable> queue;
    private int maxCapacity;

    public ThreadPoolQueue(int maxCapacity) {
        this.queue = new LinkedBlockingQueue(maxCapacity);
        this.maxCapacity = maxCapacity;
    }

    public synchronized void add(Runnable runnable) {
        while (queue.size() > maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        try {
            queue.put(runnable);
        } catch (InterruptedException e) {}
        notifyAll();
    }

    public synchronized Runnable remove() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        return queue.take();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
