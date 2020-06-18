package ch17.ex01;

import java.util.ArrayList;
import java.util.List;

public class MemoryReleaser {
    private static Runtime rt = Runtime.getRuntime();

    public static void main(String[] args) {
        System.out.println("Starty-up: " + rt.freeMemory() + " / " + rt.totalMemory());

        final int maxCount = 10000;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < maxCount; i++) {
            list.add("String: " + i);
        }
        System.out.println("After process: " + rt.freeMemory() + " / " + rt.totalMemory());

        rt.gc();
        System.out.println("After garbage collection: " + rt.freeMemory() + " / " + rt.totalMemory());
    }
}
