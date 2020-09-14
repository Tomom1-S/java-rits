package ch22.ex06;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GaussianSimulator {
    private final int absMax = 5;

    public void main(final String[] args) {
        final Map<Double, Integer> samples = new LinkedHashMap<>();
        final double interval = 0.5;
        double key = -1 * absMax;
        while (key < absMax) {
            samples.put(key, 0);
            key += interval;
        }

        final int times = 10000000;
        final Random rand = new Random(42L);
        for (int i = 0; i < times; i++) {
            final double value = rand.nextGaussian();

            for (double j = -1 * absMax; j < absMax; j += interval) {
                if (value < j || value >= j + interval) {
                    continue;
                }

                samples.put(j, samples.get(j) + 1);
                break;
            }
        }

        // TODO *でグラフ表示
        System.out.println("-----");
        samples.forEach((k, v) -> {
            if (k >= 0) {
                System.out.print(" ");
            }
            System.out.printf("%2.1f|", k);
            System.out.println(StringUtils.repeat('*', (int) Math.ceil(v / 25000)));
        });
        System.out.println("------");
    }
}
