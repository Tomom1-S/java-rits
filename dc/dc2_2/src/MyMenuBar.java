import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

class MyMenuBar extends JMenuBar {
    private final MyPanel panel;
    private final MyMenu menu;

    MyMenuBar(MyPanel panel) {
        this.panel = panel;
        menu = new MyMenu(panel);
        init();
    }

    private void init() {
        final JMenu settings = new JMenu(Settings.Menu.TITLE);
        this.add(settings);
        settings.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                menu.initLastValue();
                menu.setLocation(panel.getLocation());
                menu.setVisible(true);
                menu.toFront();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
    }
}
