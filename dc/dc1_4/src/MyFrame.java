import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class MyFrame extends Frame implements ActionListener, Runnable {
    MyPrefs prefs =  new MyPrefs(this.getClass(), this);

    private static Point location = new Point(0, 0);
    private static int width;
    private static int height;
    private static boolean isRunning = true;
    private static int buttonX = 10;
    private static int buttonY = 30;
    private static int buttonWidth = 80;
    private static int buttonHeight = 30;

    private static boolean prevSettingsInitialized = false;
    private static Settings prevSettings = new Settings();
    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    private static String fontName;
    private static int fontSize;
    private static Color fontColor;
    private static Color backColor;

    Button button;
    Thread thread;
    MyDialog dialog = new MyDialog(this);

    public MyFrame() {
        prefs.load();

        setTitle("What time is it now?");
        setSize(width, height);
        setLocation(location);
        setBackground(backColor);

        setLayout(null);
        button = new Button("Settings");
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button.addActionListener(this);
        add(button);

        setFontName(fonts[0].getFontName());
        setFontSize(height / 5);
        setFontColor(Color.black);
        setBackColor(Color.white);

        addComponentListener(new MyComponentListener(this));
        addWindowListener(new MyWindowAdapter(this, prefs));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            dialog.setVisible(true);
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

        if (!prevSettingsInitialized) {
            prevSettings.setFontName(fontName);
            prevSettings.setFontSize(fontSize);
            prevSettings.setFontColor(getFontColor());
            prevSettings.setBackgroundColor(getBackColor());
            prevSettingsInitialized = true;
        }
    }

    public void drawCenteredString(Graphics g, String text, int x, int y) {
        FontMetrics metrics = g.getFontMetrics();
        Rectangle rectText = metrics.getStringBounds(text, g).getBounds();
        x = x - rectText.width / 2;
        y = y - rectText.height / 2 + buttonY + metrics.getMaxAscent();
        g.drawString(text, x, y);
    }

    public Settings getPrevSettings() {
        return prevSettings;
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
