package ch08.ex14;

import java.util.Objects;

public class NonNullSample {
    public static void main(final String[] args) {
        Objects.requireNonNull(args[0]);
        Objects.requireNonNull(args[1], "2nd argument is null");
        Objects.requireNonNull(args[2], () -> {
            final String msg = "3rd argument is null";
            System.out.println(msg);
            return msg;
        });
    }
}
