import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MyFrame extends Frame implements ActionListener, Runnable {
    private static final int width = 300;
    private static final int height = 300;
    private static boolean isRunning = true;

    Button button;
    PopupMenu menu;
    Thread thread;

    private static Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    public MyFrame() {
        setTitle("What time is it now?");
        setSize(width, height);

        setLayout(new FlowLayout());
        button = new Button("Settings");
        button.addActionListener(this);
        add(button);

        addWindowListener(new MyWindowAdapter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            MyDialog dialog = new MyDialog(this);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            repaint();
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics graphics) {
        Image back = createImage(width, height);
        Graphics buffer = back.getGraphics();

        Font font = new Font(fonts[0].getFontName(), Font.BOLD, 30);

        LocalTime now = LocalTime.now();
        buffer.setFont(font);
        buffer.drawString(
                String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()),
                100, 130);
        graphics.drawImage(back, 0, 0, this);

        // TODO: 常にウィンドウの中央に表示
    }
}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

class MyDialog extends Dialog implements ActionListener {
    private static final int width = 250;
    private static final int height = 180;
    private static final int locX = 100;
    private static final int locY = 100;

    private static class Item {
        final String name;
        final List<String> options;

        private Item(String name) {
            this.name = name;
            this.options = Collections.emptyList();
        }

        private Item(String name, List<String> options) {
            this.name = name;
            this.options = options;
        }
    }

    private static final List<Item> items = Arrays.asList(
            new Item("Fonts", Arrays.asList("sup1", "sup2", "sup3")),
            new Item("Font size", Arrays.asList("20", "40", "50")),
            new Item("Font color", Arrays.asList("red", "green", "blue")),
            new Item("Background color", Arrays.asList("red", "green", "blue"))
    );

    MyDialog(Frame parent) {
        super(parent, true);

        init();
    }

    private void init() {
        this.setTitle("Settings");
        this.setSize(width, height);
        this.setLocation(locX, locY);
        this.setResizable(false);
        this.addWindowListener(new DialogWindowListener());
        initItems();
        this.setVisible(true);
    }

    private void initItems() {
        int itemNum = items.size();

        setLayout(new FlowLayout());
        items.forEach(s -> {
            Label label = new Label(s.name);
            add(label);
            Choice c = new Choice();
            s.options.forEach(o -> {
                c.add(o);
            });
            add(c);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < items.size(); i++) {
//            if (e.getSource() == buttons[i]) {
//                myPopupMenus[i].show(this, buttons[i].getX(), buttons[i].getY());
//            }
        }
    }

    class DialogWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}

public class MyClock {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        Thread thread = new Thread(frame);
        thread.start();

        frame.setVisible(true);
    }
}

