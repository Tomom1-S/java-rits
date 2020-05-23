import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyWindow extends JWindow {
    MyWindow() {
        getContentPane().setLayout(new BorderLayout());
        setSize(Settings.MainFrame.SIZE);
        setLocation(Settings.MainFrame.location);
    }
}
