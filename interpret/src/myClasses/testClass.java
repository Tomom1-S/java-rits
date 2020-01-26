package myClasses;

public class testClass {
    public int id;
    public String name;

    private static int nextId = 0;

    public final int PUB_INT = 42;
    private final int PVT_INT = 42;

    public testClass() {
        id = 0;
        nextId++;
    }

    public int addNumbers(int val1, int val2) {
        return val1 + val2;
    }

    // TODO: wrapper -> primitive の対応ができたら消す
    public int addNumbers(Integer val1, Integer val2) {
        return val1 + val2;
    }

    public int addNumbers(int val1, int val2, int val3) {
        return val1 + val2 + val3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", nextId: " + nextId
                + ", PUB_INT: " + PUB_INT + ", PVT_INT: " + PVT_INT;
    }
}
