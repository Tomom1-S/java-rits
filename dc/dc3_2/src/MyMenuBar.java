import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.*;

public class MyMenuBar extends MenuBar {
    final MyClock parent;
    final MenuBar bar;
    final Map<String, Menu> itemMap = new LinkedHashMap<>();
    final int colorTipSize = 10;

    public MyMenuBar(final MyClock parent) {
        this.parent = parent;
        initItems();

        final Menu menu = new Menu("Preferences");
        menu.getItems().addAll(itemMap.values());

        bar = new MenuBar();
        bar.getMenus().addAll(menu);
    }

    /**
     * MenuItem を初期化
     */
    private void initItems() {
        itemMap.put(Settings.MenuItems.FONT, new Menu(Settings.MenuItems.FONT));
        itemMap.put(Settings.MenuItems.FONT_SIZE, new Menu(Settings.MenuItems.FONT_SIZE));
        itemMap.put(Settings.MenuItems.FONT_COLOR, new Menu(Settings.MenuItems.FONT_COLOR));
        itemMap.put(Settings.MenuItems.BG_COLOR, new Menu(Settings.MenuItems.BG_COLOR));

        Font.getFamilies().stream()
                .map(MenuItem::new)
                .forEach(e -> itemMap.get(Settings.MenuItems.FONT).getItems().add(e));
        Arrays.stream(Settings.FontSize.VALUES)
                .mapToObj(String::valueOf)
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
