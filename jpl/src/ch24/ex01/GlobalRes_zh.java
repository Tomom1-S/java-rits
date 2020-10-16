package ch24.ex01;

import java.util.*;

public class GlobalRes_zh extends ResourceBundle {
    @Override
    protected Object handleGetObject(String key) {
        if (Objects.equals(key, "Hello")) {
            return "你好";
        }
        if (Objects.equals(key, "GOODBYE")) {
            return "再见";
        }

        return null;
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(keySet());
    }

    @Override
    protected Set<String> handleKeySet() {
        return new HashSet<>(Arrays.asList("Hello", "GOODBYE"));
    }
}
