package ch14.ex06;

public class NotifierHandler {
    public static class TimeNotifier implements Runnable {
        private final Object lock;
        final Thread thread;
        private final long start;
        private final int delay = 1000; // interval [msec]

        public TimeNotifier(final Object lock) {
            thread = new Thread(this);
            thread.start();

            this.lock = lock;
            start = System.currentTimeMillis();
        }

        @Override
        public void run() {
            while (!thread.isInterrupted()) {
                try {
                    thread.sleep(delay);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }

                Long now = System.currentTimeMillis();
                System.out.println("Timer: " + (now - start) / 1000 + " sec");

                synchronized (lock) {
                    synchronized (this) {
                        lock.notifyAll();
                    }
                }
            }
        }
    }

    public static class MessageNotifier implements Runnable {
        private final Object lock;
        private final Thread thread;
        private final String msg;
        private final int interval; // sec
        private int count = 0;

        public MessageNotifier(final Object lock, final String msg, final int interval) {
            thread = new Thread(this);
            thread.start();

            this.lock = lock;
            this.msg = msg;
            this.interval = interval;
        }

        @Override
        public void run() {
            while (!thread.isInterrupted()) {
                if (count == interval) {
                    System.out.println(msg);
                    count = 0;
                }

                count++;
                try {
                    synchronized (lock) {
                        synchronized (this) {
                            lock.wait();
                        }
                    }
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(final String[] args) {
        final Object lock = new Object();
        new TimeNotifier(lock);
        new MessageNotifier(lock, "\tEvery 15sec message", 15);
        new MessageNotifier(lock, "\tEvery 7sec message", 7);
    }
}
