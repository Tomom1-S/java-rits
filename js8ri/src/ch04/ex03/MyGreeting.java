package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyGreeting {
    private String defaultText = "default";
    private StringProperty property = null;

    public final StringProperty textProperty() {
        // 柴田さん：JavaFX のプロパティはスレッドセーフにしなくて良い
        if (property == null) {
            property = new SimpleStringProperty(defaultText);
        }
        return property;
    }

    public final void setText(final String newValue) {
        if (defaultText.equals(newValue)) {
            return;
        }
        if (property != null) {
            property.set(newValue);
        } else {
            property = new SimpleStringProperty(newValue);
        }
    }

    public final String getText() {
        return property != null ? property.get() : defaultText;
    }
}
