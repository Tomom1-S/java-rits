public class MyClock {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        Thread thread = new Thread(frame);
        thread.start();

        frame.setVisible(true);
    }

    // 柴田さん：ポップアップから終了できるように
    // ドラッグアンドドロップの挙動（スクリーン上の位置 - フレーム上の位置？）
    // 色増やす（cyan, yellowなど）
}
