package ch16.ex09;

import java.util.List;

public class Sample {
    public int id;
    public String name;

    private static int nextId = 0;

    public final int PUB_INT = 42;
    private final int PVT_INT = 42;

    Sample() {
        id = nextId;
        nextId++;
        this.name = "Default";
    }

    public Sample(String name) {
        id = nextId;
        nextId++;
        this.name = name;
    }

    public int addNumbers(int val1, int val2) {
        return val1 + val2;
    }

    public int addNumbers(int val1, int val2, int val3) {
        return val1 + val2 + val3;
    }

    public int addNumbers(final List<Integer> vals) {
        return vals.stream().mapToInt(Integer::intValue).sum();
    }

    int addPositiveNumbers(int val1, int val2) {
        if (val1 < 0 || val2 < 0) {
            throw new IllegalArgumentException("Arguments should be positive.");
        }
        return val1 + val2;
    }

    public String getName() {
        return name;
    }

    private int multiplyNumbers(int val1, int val2) {
        return val1 * val2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", nextId: " + nextId
                + ", PUB_INT: " + PUB_INT + ", PVT_INT: " + PVT_INT;
    }
}
