package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// p.89
public class MyGreeting {
    private String text = "";
    private StringProperty property = null;

    public final StringProperty textProperty() {
        synchronized (property) {
            if (property == null) {
                property = new SimpleStringProperty(text);
            }
            return property;
        }
    }

    public final void setText(final String newValue) {
        synchronized (property) {
            if (property != null) {
                property.set(newValue);
            }
        }

        synchronized (text) {
            text = newValue;
        }
    }

    public final String getText() {
        return text;
    }
}
