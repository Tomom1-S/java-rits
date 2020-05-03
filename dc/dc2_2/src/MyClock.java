import java.awt.*;

public class MyClock {
    public static void main(String args[]) {
        MyFrame frame = new MyFrame();
        MyPanel panel = new MyPanel(frame);  // 時刻

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
