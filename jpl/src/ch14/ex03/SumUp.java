package ch14.ex03;

public class SumUp implements Runnable {
    private int sum;
    private int addition;
    private int delay;

    public SumUp (int addition, int delay) {
        sum = 0;
        this.addition = addition;
        this.delay = delay;
    }

    public synchronized void add() {
        if (sum >= 100) {
            return;
        }

        int oldSum = sum;
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
        Runnable sumUp = new SumUp(5, 1000);
        new Thread(sumUp).start();
        new Thread(sumUp).start();
        new Thread(sumUp).start();
    }
}
