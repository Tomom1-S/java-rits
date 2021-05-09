import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Facility;
import models.FacilityType;
import models.Park;
import controller.StatusClient;
import utils.ColorHelper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ParkNowController implements Initializable {
    @FXML
    private ComboBox<String> parkComboBox;
    @FXML
    private ComboBox<String> facilityComboBox;
    @FXML
    private TableView table;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn statusCol;

    private static Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO ComboBoxにアイコンつける

        nameCol.prefWidthProperty().bind(table.widthProperty().divide(2));
        statusCol.prefWidthProperty().bind(table.widthProperty().divide(2));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public static void openWindow(final double locX, final double locY, final String bgColorName) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ParkNowController.class.getResource("park_now.fxml"));
            root = fxmlLoader.load();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Park information");
        stage.setScene(new Scene(root));
        stage.setX(locX);
        stage.setY(locY);
        stage.sizeToScene();
        stage.show();
    }

    public void handleOkAction(final ActionEvent _actionEvent) {
        if (Objects.isNull(parkComboBox.valueProperty().get())
                || Objects.isNull(facilityComboBox.valueProperty().get())) {
            return;
        }

        table.getItems().clear();

        final Park park = Park.fromString(parkComboBox.getValue());
        final StatusClient client = new StatusClient(park);
        final List<Facility> facilities = client.fetchStatusList(park, FacilityType.fromString(facilityComboBox.getValue()));
        facilities.forEach(f -> table.getItems().add(f));

        changeBgColor(park);
    }

    public void handleResetAction(final ActionEvent _actionEvent) {
        parkComboBox.valueProperty().set(null);
        facilityComboBox.valueProperty().set(null);
        table.getItems().clear();

        changeBgColor(Color.WHITE);
    }

    private void changeBgColor(final Color color) {
        root.setStyle("-fx-background-color: " + ColorHelper.colorToHex(color) + ";");
    }

    private void changeBgColor(final Park park) {
        final Color color;
        switch (park) {
            case TDL:
                color = Color.LIGHTPINK;
                break;
            case TDS:
                color = Color.SKYBLUE;
                break;
            default:
                throw new IllegalArgumentException("Select correct park.");
        }
        changeBgColor(color);
    }
}
