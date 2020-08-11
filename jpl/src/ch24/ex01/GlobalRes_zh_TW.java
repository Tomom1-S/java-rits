package ch24.ex01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GlobalRes_zh_TW extends GlobalRes_zh {
    @Override
    protected Object handleGetObject(String key) {
        if (Objects.equals(key, "GOODBYE")) {
            return "再見";
        }

        return null;
    }

    @Override
    protected Set<String> handleKeySet() {
        return new HashSet<>(Arrays.asList("GOODBYE"));
    }
}
