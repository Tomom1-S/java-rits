import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class MyPanel extends JPanel implements ActionListener, ComponentListener, MouseListener {
    private MyFrame parent;

    private final Timer timer;

    private Font font = new Font("Serif", Font.PLAIN, Settings.FONT_SIZE);
    private Color fontColor = Color.BLACK;
    private Color bgColor = Color.WHITE;
    MyMenu menu = new MyMenu(this);

    public MyPanel(MyFrame parent) {
        this.parent = parent;

        timer = new Timer(Settings.INTERVAL, this);
        timer.start();

        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
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

    private int calculateFontSize(Dimension textSize) {
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
        Graphics g = getGraphics();
        final FontMetrics metrics = g.getFontMetrics();
        final LocalTime now = LocalTime.now();
        final String text = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
        final Dimension textSize = new Dimension(metrics.stringWidth(text), metrics.getHeight());
        Dimension winSize = calculatePanelSize(fontSize, textSize);
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

    public Font setClockFont(Font newVal) {
        Font old = font;
        font = newVal;
        return old;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color setFontColor(Color newVal) {
        Color old = fontColor;
        fontColor = newVal;
        return old;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public Color setBgColor(Color newVal) {
        Color old = bgColor;
        bgColor = newVal;
        return old;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        repaint();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            menu.initLastValue();
            menu.setLocation(e.getPoint());
            menu.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
