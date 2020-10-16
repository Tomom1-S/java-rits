package ch22.ex15.views;

import javax.swing.*;

public class MyCalculator extends JFrame {
    MyCalculator(final String title) {
        setTitle(title);
    }

    public static void main(String[] args) {
        MyCalculator frame = new MyCalculator("My Calculator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 300);
        frame.setLocationRelativeTo(null);  // 画面中央に表示

        MyPanel panel = new MyPanel(frame);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
