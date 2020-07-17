import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.prefs.Preferences;

public class MyPanel extends JPanel implements ActionListener, ComponentListener {
    private MyFrame parent;
    private Preferences prefs;
    private final Settings.PrefKey keys = new Settings.PrefKey();

    private final Timer timer;

    private Font font = new Font("Serif", Font.PLAIN, Settings.FONT_SIZE);
    private Color fontColor = Color.BLACK;
    private Color bgColor = Color.WHITE;

    public MyPanel(final MyFrame parent) {
        this.parent = parent;
        this.prefs = parent.getPreferences();

        setLastSettings();

        timer = new Timer(Settings.INTERVAL, this);
        timer.start();

        parent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveCurrentSettings();
                super.windowClosing(e);
            }
        });
    }

    private void setLastSettings() {
        font = new Font(prefs.get(keys.FONT_NAME, "Selif"), Font.PLAIN, prefs.getInt(keys.FONT_SIZE, Settings.FONT_SIZE));
        fontColor = Utils.nameToColor(prefs.get(keys.FONT_COLOR, "Black"));
        bgColor = Utils.nameToColor(prefs.get(keys.BG_COLOR, "White"));
    }

    private void saveCurrentSettings() {
        final Point loc = parent.getLocationOnScreen();
        prefs.putInt(keys.LOC_X, loc.x);
        prefs.putInt(keys.LOC_Y, loc.y);

        prefs.putInt(keys.HEIGHT, getHeight());
        prefs.putInt(keys.WIDTH, getWidth());

        prefs.put(keys.FONT_NAME, font.getFontName());
        prefs.putInt(keys.FONT_SIZE, font.getSize());
        prefs.put(keys.FONT_COLOR, Utils.colorToName(fontColor));
        prefs.put(keys.BG_COLOR, Utils.colorToName(bgColor));
    }

    public void paintComponent(final Graphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.setColor(fontColor);
        final LocalTime now = LocalTime.now();
        final String text = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
        drawCenteredString(g, text);
    }

    private void drawCenteredString(final Graphics g, final String text) {
        g.setFont(font);
        final FontMetrics metrics = g.getFontMetrics();
        final Dimension textSize = new Dimension(metrics.stringWidth(text), metrics.getHeight());

        font = new Font(font.getName(), font.getStyle(), calculateFontSize(textSize));
        g.setFont(font);
        final FontMetrics newMetrics = g.getFontMetrics();
        g.drawString(text,
                (getWidth() - newMetrics.stringWidth(text)) / 2,
                (getHeight() + newMetrics.getDescent()) / 2
        );
    }

    private int calculateFontSize(final Dimension textSize) {
        if (getHeight() < 1 || textSize.height < 1) {
            return 1;
        }

        final double winRatio = getWidth() / getHeight();
        final double textRatio = textSize.width / textSize.height;

        if (winRatio < textRatio) {
            return (int) (getWidth() * Settings.FONT_OCCUPANCY / textRatio);
        } else {
            return (int) (getHeight() * Settings.FONT_OCCUPANCY);
        }
    }

    void updatePanelSize(final int fontSize) {
        final Graphics g = getGraphics();
        final FontMetrics metrics = g.getFontMetrics();
        final LocalTime now = LocalTime.now();
        final String text = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
        final Dimension textSize = new Dimension(metrics.stringWidth(text), metrics.getHeight());
        final Dimension winSize = calculatePanelSize(fontSize, textSize);
        this.setSize(winSize);
        parent.setSize(winSize);
    }

    private Dimension calculatePanelSize(final int fontSize, final Dimension textSize) {
        if (fontSize <= 0) {
            return new Dimension(getWidth(), getHeight());
        }

        final double winRatio = getWidth() / getHeight();
        final double textRatio = textSize.width / textSize.height;

        if (winRatio < textRatio) {
            final double width = fontSize * textRatio / Settings.FONT_OCCUPANCY;
            return new Dimension((int) width, (int) (width / winRatio));
        } else {
            final double height = fontSize / Settings.FONT_OCCUPANCY;
            return new Dimension((int) (height * winRatio), (int) height);
        }
    }

    public Font getClockFont() {
        return font;
    }

    public Font setClockFont(final Font newVal) {
        Font old = font;
        font = newVal;
        return old;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color setFontColor(final Color newVal) {
        Color old = fontColor;
        fontColor = newVal;
        return old;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public Color setBgColor(final Color newVal) {
        Color old = bgColor;
        bgColor = newVal;
        return old;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
    }

    @Override
    public void componentShown(final ComponentEvent e) {
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
    }
}

