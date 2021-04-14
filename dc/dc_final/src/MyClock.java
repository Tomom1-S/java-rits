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
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/my_clock.fxml"));
        final Parent root = loader.load();
        final Controller controller = loader.getController();
        loader.setController(controller);

        // Initialize preferences
        controller.view.setStage(stage);
        controller.view.changeFontFamily(Font.getDefault().getFamily());
        controller.view.changeFontSize(60);
        controller.view.changeFontColor(Color.DARKSEAGREEN);
        controller.view.changeBgColor("pink");

        controller.runTimer();

        final Scene scene = new Scene(root);
        controller.bindMenuBar(stage);

        stage.setTitle("What Time Is It Now?");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
