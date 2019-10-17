package ch03.ex05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumBenchmarkTest {

    @Test
    void benchmarkで0から与えられた数値までの和が計算できる() {
        SumBenchmark testBM = new SumBenchmark();
        testBM.value = 100;
        testBM.benchmark();

        int expect = 4950;

        assertEquals(testBM.sum, expect);
    }

}
