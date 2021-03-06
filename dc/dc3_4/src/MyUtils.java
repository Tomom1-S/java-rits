import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class MyUtils {
    public static <K, V> Stream<K> getKey(final Map<K, V> map, final V value) {
        return map.entrySet().stream()
                .filter(kvEntry -> Objects.equals(kvEntry.getValue(), value))
                .map(Map.Entry::getKey);
    }
}
