package ch01.ex05.dc2_3;

import java.awt.*;

public class MyClock {
    public static void main(String args[]) {
        MyWindow window = new MyWindow();
        MyPanel panel = new MyPanel(window);  // 時刻

        MyWindowMouseListener mouseListener = new MyWindowMouseListener(window);
        panel.addMouseMotionListener(mouseListener);
        window.addMouseMotionListener(mouseListener);

        window.getContentPane().add(panel, BorderLayout.CENTER);
        window.setVisible(true);
    }
}
