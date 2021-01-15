package ch08.ex08;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class CheckedQueueExample {
    public static void main(final String[] _args) {
        final Queue<Path> queue = new ArrayDeque<>();
        addElements(queue);

        final Queue<Path> checkedQueue = Collections.checkedQueue(new ArrayDeque<>(), Path.class);
        addElements(checkedQueue);
    }

    private static void addElements(final Queue queue) {
        queue.add(Paths.get("sample/WarAndPeace.txt"));
        queue.add("sample/WarAndPeace.txt");

    }
}
