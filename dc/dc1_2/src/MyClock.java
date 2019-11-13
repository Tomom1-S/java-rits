import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class MyFrame extends Frame implements ActionListener, Runnable {
    private static final int width = 300;
    private static final int height = 300;
    private static boolean isRunning = true;

    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    private static String fontName = fonts[3].getFontName();
    private static int defaultFontSize = 30;
    private static int fontSize = 40;
    private static Color fontColor = Color.black;
    private static Color backColor = Color.yellow;

    Button button;
    Thread thread;

    public MyFrame() {
        setTitle("What time is it now?");
        setSize(width, height);
        setBackground(backColor);

        setLayout(new FlowLayout());
        button = new Button("Settings");
        button.addActionListener(this);
        add(button);

        addWindowListener(new MyWindowAdapter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            MyDialog dialog = new MyDialog(this);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            repaint();
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics graphics) {
        Image back = createImage(width, height);
        Graphics buffer = back.getGraphics();

        Font font = new Font(fontName, Font.BOLD, fontSize);

        LocalTime now = LocalTime.now();
        buffer.setFont(font);
        buffer.setColor(getFontColor());
        buffer.drawString(
                String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()),
                100, 130);
        graphics.drawImage(back, 0, 0, this);

        // TODO: 常にウィンドウの中央に表示
    }

    public String getFontName() {
        return fontName;
    }
    public String setFontName(String fontName) {
        String old = getFontName();
        this.fontName = fontName;
        return old;
    }

    public int getFontSize() {
        return fontSize;
    }
    public int setFontSize(int fontSize) {
        int old = getFontSize();
        this.fontSize = fontSize;
        return old;
    }

    public Color getFontColor() {
        return fontColor;
    }
    public Color setFontColor(Color color) {
        Color old = getFontColor();
        this.fontColor = color;
        return old;
    }

    public Color getBackColor() {
        return backColor;
    }
    public Color setBackColor(Color color) {
        Color old = getBackColor();
        this.backColor = color;
        return old;
    }
}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

class MyDialog extends Dialog {
    MyFrame parent;
    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    private static final int width = 250;
    private static final int height = 180;
    private static final int locX = 100;
    private static final int locY = 100;

    private static class Item {
        final String name;
        final List<String> options;

        private Item(String name) {
            this.name = name;
            this.options = Collections.emptyList();
        }

        private Item(String name, List<String> options) {
            this.name = name;
            this.options = options;
        }
    }

    private static final List<Item> items = Arrays.asList(
            new Item("Fonts",
                    Arrays.asList(
                            fonts[0].getFontName(),
                            fonts[1].getFontName(),
                            fonts[2].getFontName(),
                            fonts[3].getFontName()
                    )),
            // TODO: デフォルト値も入れておきたい
            new Item("Font size", Arrays.asList("20", "40", "50")),
            new Item("Font color", Arrays.asList("red", "green", "blue", "black", "white")),
            new Item("Background color", Arrays.asList("red", "green", "blue", "black", "white"))
    );

    MyDialog(MyFrame parent) {
        super(parent, true);
        this.parent = parent;
//        items.get(items.indexOf("Font size")).options.add(Objects.toString(parent.defaultFontSize));
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

    private void initItems() {
        setLayout(new FlowLayout());
        items.forEach(s -> {
            Label label = new Label(s.name);
            add(label);
            Choice c = new Choice();
            s.options.forEach(o -> {
                c.add(o);
            });
            c.addItemListener(e -> {
                Color color;
                switch (s.name) {
                    case "Fonts":
                        this.parent.setFontName(e.getItem().toString());
                        break;
                    case "Font size":
                        this.parent.setFontSize(Integer.parseInt(e.getItem().toString()));
                        break;
                    case "Font color":
                        try {
                            Field field = Class.forName("java.awt.Color").getField(e.getItem().toString());
                            color = (Color)field.get(null);
                        } catch (Exception ex) {
                            color = this.parent.getFontColor();
                        }
                        this.parent.setFontColor(color);
                        break;
                    case "Background color":
                        try {
                            Field field = Class.forName("java.awt.Color").getField(e.getItem().toString());
                            color = (Color)field.get(null);
                        } catch (Exception ex) {
                            color = this.parent.getBackColor();
                        }
                        // TODO: 動的に変更を反映させたい
                        this.parent.setBackColor(color);
                        break;
                    default:
                        break;
                }
            });
            add(c);
        });
    }

    class DialogWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}

public class MyClock {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        Thread thread = new Thread(frame);
        thread.start();

        frame.setVisible(true);
    }
}

