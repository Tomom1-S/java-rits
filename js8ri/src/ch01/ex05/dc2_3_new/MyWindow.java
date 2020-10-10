package ch01.ex05.dc2_3_new;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JWindow {
    MyWindow() {
        getContentPane().setLayout(new BorderLayout());
        setSize(Settings.MainFrame.SIZE);
        setLocation(Settings.MainFrame.location);
    }
}
