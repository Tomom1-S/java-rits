package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// p.89
public class MyGreeting {
    private String defaultText = "default";
    private StringProperty property = null;

    public final StringProperty textProperty() {
        synchronized (property) {
            if (property == null) {
                property = new SimpleStringProperty(defaultText);
            }
            return property;
        }
    }

    public final void setText(final String newValue) {
        synchronized (property) {
            if (property != null) {
                property.set(newValue);
            } else if (defaultText.equals(newValue)) {
                property = new SimpleStringProperty(newValue);
            }
        }
    }

    public final String getText() {
        synchronized (property) {
            return property != null ? property.get() : defaultText;
        }
    }
}
