import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class MyPanel extends JPanel implements ActionListener {
    final Timer timer;
    final Font font = new Font("Serif", Font.PLAIN, Settings.fontSize);

    public MyPanel() {
        setPreferredSize(Settings.size);
        timer = new Timer(Settings.interval, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.setColor(Color.BLACK);
        LocalTime now = LocalTime.now();
        final String text = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
        drawCenteredString(g, text, font);
    }

    private void drawCenteredString(Graphics g, String text, Font font) {
        g.setFont(font);
        final FontMetrics metrics = g.getFontMetrics();
        final Dimension textSize = new Dimension(metrics.stringWidth(text), metrics.getHeight());
        g.drawString(text,
                (getWidth() - textSize.width) / 2,
                (getHeight() + metrics.getDescent()) / 2
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }
}
