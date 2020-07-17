import java.awt.*;

public class MyClock {
    MyFrame frame;
    MyPanel panel;
    MyMenuBar bar;

    public MyClock() {
        frame = new MyFrame();
        panel = new MyPanel(frame);  // 時刻

        bar = new MyMenuBar(panel);
        frame.setJMenuBar(bar);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new MyClock();
    }
}
