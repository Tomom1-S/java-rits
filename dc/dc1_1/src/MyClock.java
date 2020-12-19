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
        // 柴田さん：日付を取得する場合、現在は Calendar は推奨されない。
        // 別のパッケージが提供されているのでそちらを利用する。
        LocalTime now = LocalTime.now();
        graphics.drawString(
                String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()),
                100, 130);
        // 柴田さん：表示される文字の大きさを適切に
        // ウィンドウサイズを変えたときに文字の大きさも変える、中央に表示するようにするなど
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

// 柴田さん：CPU 使用量は大丈夫？
