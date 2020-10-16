package ch22.ex05;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DiceSimulator {
    final int diceMax = 6;
    final Random rand;

    public DiceSimulator() {
        rand = new Random();
    }

    public DiceSimulator(final long seed) {
        rand = new Random(seed);
    }

    public Map<Integer, Double> simulateTotalValue(final int diceNum, final int times, final boolean useMath) {
        final Map<Integer, Integer> totals = initializeTotals(diceNum);
        for (int i = 0; i < times; i++) {
            final int sum = sumDices(diceNum, useMath);
            totals.put(sum, totals.get(sum) + 1);
        }

        return calculateProb(times, totals);
    }

    private Map<Integer, Double> calculateProb(int times, Map<Integer, Integer> totals) {
        final Map<Integer, Double> result = new LinkedHashMap<>();
        totals.forEach((k, v) -> {
            result.put(k, (double) v / times);
        });
        return result;
    }

    private Map<Integer, Integer> initializeTotals(int diceNum) {
        final Map<Integer, Integer> result = new LinkedHashMap<>();
        for (int i = diceNum; i <= diceMax * diceNum; i++) {
            result.put(i, 0);
        }
        return result;
    }

    private int sumDices(final int num, final boolean useMath) {
        int result = 0;
        for (int i = 0; i < num; i++) {
            if (useMath) {
                result += rollDiceWithMathRandom();
            } else {
                result += rollDice();
            }
        }
        return result;
    }

    private int rollDice() {
        return rand.nextInt(diceMax) + 1;
    }

    private int rollDiceWithMathRandom() {
        return (int) (Math.random() * diceMax + 1);
    }
}
