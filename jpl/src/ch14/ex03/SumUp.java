package ch14.ex03;

public class SumUp implements Runnable {
    private int sum;
    private int addition;

    public SumUp (int addition) {
        sum = 0;
        this.addition = addition;
    }

    public synchronized void add() {
        if (sum >= 100) {
            return;
        }

        final int oldSum = sum;
        sum += addition;
        System.out.println(Thread.currentThread().getName() + ": " + oldSum + " + " + addition + " = " + sum);
    }

    @Override
    public void run() {
        while (sum < 100) {
            add();
        }

    }

    public static void main(String[] args) {
        final Runnable sumUp = new SumUp(5);
        new Thread(sumUp).start();
        new Thread(sumUp).start();
        new Thread(sumUp).start();
    }
}
