import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyPopupMenu {
    final MyClock parent;
    final ContextMenu contextMenu;
    final Map<String, Menu> itemMap = new LinkedHashMap<>();
    final int colorTipSize = 10;

    public MyPopupMenu(MyClock parent) {
        this.parent = parent;

        contextMenu = new ContextMenu();
        initItems();
        initMenus();
    }

    private void initMenus() {
//        System.out.println("initMenu");

//        MenuItem menuItem1 = new MenuItem("foo");
//        menuItem1.setOnAction(event -> System.out.println("foo"));
//
//        MenuItem menuItem2 = new MenuItem("bar");
//        menuItem2.setOnAction(event -> System.out.println("bar"));

//        contextMenu.getItems().addAll(menuItem1, menuItem2);
        contextMenu.getItems().addAll(itemMap.values());
    }

    /**
     * MenuItem を初期化
     */
    private void initItems() {
        itemMap.put(Settings.MenuItems.FONT, new Menu(Settings.MenuItems.FONT));
        itemMap.put(Settings.MenuItems.FONT_SIZE, new Menu(Settings.MenuItems.FONT_SIZE));
        itemMap.put(Settings.MenuItems.FONT_COLOR, new Menu(Settings.MenuItems.FONT_COLOR));
        itemMap.put(Settings.MenuItems.BG_COLOR, new Menu(Settings.MenuItems.BG_COLOR));
        itemMap.put(Settings.MenuItems.QUIT, new Menu(Settings.MenuItems.QUIT));

        // itemMap のサブアイテムをセット
        Font.getFamilies().stream()
                .map(MenuItem::new)
                .forEach(e -> itemMap.get(Settings.MenuItems.FONT).getItems().add(e));
        Arrays.stream(Settings.FontSize.VALUES)
                .mapToObj(String::valueOf)
                .map(MenuItem::new)
                .forEach(e -> itemMap.get(Settings.MenuItems.FONT_SIZE).getItems().add(e));
        setMenuItemForColor(Settings.MenuItems.FONT_COLOR);
        setMenuItemForColor(Settings.MenuItems.BG_COLOR);

        // MenuItem のアクションをセット
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
        itemMap.get(Settings.MenuItems.QUIT).setOnAction(event -> parent.stage.close());
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
