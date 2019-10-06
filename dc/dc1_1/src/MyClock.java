import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class MyClock extends Frame implements Runnable {
    private static final int width = 300;
    private static final int height = 300;
    private static boolean isRunning = true;

    private static MyClock frame = new MyClock();
    private static Thread thread = new Thread(frame);

    public static void main(String[] args) {
        frame.setTitle("What time is it now?");
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.addWindowListener(new MyWindowAdaptor());

        thread.start();
    }

    /**
     * 描画を行うのはここ
     */
    public void paint(Graphics graphics) {
        LocalTime now = LocalTime.now();
        graphics.drawString(
                String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()),
                100, 130);
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
}

class MyWindowAdaptor extends WindowAdapter {
    public void windowClosing(WindowEvent event) {
        System.exit(0);
    }
}
