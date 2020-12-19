package ch04.ex05;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Sample extends Application {

    @Override
    public void start(final Stage stage) {
        final int buttonWidth = 100;
        final int maxWidth = 300;
        final int height = 30;
        final int diff = maxWidth / 10;

        final Rectangle gauge = new Rectangle(maxWidth / 2, height, Color.BLUE);
        final Rectangle frame = new Rectangle(maxWidth, height, null);
        frame.setStroke(Color.MEDIUMBLUE);
        final Pane pane = new Pane();
        pane.getChildren().addAll(gauge, frame);

        final Button smaller = createButton("Smaller", buttonWidth, height);
        final Button larger = createButton("Larger", buttonWidth, height);
        smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - diff));
        larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + diff));
        smaller.disableProperty().bind(
                observe(
                        number -> number.doubleValue() <= 0,
                        gauge.widthProperty()
                )
        );
        larger.disableProperty().bind(
                observe(
                        number -> number.doubleValue() >= maxWidth,
                        gauge.widthProperty()
                )
        );

        HBox box = new HBox(10);
        box.getChildren().addAll(smaller, pane, larger);

        stage.setScene(new Scene(box));
        stage.show();
    }

    private Button createButton(final String name, final int width, final int height) {
        final Button button = new Button(name);
        button.setPrefSize(width, height);
        return button;
    }

    public static <T, R> ObservableValue<R> observe(
            Function<T, R> f, ObservableValue<T> t) {
        return Bindings.createObjectBinding(() -> f.apply(t.getValue()), t);
    }

    public static <T, U, R> ObservableValue<R> observe(
            BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
        return Bindings.createObjectBinding(() -> f.apply(t.getValue(), u.getValue()));
    }
}
