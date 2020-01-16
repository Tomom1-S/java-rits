import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter {
    MyFrame frame;
    MyPrefs prefs;

    public MyWindowAdapter(MyFrame frame, MyPrefs prefs) {
        this.frame = frame;
        this.prefs = prefs;
    }

    public void windowClosing(WindowEvent e) {
        // 閉じる前に設定を保存
        prefs.save();
        System.exit(0);
    }
}
