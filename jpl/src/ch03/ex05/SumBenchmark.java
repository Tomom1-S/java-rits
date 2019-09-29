package ch03.ex05;

public class SumBenchmark extends Benchmark {
    static int value;
    static int sum = 0;

    void benchmark() {
        for (int i = 0; i < value; i++) {
            sum += i;
        }
    }

    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        value = Integer.parseInt(args[1]);
        long time = new SumBenchmark().repeat(count);
        System.out.println(count + " methods in " + time + "nanoseconds");
    }
}
