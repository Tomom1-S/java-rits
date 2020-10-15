package ch02.ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sample {
    public static Stream<Character> characterStream(final String s) {
        final List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }
        return result.stream();
    }

    public static Stream<Character> improvedCharacterStream(final String s) {
        return IntStream.range(0, s.length()).mapToObj(i -> s.charAt(i));
    }
}
