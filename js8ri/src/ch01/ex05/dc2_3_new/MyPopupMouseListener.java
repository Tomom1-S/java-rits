package ch01.ex05.dc2_3_new;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPopupMouseListener implements MouseListener {
    private final JPopupMenu menu = new JPopupMenu(Settings.Menu.TITLE);
    private final MyPanel parent;

    private static final List<String> FONT_NAMES = new ArrayList<>();
    private static List<String> FONT_SIZES;
    private static List<String> COLOR_NAMES;

    MyPopupMouseListener(final MyPanel parent) {
        this.parent = parent;

        final List<Font> fontList = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
        fontList.forEach(f -> FONT_NAMES.add(f.getFontName()));
        FONT_SIZES = new ArrayList<>(Settings.Menu.SIZE_MAP.keySet());
        COLOR_NAMES = new ArrayList<>(Settings.Menu.COLOR_MAP.keySet());
        initMenus();
    }

    private void initMenus() {
        initFontMenu();
        initFontSizeMenu();
        initFontColorMenu();
        initBgColorMenu();
        initQuitMenu();
    }

    private void initFontMenu() {
        final JMenu menu = new JMenu(Settings.Menu.Label.FONT);
        for (String name : FONT_NAMES) {
            final JMenuItem item = new JMenuItem(name);
            item.addActionListener(e -> {
                final int fontSize = parent.getClockFont().getSize();
                parent.setClockFont(new Font(e.getActionCommand(), Font.PLAIN, fontSize));
            });
            menu.add(item);
        }
        // TODO: フォント名が画面に収まりきらない -> スクロールできる？

        this.menu.add(menu);
    }

    private void initFontSizeMenu() {
        final JMenu menu = new JMenu(Settings.Menu.Label.FONT_SIZE);
        for (String name : FONT_SIZES) {
            final JMenuItem item = new JMenuItem(name);
            item.addActionListener(e -> {
                final int fontSize = Settings.Menu.SIZE_MAP.get(e.getActionCommand());
                parent.setClockFont(new Font(parent.getClockFont().getName(), Font.PLAIN, fontSize));
                parent.updatePanelSize(fontSize);
            });
            menu.add(item);
        }

        this.menu.add(menu);
    }

    private void initFontColorMenu() {
        final JMenu menu = new JMenu(Settings.Menu.Label.FONT_COLOR);
        addColorItems(menu);

        this.menu.add(menu);
    }

    private void initBgColorMenu() {
        final JMenu menu = new JMenu(Settings.Menu.Label.BG_COLOR);
        addColorItems(menu);

        this.menu.add(menu);
    }

    private void initQuitMenu() {
        final JMenuItem menu = new JMenuItem(Settings.Menu.Label.QUIT);
        menu.addActionListener(e -> System.exit(0));
        this.menu.add(menu);
    }


    private void addColorItems(JMenu menu) {
        for (String name : COLOR_NAMES) {
            final JMenuItem item = new JMenuItem(name);
            item.addActionListener(e -> {
                Color color;
                try {
                    color = Settings.Menu.COLOR_MAP.get(e.getActionCommand());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }

                if (menu.getText().equals(Settings.Menu.Label.FONT_COLOR)) {
                    parent.setFontColor(color);
                } else if (menu.getText().equals(Settings.Menu.Label.BG_COLOR)) {
                    parent.setBgColor(color);
                }
            });
            menu.add(item);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePopup(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePopup(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void mousePopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            JComponent c = (JComponent) e.getSource();
            showPopup(c, e.getX(), e.getY());
            e.consume();
        }
    }

    private void showPopup(JComponent c, int x, int y) {
        menu.show(c, x, y);
    }
}
