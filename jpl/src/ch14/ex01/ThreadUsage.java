package ch14.ex01;

public class ThreadUsage extends Thread {
    private String threadName;
    private final int delayTime = 10000;

    public ThreadUsage() {}

    public ThreadUsage(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        try {
            if (threadName != null) {
                Thread.currentThread().setName(threadName);
            }
            System.out.println(Thread.currentThread().getName() + " is running!");
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void main(String[] args) {
        new ThreadUsage().start();
        new ThreadUsage("MyThread").start();
    }
}
