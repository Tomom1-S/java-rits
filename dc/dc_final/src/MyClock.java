import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyClock extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/sample.fxml"));
        final Parent root = loader.load();
        final Controller controller = loader.getController();
        loader.setController(controller);

        controller.runTimer();

        final Scene scene = new Scene(root, 300, 275);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }
}
