package ch14.ex09;

public class Hello extends Thread {
    private final int delay = 1000;

    public Hello(ThreadGroup group) {
        super(group, new Thread());
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Hello - " + i);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}