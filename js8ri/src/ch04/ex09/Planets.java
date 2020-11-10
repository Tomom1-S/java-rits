package ch04.ex09;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Planets extends Application {
    @Override
    public void start(final Stage stage) {
        final int width = 1000;
        final int height = 500;
        final int radius = 10;

        final Pane pane = new Pane();
        pane.setPrefSize(width, height);
        pane.setBackground(new Background(
                new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)
        ));
        final Scene scene = new Scene(pane);

        final Circle sun = new Circle(width / 2, height / 2,
                radius * 3, Color.ORANGE);
        final Circle planet = new Circle(radius, Color.LIGHTCORAL);
        final Ellipse orbit = new Ellipse(width / 2, height / 2,
                width * 0.4, height * 0.4);
        orbit.setFill(null);
        orbit.setStroke(Color.WHITE);
        pane.getChildren().addAll(orbit, sun, planet);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setPath(orbit);
        pathTransition.setNode(planet);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setCycleCount(Animation.INDEFINITE); // 無限回数繰り返す
        pathTransition.play();

        stage.setScene(scene);
        stage.show();
    }
}
