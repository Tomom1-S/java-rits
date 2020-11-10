package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Hello extends Application {


    @Override
    public void start(Stage stage) {
        // Label
        final Label message = new Label("Text displayed here.");
        message.setFont(new Font(100));

        // Text field
        final TextField textField = new TextField();
        textField.textProperty().addListener(
                (_observable, _oldValue, newValue) -> message.setText(newValue)
        );

        // Put elements
        stage.setScene(new Scene(
                new VBox(textField, message)
        ));
        stage.setTitle("Hello");
        stage.show();
    }
}
