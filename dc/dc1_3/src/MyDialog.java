import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyDialog extends Dialog {
    MyFrame parent;
    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    private static final int width = 250;
    private static final int height = 180;
    private static final int locX = 100;
    private static final int locY = 100;

    private static class Item {
        final String name;
        final java.util.List<String> options;

        private Item(String name) {
            this.name = name;
            this.options = Collections.emptyList();
        }

        private Item(String name, java.util.List<String> options) {
            this.name = name;
            this.options = options;
        }
    }

    private static final List<Item> items = Arrays.asList(
            new Item("Fonts", Collections.emptyList()),
            // TODO: デフォルト値も入れておきたい
            new Item("Font size", Arrays.asList("60", "20", "40", "80", "100")),
            new Item("Font color", Arrays.asList("black", "white", "red", "green", "blue")),
            new Item("Background color", Arrays.asList("white", "black", "red", "green", "blue"))
    );

    MyDialog(MyFrame parent) {
        super(parent, String.valueOf(true));
        this.parent = parent;
        init();
    }

    private void init() {
        this.setTitle("Settings");
        this.setSize(width, height);
        this.setLocation(locX, locY);
        this.setResizable(false);
        this.addWindowListener(new DialogWindowListener());
        initItems();
        this.setVisible(true);
    }

    private void initItemsFonts() {
        ArrayList<Font> fontList = new ArrayList<>(Arrays.asList(fonts));

        ArrayList<String> fontNames = new ArrayList<>(fontList.size());
        fontList.forEach(f -> {
            fontNames.add(f.getFontName());
        });

        // TODO: Fonts のある場所を取得して置き換えたい
        items.set(0, new Item("Fonts", fontNames));
    }

    private void initItems() {
        GridLayout layout = new GridLayout(items.size(), 2);
        setLayout(layout);

        initItemsFonts();

        items.forEach(s -> {
            Choice c = new Choice();
            s.options.forEach(o -> {
                c.add(o);
            });
            c.addItemListener(e -> {
                Color color;
                switch (s.name) {
                    case "Fonts":
                        parent.setFontName(e.getItem().toString());
                        break;
                    case "Font size":
                        int fontSize = Integer.parseInt(e.getItem().toString());
                        parent.setFontSize(fontSize);
                        parent.setHeight(fontSize * 5);
                        parent.setWidth(fontSize * 5);
                        parent.setSize(fontSize * 5, fontSize * 5);
                        break;
                    case "Font color":
                        try {
                            Field field = Class.forName("java.awt.Color").getField(e.getItem().toString());
                            color = (Color) field.get(null);
                        } catch (Exception ex) {
                            color = parent.getFontColor();
                        }
                        parent.setFontColor(color);
                        break;
                    case "Background color":
                        try {
                            Field field = Class.forName("java.awt.Color").getField(e.getItem().toString());
                            color = (Color) field.get(null);
                        } catch (Exception ex) {
                            color = this.parent.getBackColor();
                        }
                        parent.setBackColor(color);
                        parent.setBackground(color);
                        break;
                    default:
                        break;
                }
            });

            Label label = new Label(s.name);
            add(label);
            add(c);
        });
    }

    class DialogWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}
