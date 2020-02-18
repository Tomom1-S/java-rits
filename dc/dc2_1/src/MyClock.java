public class MyClock {
    public static void main(String args[]) {
        MyPanel panel = new MyPanel();  // 時刻

        MyFrame frame = new MyFrame();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
