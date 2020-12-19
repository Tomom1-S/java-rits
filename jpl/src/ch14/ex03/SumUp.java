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

    public synchronized int getSum() {
        return sum;
    }

    @Override
    public void run() {
        // 柴田さん
        // 値を取り出すときも同期しないと、最新の値を使って判断できない
        while (getSum() < 100) {
            add();

            // sleep しないと正しく複数スレッドで処理できているか確認できない
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        final Runnable sumUp = new SumUp(5);
        new Thread(sumUp).start();
        new Thread(sumUp).start();
        new Thread(sumUp).start();
    }
}
