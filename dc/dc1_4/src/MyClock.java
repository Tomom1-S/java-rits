public class MyClock {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        Thread thread = new Thread(frame);
        thread.start();

        frame.setVisible(true);
    }
}
