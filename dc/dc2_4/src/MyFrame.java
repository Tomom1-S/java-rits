import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class MyFrame extends JFrame {
    private Preferences prefs = Preferences.userNodeForPackage(getClass());
    private final Settings.PrefKey keys = new Settings.PrefKey();

    MyFrame() {
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Settings.MainFrame.TITLE);
        setSize(prefs.getInt(keys.WIDTH, Settings.MainFrame.SIZE.width), prefs.getInt(keys.HEIGHT, Settings.MainFrame.SIZE.height));
        setLocation(Settings.MainFrame.LOCATION);
        setLocation(
                prefs.getInt(keys.LOC_X, Settings.MainFrame.LOCATION.x),
                prefs.getInt(keys.LOC_Y, Settings.MainFrame.LOCATION.y)
        );
    }

    Preferences getPreferences() {
        return prefs;
    }
}
