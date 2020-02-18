import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class MyPanel extends JPanel {
    final Dimension winSize = Settings.size;
    final Font font = new Font("Serif", Font.PLAIN, Settings.fontSize);

    public MyPanel() {
        setPreferredSize(Settings.size);
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
                (winSize.width - textSize.width) / 2,
                (winSize.height + metrics.getDescent()) / 2
        );
    }
}
