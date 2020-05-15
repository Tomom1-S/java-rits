import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame() {
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Settings.MainFrame.TITLE);
        setSize(Settings.MainFrame.SIZE);
        setLocation(Settings.MainFrame.location);
    }
}
