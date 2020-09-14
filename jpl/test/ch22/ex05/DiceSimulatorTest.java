package ch22.ex05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiceSimulatorTest {

    private static final Map<Integer, Double> expected2Dices = new LinkedHashMap<>();

    @BeforeClass
    public static void setup() {
        // initialize expected2Dices
        expected2Dices.put(2, (double) 1 / 36);
        expected2Dices.put(3, (double) 2 / 36);
        expected2Dices.put(4, (double) 3 / 36);
        expected2Dices.put(5, (double) 4 / 36);
        expected2Dices.put(6, (double) 5 / 36);
        expected2Dices.put(7, (double) 6 / 36);
        expected2Dices.put(8, (double) 5 / 36);
        expected2Dices.put(9, (double) 4 / 36);
        expected2Dices.put(10, (double) 3 / 36);
        expected2Dices.put(11, (double) 2 / 36);
        expected2Dices.put(12, (double) 1 / 36);

        System.out.println(expected2Dices.values());
    }

    @Test
    public void サイコロを2個を10000回投げたとき1() {
        DiceSimulator ds = new DiceSimulator(123456789L);
        Map<Integer, Double> actual = ds.simulateTotalValue(2, 10000, false);
        System.out.println(actual.values());

        actual.forEach((k, v) -> {
            final double expected = expected2Dices.get(k);
            Assert.assertEquals(expected, v, 0.01);
        });
    }

    @Test
    public void サイコロを2個を10000回投げたとき2() {
        DiceSimulator ds = new DiceSimulator(987654321L);
        Map<Integer, Double> actual = ds.simulateTotalValue(2, 10000, false);
        System.out.println(actual.values());

        actual.forEach((k, v) -> {
            final double expected = expected2Dices.get(k);
            Assert.assertEquals(expected, v, 0.01);
        });
    }

    @Test
    public void サイコロを2個を10000回投げたとき_Mathを使う() {
        DiceSimulator ds = new DiceSimulator();
        Map<Integer, Double> actual = ds.simulateTotalValue(2, 10000, true);
        System.out.println(actual.values());

        actual.forEach((k, v) -> {
            final double expected = expected2Dices.get(k);
            Assert.assertEquals(expected, v, 0.01);
        });
    }

}