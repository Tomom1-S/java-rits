package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

public class Sample {
    public static void main(final String[] args) {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(
                    () -> System.out.println(name)
            );
        }

        for (int i = 0; i < names.length; i++) {
            final String name = names[i];
            runners.add(
                    () -> System.out.println(name)
            );
        }

        for (Runnable runner : runners) {
            runner.run();
        }
    }
}
