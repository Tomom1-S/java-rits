import java.awt.*;

public class MyClock {
    public static void main(String args[]) {
        MyFrame frame = new MyFrame();
        MyPanel panel = new MyPanel(frame);  // 時刻

        MyMenuBar bar = new MyMenuBar(panel);
        frame.setJMenuBar(bar);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
