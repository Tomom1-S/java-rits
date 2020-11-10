package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleBinding extends Application {

    @Override
    public void start(final Stage stage) {
        final Pane pane = new Pane();
        pane.setPrefSize(300, 300);
        final Scene scene = new Scene(pane);

        final Circle circle = new Circle();
        circle.setFill(Color.PINK);
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
        circle.radiusProperty().bind(
                Bindings.divide(
                        Bindings.min(scene.widthProperty(), scene.heightProperty()),
                        2
                )
        );
        pane.getChildren().add(circle);

        stage.setScene(scene);
        stage.show();
    }
}
