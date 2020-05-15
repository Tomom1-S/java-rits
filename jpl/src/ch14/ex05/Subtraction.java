package ch14.ex05;

public class Subtraction implements Runnable {
    private int diff;
    private int subtrahend;
    protected final Object lock = new Object();

    public Subtraction(int addition) {
        diff = 0;
        this.subtrahend = addition;
    }

    public void subtract() {
        synchronized (lock) {
            if (diff <= -100 || diff >= 100) {
                return;
            }

            final int oldDiff = diff;
            diff -= subtrahend;
            System.out.println(Thread.currentThread().getName() + ": " + oldDiff + " + " + subtrahend + " = " + diff);
        }
    }

    @Override
    public void run() {
        while (diff > -100 && diff < 100) {
            subtract();
        }

    }

    public static void main(String[] args) {
        Runnable subtraction = new Subtraction(5);
        new Thread(subtraction).start();
        new Thread(subtraction).start();
        new Thread(subtraction).start();
    }
}
