import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.time.LocalTime;

public class MyPanel extends JPanel implements ComponentListener {
    final Timer timer;
    Font font = new Font("Serif", Font.PLAIN, Settings.fontSize);

    public MyPanel() {
        setPreferredSize(Settings.size);
        // 柴田さん：Timer に直接 ActionListener を渡せば、ActionListener の実装が不要になる
        timer = new Timer(Settings.interval, e -> this.repaint());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.setColor(Color.BLACK);
        LocalTime now = LocalTime.now();
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
            return (int) (getWidth() * Settings.fontOccupancy / textRatio);
        } else {
            return (int) (getHeight() * Settings.fontOccupancy);
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
}
