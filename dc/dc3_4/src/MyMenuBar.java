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
import javafx.util.Pair;

import java.util.*;

public class MyMenuBar extends MenuBar {
    final MyClock parent;
    final Appearance defaultAppearance;

    final MenuBar bar;
    final Dialog<Pair<String, String>> dialog = new Dialog<>();
    final Map<String, Menu> itemMap = new LinkedHashMap<>();
    final int colorTipSize = 10;

    public MyMenuBar(final MyClock parent) {
        this.parent = parent;
        defaultAppearance = new Appearance(
                parent.appearance.font,
                parent.appearance.fontSize,
                parent.appearance.fontColor,
                parent.appearance.bgColor);

        final Menu menu = new Menu("Settings");
        final MenuItem item = new MenuItem(Settings.Menus.APPEARANCE);
        menu.getItems().addAll(item);

        initDialog();

        item.setOnAction(event -> {
            dialog.show();
        });

        bar = new MenuBar();
        bar.getMenus().addAll(menu);
    }

    /**
     * Dialog を初期化
     * https://code.makery.ch/blog/javafx-dialogs-official/
     */
    private void initDialog() {
        dialog.setTitle(Settings.Menus.APPEARANCE);
        // TODO メッセージ修正
        dialog.setHeaderText("Enter some text, or use default value.");
        dialog.getDialogPane().setGraphic(new ImageView(
                new Image("file:dc3_4/resources/palette.png", 70, 70, false, false)));

        final GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        dialog.getDialogPane().setContent(grid);

        final ComboBox<String> fontCmb = createComboBox(Font.getFamilies(), parent.appearance.font.getFamily());
        final ComboBox<Integer> fontSizeCmb = createComboBox(Settings.FontSize.VALUES, parent.appearance.fontSize);
        final List<String> colorList = new ArrayList<>(Settings.COLOR_MAP.keySet());
        Collections.sort(colorList);
        final ComboBox<String> fontColorCmb = createComboBox(colorList,
                MyUtils.getKey(Settings.COLOR_MAP, parent.appearance.fontColor).findFirst().get());
        final ComboBox<String> bgColorCmb = createComboBox(colorList,
                MyUtils.getKey(Settings.COLOR_MAP, parent.appearance.bgColor).findFirst().get());

        grid.add(createAlignedLabel(Settings.MenuItems.FONT, grid, HPos.RIGHT), 0, 0);
        grid.add(fontCmb, 1, 0);
        grid.add(createAlignedLabel(Settings.MenuItems.FONT_SIZE, grid, HPos.RIGHT), 0, 1);
        grid.add(fontSizeCmb, 1, 1);
        grid.add(createAlignedLabel(Settings.MenuItems.FONT_COLOR, grid, HPos.RIGHT), 0, 2);
        grid.add(fontColorCmb, 1, 2);
        grid.add(createAlignedLabel(Settings.MenuItems.BG_COLOR, grid, HPos.RIGHT), 0, 3);
        grid.add(bgColorCmb, 1, 3);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        // TODO ボタンが押されたときのアクション
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
        result.getSelectionModel().select(list.indexOf(defaultValue));
        return result;
    }

    /**
     * MenuItem を初期化
     * TODO 削除する
     */
    private void initItems() {
        itemMap.put(Settings.MenuItems.FONT, new Menu(Settings.MenuItems.FONT));
        itemMap.put(Settings.MenuItems.FONT_SIZE, new Menu(Settings.MenuItems.FONT_SIZE));
        itemMap.put(Settings.MenuItems.FONT_COLOR, new Menu(Settings.MenuItems.FONT_COLOR));
        itemMap.put(Settings.MenuItems.BG_COLOR, new Menu(Settings.MenuItems.BG_COLOR));

        Font.getFamilies().stream()
                .map(MenuItem::new)
                .forEach(e -> itemMap.get(Settings.MenuItems.FONT).getItems().add(e));
        Settings.FontSize.VALUES.stream()
                .map(String::valueOf)
                .map(MenuItem::new)
                .forEach(e -> itemMap.get(Settings.MenuItems.FONT_SIZE).getItems().add(e));
        setMenuItemForColor(Settings.MenuItems.FONT_COLOR);
        setMenuItemForColor(Settings.MenuItems.BG_COLOR);

        setMenuItemAction(
                Settings.MenuItems.FONT,
                event -> parent.changeFontFamily(((MenuItem) event.getSource()).getText())
        );
        setMenuItemAction(
                Settings.MenuItems.FONT_SIZE,
                event -> parent.changeFontSize(
                        Integer.parseInt(((MenuItem) event.getSource()).getText())
                )
        );
        setMenuItemAction(
                Settings.MenuItems.FONT_COLOR,
                event -> parent.changeFontColor(
                        Settings.COLOR_MAP.get(((MenuItem) event.getSource()).getText())
                )
        );
        setMenuItemAction(
                Settings.MenuItems.BG_COLOR,
                event -> parent.changeBgColor(((MenuItem) event.getSource()).getText()
                        .replaceAll("\\s", ""))
        );
    }

    private void setMenuItemAction(final String key, final EventHandler<ActionEvent> handler) {
        itemMap.get(key).getItems().stream()
                .forEach(menuItem -> menuItem.setOnAction(handler));
    }

    private void setMenuItemForColor(final String key) {
        Settings.COLOR_MAP.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new MenuItem(e.getKey(),
                        new Rectangle(colorTipSize, colorTipSize, e.getValue())))
                .forEach(e -> itemMap.get(key).getItems().add(e));
    }
}
