package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// p.89
public class Greeting {
    private StringProperty text = new SimpleStringProperty("");

    public final StringProperty textProperty() {
        return text;
    }

    public final void setText(final String newValue) {
        text.set(newValue);
    }

    public final String getText() {
        return text.get();
    }
}
