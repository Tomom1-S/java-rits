import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferenceController implements Initializable {
    private ViewController viewController;

    @FXML
    private ComboBox<String> fontComboBox;
    @FXML
    private ComboBox<String> fontSizeComboBox;
    @FXML
    private ComboBox<Label> fontColorComboBox;
    @FXML
    private ComboBox<Label> bgColorComboBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        fontComboBox.getItems().addAll(Font.getFamilies());
        fontComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(final ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setText(null);
                            return;
                        }
                        setFont(Font.font(item));
                        setText(item);
                    }
                };
            }
        });

        fontColorComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(final Label item, final boolean isEmpty) {
                super.updateItem(item, isEmpty);
                setText(item == null ? "" : item.getText());
            }
        });
        bgColorComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(final Label item, final boolean isEmpty) {
                super.updateItem(item, isEmpty);
                setText(item == null ? "" : item.getText());
            }
        });

        // TODO 前に選んでいたものを選ぶ
        fontComboBox.getSelectionModel().selectFirst();
        fontSizeComboBox.getSelectionModel().selectFirst();
        fontColorComboBox.getSelectionModel().selectFirst();
        bgColorComboBox.getSelectionModel().selectFirst();
    }

    public void setViewController(final ViewController viewController) {
        this.viewController = viewController;
    }

    public void handlePreferenceCancelAction(final ActionEvent _actionEvent) {
        // TODO コンボボックスのデフォルト値を元に戻す

        closeDialog(cancelButton);
    }

    public void handlePreferenceOkAction(final ActionEvent _actionEvent) {
        viewController.changeFontFamily(fontComboBox.getValue());
        viewController.changeFontSize(Integer.parseInt(fontSizeComboBox.getValue()));
        viewController.changeFontColor(fontColorComboBox.getValue().getText());
        viewController.changeBgColor(bgColorComboBox.getValue().getText());
        closeDialog(okButton);
    }

    private void closeDialog(final Button button) {
        final Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
