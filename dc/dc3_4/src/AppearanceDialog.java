import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.util.*;

public class AppearanceDialog<T> extends Dialog<T> {
    final MyClock parent;
    final MenuItem item;

    final Map<String, ComboBox> cmbMap = new LinkedHashMap<>();
    private static final int COLOR_TIP_SIZE = 10;

    final ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    final ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

    AppearanceDialog(final MyClock parent, final MenuItem item) {
        this.parent = parent;
        this.item = item;

        setTitle(Settings.Menus.APPEARANCE);
        initStyle(StageStyle.UNDECORATED);

        setHeaderText("Select your preferences.");
        getDialogPane().setGraphic(new ImageView(
                new Image(String.valueOf(getClass().getResource("/palette.png")),
                        70, 70, false, false)));

        initComboBoxes();
        putElements();
        getDialogPane().getButtonTypes().addAll(cancel, ok);
    }

    EventHandler<ActionEvent> createButtonEvents() {
        return _ev -> {
            final Optional<T> result = showAndWait();
            if (!result.isPresent()) {
                return;
            }

            if (result.get() == ok) {
                parent.changeFontFamily((String) cmbMap.get(Settings.MenuItems.FONT).getValue());
                parent.changeFontSize((int) cmbMap.get(Settings.MenuItems.FONT_SIZE).getValue());
                parent.changeFontColor(Settings.COLOR_MAP.get(
                        cmbMap.get(Settings.MenuItems.FONT_COLOR).getValue()));
                parent.changeBgColor(
                        ((String) cmbMap.get(Settings.MenuItems.BG_COLOR).getValue())
                                .replaceAll("\\s", ""));
            } else if (result.get() == cancel) {
                setDefaultValueOnComboBox(
                        cmbMap.get(Settings.MenuItems.FONT),
                        Font.getFamilies(),
                        parent.appearance.font);
                setDefaultValueOnComboBox(
                        cmbMap.get(Settings.MenuItems.FONT_SIZE),
                        Settings.FontSize.VALUES,
                        parent.appearance.fontSize);
                final List<String> colorList = new ArrayList<>(Settings.COLOR_MAP.keySet());
                Collections.sort(colorList);
                setDefaultValueOnComboBox(
                        cmbMap.get(Settings.MenuItems.FONT_COLOR),
                        colorList,
                        MyUtils.getKey(Settings.COLOR_MAP, parent.appearance.fontColor).get(0)
                );
                setDefaultValueOnComboBox(
                        cmbMap.get(Settings.MenuItems.BG_COLOR),
                        colorList,
                        MyUtils.getKey(Settings.COLOR_MAP, parent.appearance.bgColor).get(0)
                );
            }
        };
    }

    private void initComboBoxes() {
        final List<String> colorList = new ArrayList<>(Settings.COLOR_MAP.keySet());
        Collections.sort(colorList);

        cmbMap.put(Settings.MenuItems.FONT,
                createComboBox(Font.getFamilies(), parent.appearance.font));
        cmbMap.put(Settings.MenuItems.FONT_SIZE,
                createComboBox(Settings.FontSize.VALUES, parent.appearance.fontSize));
        cmbMap.put(Settings.MenuItems.FONT_COLOR,
                createComboBox(colorList,
                        MyUtils.getKey(Settings.COLOR_MAP, parent.appearance.fontColor).get(0)));
        cmbMap.put(Settings.MenuItems.BG_COLOR,
                createComboBox(colorList,
                        MyUtils.getKey(Settings.COLOR_MAP, parent.appearance.bgColor).get(0)));

        cmbMap.get(Settings.MenuItems.FONT_COLOR).setCellFactory(createCallbackForColortip());
        cmbMap.get(Settings.MenuItems.BG_COLOR).setCellFactory(createCallbackForColortip());
        // TODO 選択したアイテムにカラーチップをつける
    }

    private void putElements() {
        final GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        getDialogPane().setContent(grid);
        grid.add(createAlignedLabel(Settings.MenuItems.FONT, grid, HPos.RIGHT), 0, 0);
        grid.add(cmbMap.get(Settings.MenuItems.FONT), 1, 0);
        grid.add(createAlignedLabel(Settings.MenuItems.FONT_SIZE, grid, HPos.RIGHT), 0, 1);
        grid.add(cmbMap.get(Settings.MenuItems.FONT_SIZE), 1, 1);
        grid.add(createAlignedLabel(Settings.MenuItems.FONT_COLOR, grid, HPos.RIGHT), 0, 2);
        grid.add(cmbMap.get(Settings.MenuItems.FONT_COLOR), 1, 2);
        grid.add(createAlignedLabel(Settings.MenuItems.BG_COLOR, grid, HPos.RIGHT), 0, 3);
        grid.add(cmbMap.get(Settings.MenuItems.BG_COLOR), 1, 3);
    }

    /**
     * 文字位置を指定したラベルを作成
     *
     * @param text ラベルの値
     * @param grid ラベルを載せる GridPane
     * @param pos  ラベル位置
     * @return 生成した Label
     */
    private Label createAlignedLabel(final String text, final GridPane grid, final HPos pos) {
        final Label label = new Label(text);
        grid.setHalignment(label, pos);
        return label;
    }

    /**
     * 指定した値を初期選択とした ComboBox を生成
     *
     * @param list         選択肢のリスト
     * @param defaultValue デフォルトの選択値
     * @param <T>
     * @return 生成した ComboBox
     */
    private <T> ComboBox<T> createComboBox(final List<T> list, final T defaultValue) {
        final ComboBox<T> result = new ComboBox<>();
        list.forEach(e -> result.getItems().add(e));
        return setDefaultValueOnComboBox(result, list, defaultValue);
    }

    private <T> ComboBox<T> setDefaultValueOnComboBox(
            final ComboBox<T> comboBox, final List<T> list, final T defaultValue) {
        comboBox.getSelectionModel().select(list.indexOf(defaultValue));
        return comboBox;
    }

    /**
     * Combobox にカラーチップをつけるための Callback を生成
     *
     * @return 生成した Callback
     */
    private Callback<ListView<String>, ListCell<String>> createCallbackForColortip() {
        return new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(final String text, final boolean empty) {
                        super.updateItem(text, empty);
                        setText(text);
                        if (text == null || empty) {
                            setGraphic(null);
                            return;
                        }
                        setGraphic(new Rectangle(COLOR_TIP_SIZE, COLOR_TIP_SIZE, Settings.COLOR_MAP.get(text)));
                    }
                };
            }
        };
    }
}
