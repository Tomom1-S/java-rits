package ch02.ex07;

import java.util.stream.Stream;

public class Sample {
    public static <T> boolean isFinite(Stream<T> stream) {
        if (stream.count() < Long.MAX_VALUE) {
            return true;
        }
        return false;   // 無限ストリームでここまで到達しない(結果が返らない)
    }
}
