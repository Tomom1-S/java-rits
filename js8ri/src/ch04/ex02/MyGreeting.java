package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyGreeting {
    private String text = "";
    private StringProperty property = null;

    public final StringProperty textProperty() {
        // 柴田さん：JavaFX のプロパティはスレッドセーフにしなくて良い
//        synchronized (property) {   // property が null のときに落ちてしまう
        if (property == null) {
            property = new SimpleStringProperty(text);
        }
        return property;
//        }
    }

    public final void setText(final String newValue) {
        if (property != null) {
            property.set(newValue);
        }
        text = newValue;
    }

    public final String getText() {
        if (property != null) {
            return property.get();
        }
        return text;
    }
}
