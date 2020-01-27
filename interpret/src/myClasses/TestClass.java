package myClasses;

public class TestClass {
    public int id;
    public String name;

    private static int nextId = 0;

    public final int PUB_INT = 42;
    private final int PVT_INT = 42;

    public TestClass() {
        id = nextId;
        nextId++;
        this.name = "Default";
    }

    public TestClass(String name) {
        id = nextId;
        nextId++;
        this.name = name;
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

    // TODO: wrapper -> primitive の対応ができたら消す
    public int addPositiveNumbers(Integer val1, Integer val2) {
        if(val1 < 0 || val2 < 0) {
            throw new IllegalArgumentException("Arguments should be positive.");
        }
        return val1 + val2;
    }

    public int addPositiveNumbers(int val1, int val2) {
        if(val1 < 0 || val2 < 0) {
            throw new IllegalArgumentException("Arguments should be positive.");
        }
        return val1 + val2;
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
