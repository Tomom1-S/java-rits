import java.awt.*;
import java.util.Collections;

public class MyMenuItem extends Menu {
    final String name;
    final java.util.List<String> options;

    private MyMenuItem(String name) {
        this.name = name;
        this.options = Collections.emptyList();
    }

    private MyMenuItem(String name, java.util.List<String> options) {
        this.name = name;
        this.options = options;
    }
}
