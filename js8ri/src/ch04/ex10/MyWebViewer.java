package ch04.ex10;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MyWebViewer extends Application {
    @Override
    public void start(final Stage stage) {
        final String location = "http://horstmann.com";
        final WebView browser = new WebView();
        final WebEngine engine = browser.getEngine();
        engine.load(location);

        final TextField url = new TextField(location);
        url.setOnAction(event -> engine.load(url.getText()));
        engine.locationProperty().addListener(
                (observable, oldValue, newValue) -> url.setText(newValue)
        );

        final Button back = new Button("<");
        final WebHistory history = engine.getHistory();
        back.setOnAction(event -> history.go(-1));
        back.disableProperty().bind(
                Bindings.equal(0, history.currentIndexProperty())
        );

        final HBox hBox = new HBox(back, url);
        HBox.setHgrow(url, Priority.ALWAYS);    // URL フィールドを横幅いっぱいにする
        final BorderPane pane = new BorderPane();
        pane.setTop(hBox);
        pane.setCenter(browser);

        stage.setScene(new Scene(pane));
        stage.show();
    }
}
