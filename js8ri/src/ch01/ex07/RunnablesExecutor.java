package ch01.ex07;

public class RunnablesExecutor {
    public static void main(final String[] args) {
        andThen(
                () -> System.out.println("1st runner"),
                () -> System.out.println("2nd runner")
        ).run();
    }

    public static Runnable andThen(final Runnable runner1, final Runnable runner2) {
        return () -> {
            runner1.run();
            runner2.run();
        };
    }
}
