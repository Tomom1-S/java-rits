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
                    // 柴田さんコメント
                    // この時点で add に name の値が渡されるため、このときの name がキャプチャされる
                    // これは言語によって異なり、最後の値をキャプチャするものもある (e.g. Go)
                    // 変数の値ではなく、変数そのものをキャプチャする言語では、
                    // その変数に最後に入った値が使われる
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
