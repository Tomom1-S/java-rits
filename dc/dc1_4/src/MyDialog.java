import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyDialog extends Dialog implements ActionListener {
    private static MyFrame parent;
    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    private static final int width = 500;
    private static final int height = 200;
    private static final int locX = 50;
    private static final int locY = 50;

    private static final GridBagLayout layout = new GridBagLayout();

    Button okButton;
    Button cancelButton;

    private static Settings tmpSettings = new Settings();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            Settings prev = parent.getPrevSettings();
            prev.setFontName(tmpSettings.getFontName());
            prev.setFontSize(tmpSettings.getFontSize());
            prev.setFontColor(tmpSettings.getFontColor());
            prev.setBackgroundColor(tmpSettings.getBackgroundColor());

            dispose();
        } else if (e.getSource() == cancelButton) {
            // TODO: 戻す
            Settings prev = parent.getPrevSettings();
            parent.setFontName(prev.getFontName());
            int fontSize = prev.getFontSize();
            parent.setFontSize(fontSize);
            parent.setHeight(fontSize * 5);
            parent.setWidth(fontSize * 5);
            parent.setSize(fontSize * 5, fontSize * 5);
            parent.setFontColor(prev.getFontColor());
            parent.setBackColor(prev.getBackgroundColor());
            parent.setBackground(prev.getBackgroundColor());

            dispose();
        }
    }

    private static class Item {
        final String name;
        final java.util.List<String> options;

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
        super(parent, true);
        this.parent = parent;
        init();
    }

    private void init() {
        this.setTitle("Settings");
        this.setSize(width, height);
        this.setLocation(parent.getX() + locX, parent.getY() + locY);
        this.setResizable(false);
        this.addWindowListener(new DialogWindowListener());
        initItems();
        okButton = initButton("OK");
        cancelButton = initButton("Cancel");
        this.setVisible(false);
    }

    private Button initButton(String label) {
        setLayout(layout);
        Button button = new Button(label);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.SOUTHEAST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(button, constraints);
        button.addActionListener(this);
        add(button);
        return button;
    }

    private void initItems() {
        setLayout(layout);

        initItemsFonts();

        items.forEach(s -> {
            Choice c = new Choice();
            s.options.forEach(o -> {
                c.add(o);
            });
            c.addItemListener(e -> {
                Color color;
                String colorText;
                switch (s.name) {
                    case "Fonts":
                        String fontName = e.getItem().toString();
                        parent.setFontName(fontName);
                        c.select(s.name);
                        tmpSettings.setFontName(e.getItem().toString());
                        break;
                    case "Font size":
                        String fontSizeText = e.getItem().toString();
                        int fontSize = Integer.parseInt(fontSizeText);
                        parent.setFontSize(fontSize);
                        parent.setHeight(fontSize * 5);
                        parent.setWidth(fontSize * 5);
                        parent.setSize(fontSize * 5, fontSize * 5);
                        c.select(fontSizeText);
                        tmpSettings.setFontSize(fontSize);
                        break;
                    case "Font color":
                        colorText = e.getItem().toString();
                        try {
                            Field field = Class.forName("java.awt.Color").getField(colorText);
                            color = (Color) field.get(null);
                        } catch (Exception ex) {
                            color = parent.getFontColor();
                        }
                        parent.setFontColor(color);
                        c.select(colorText);
                        tmpSettings.setFontColor(color);
                        break;
                    case "Background color":
                        colorText = e.getItem().toString();
                        try {
                            Field field = Class.forName("java.awt.Color").getField(colorText);
                            color = (Color) field.get(null);
                        } catch (Exception ex) {
                            color = this.parent.getBackColor();
                        }
                        parent.setBackColor(color);
                        parent.setBackground(color);
                        c.select(colorText);
                        tmpSettings.setBackgroundColor(color);
                        break;
                    default:
                        break;
                }
            });

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            Label label = new Label(s.name, Label.RIGHT);
            layout.setConstraints(label, constraints);
            constraints.gridwidth = GridBagConstraints.REMAINDER; //end row
            add(label);
            layout.setConstraints(c, constraints);
            add(c);
        });
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

    class DialogWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}
