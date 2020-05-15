package ch14.ex06;

public class NotifierHandler {
    public static class TimeNotifier implements Runnable {
        private final Object lock;
        final Thread thread;
        private final long start;
        private final int delay = 1000; // interval [msec]

        public TimeNotifier(final Object lock) {
            this.lock = lock;
            start = System.currentTimeMillis();

            thread = new Thread(this);
            // 柴田さん：コンストラクタ内でスレッドを開始する場合は最後に実行する
            // lock が設定される前に、run 内の synchronized(lock) が呼ばれると NPE になる
            thread.start();
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
                    // 柴田さん：このとき this のロックを獲得する必要はない
                    lock.notifyAll();
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
            this.lock = lock;
            this.msg = msg;
            this.interval = interval;

            thread = new Thread(this);
            // 柴田さん：コンストラクタ内でスレッドを開始する場合は最後に実行する
            // lock が設定される前に、run 内の synchronized(lock) が呼ばれると NPE になる
            thread.start();
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
                        // 柴田さん：このとき this のロックを獲得する必要はない
                        lock.wait();
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
