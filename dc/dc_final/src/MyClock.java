import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyClock extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/sample.fxml"));
        final Parent root = loader.load();
        final Controller controller = loader.getController();
        loader.setController(controller);

        // Initialize preferences
        controller.view.changeFontFamily(Font.getDefault().getFamily());
        controller.view.changeFontSize(50);
        controller.view.changeFontColor(Color.DARKSEAGREEN);
        controller.view.changeBgColor("pink");

        controller.runTimer();

        final Scene scene = new Scene(root,
                controller.getCanvasWidth(), controller.getCanvasHeight());
        stage.setTitle("What Time Is It Now?");
        stage.setScene(scene);
        stage.show();
    }
}
