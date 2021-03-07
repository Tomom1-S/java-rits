import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MyMenuBar extends MenuBar {
    final MyClock parent;
    final MenuBar bar;

    public MyMenuBar(final MyClock parent) {
        this.parent = parent;

        final Menu menu = new Menu("Settings");
        final MenuItem item = new MenuItem(Settings.Menus.APPEARANCE);
        final AppearanceDialog<ButtonType> appearance = new AppearanceDialog(parent, item);
        item.setOnAction(appearance.createButtonEvents());
        menu.getItems().addAll(item);

        bar = new MenuBar();
        bar.getMenus().addAll(menu);
    }
}
