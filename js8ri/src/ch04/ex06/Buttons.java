package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Buttons extends Application {
    @Override
    public void start(final Stage stage) {
        final BorderPane pane = new BorderPane();

        final Button top = new Button("Top");
        pane.setTop(top);
        BorderPane.setAlignment(top, Pos.CENTER);

        pane.setLeft(new Button("Left"));
        pane.setCenter(new Button("Center"));
        pane.setRight(new Button("Right"));

        final Button bottom = new Button("Bottom");
        pane.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        stage.setScene(new Scene(pane));
        stage.show();
    }
}
