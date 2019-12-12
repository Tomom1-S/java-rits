import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFrame extends Window implements ActionListener, Runnable {
    private static int width = 300;
    private static int height = 300;
    private static Point windowLocation = new Point(100, 100);
    private static boolean isRunning = true;

    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    private static ArrayList<String> fontNames;
    private static String fontName = fonts[3].getFontName();
    private static List<String> fontSizes = Arrays.asList("20", "40", "60", "80", "100");
    private static int fontSize = height / 5;
    private static Color fontColor = Color.black;
    private static Color backColor = Color.white;

    Thread thread;
    MyPopup popup;

    public MyFrame() {
        super(new Frame());

        setLocation(windowLocation);
        setSize(width, height);
        setBackground(backColor);

        popup = new MyPopup(this);

        MyPopup font = new MyPopup("Fonts", this);
        ArrayList<Font> fontList = new ArrayList<>(Arrays.asList(fonts));
        fontNames = new ArrayList<>(fontList.size());
        fontList.forEach(f -> {
            fontNames.add(f.getFontName());
        });
        font.addSubMenu(fontNames);
        popup.add(font);

        MyPopup fontSize = new MyPopup("Font size", this);
        fontSize.addSubMenu(fontSizes);
        popup.add(fontSize);

        MyPopup fontColor = new MyPopup("Font color", this);
        fontColor.addSubMenu(Arrays.asList("black", "white", "red", "green", "blue"));
        popup.add(fontColor);

        MyPopup backgroundColor = new MyPopup("Background color", this);
        backgroundColor.addSubMenu(Arrays.asList("black", "white", "red", "green", "blue"));
        popup.add(backgroundColor);

        add(popup);

        addMouseListener(new MyMouseListener(this));
        addMouseMotionListener(new MyMouseListener(this));
        addComponentListener(new MyComponentListener(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (fontNames.contains(command)) {
            setFontName(command);
            repaint();
        } else if (fontSizes.contains(command)) {
            int fontSize = Integer.parseInt(command);
            setFontSize(fontSize);
            this.setHeight(fontSize * 5);
            this.setWidth(fontSize * 5);
            this.setSize(fontSize * 5, fontSize * 5);
            repaint();
        } else {
            System.out.println(e.getActionCommand());
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

    public Point getWindowLocation() {
        return windowLocation;
    }

    public Point setWindowLocation(Point point) {
        Point old = getWindowLocation();
        this.windowLocation = point;
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
