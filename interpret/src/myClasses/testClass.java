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

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", nextId: " + nextId
                + ", PUB_INT: " + PUB_INT + ", PVT_INT: " + PVT_INT;
    }
}
