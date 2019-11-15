import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MyFrame extends Frame implements ActionListener, Runnable {
    private static int width = 300;
    private static int height = 300;
    private static boolean isRunning = true;

    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    private static String fontName = fonts[3].getFontName();
    private static int fontSize = height / 5;
    private static Color fontColor = Color.black;
    private static Color backColor = Color.white;

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

        addComponentListener(new MyComponentListener(this));
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

        setLayout(new BorderLayout());
        LocalTime now = LocalTime.now();
        buffer.setFont(new Font(fontName, Font.BOLD, fontSize));
        buffer.setColor(getFontColor());
        drawCenteredString(buffer, String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()),
                width / 2, height / 2);

        graphics.setColor(getBackColor());
        graphics.fillRect(0, 0, width, height);
        graphics.drawImage(back, 0, 0, this);
    }

    public void drawCenteredString(Graphics g, String text, int x, int y) {
        FontMetrics metrics = g.getFontMetrics();
        Rectangle rectText = metrics.getStringBounds(text, g).getBounds();
        x = x - rectText.width / 2;
        y = y - rectText.height / 2 + metrics.getMaxAscent();
        g.drawString(text, x, y);
    }

    public int getWidth() {
        return width;
    }

    public int setWidth(int width) {
        int old = getWidth();
        this.width = width;
        return old;
    }

    public int getHeight() {
        return height;
    }

    public int setHeight(int height) {
        int old = getHeight();
        this.height = height;
        return old;
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

class MyComponentListener implements ComponentListener {
    MyFrame parent;

    MyComponentListener(MyFrame parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Component c = e.getComponent();
        Dimension size = c.getSize();
        parent.setWidth(size.width);
        parent.setHeight(size.height);
        parent.setFontSize(calculateFontSize(size));
        c.repaint();
    }

    private int calculateFontSize(Dimension size) {
        int shortSide = size.width < size.height ? size.width : size.height;
        return shortSide / 5;
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
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
                            fonts[3].getFontName(),
                            fonts[12].getFontName(),
                            fonts[30].getFontName(),
                            fonts[43].getFontName(),
                            fonts[65].getFontName(),
                            fonts[130].getFontName(),
                            fonts[450].getFontName()
                    )),
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
        this.setLocation(locX, locY);
        this.setResizable(false);
        this.addWindowListener(new DialogWindowListener());
        initItems();
        this.setVisible(true);
    }

    private void initItems() {
        GridLayout layout = new GridLayout(items.size(), 2);
        setLayout(layout);

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

public class MyClock {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        Thread thread = new Thread(frame);
        thread.start();

        frame.setVisible(true);
    }
}

