package view;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public static void main(String[] args) {
        MyFrame me = new MyFrame();
        me.init();
        me.setVisible(true);
    }

    private void init() {
        setTitle("Swingの実験");
        initBounds();
        initCloseOperation();

        //        initMenuBar();		// メニューバーの初期化

        Container c = getContentPane();
        initAppearance(c);

//        initToolBar(c);		// ツールバーの初期化
//        initPane(c);		// ペインの初期化
//        initStatusBar(c);		// ステータスバーの初期化

        setResizable(true);
    }

    /**
     * ウィンドウの位置を設定
     */
    private void initBounds() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //画面全体のサイズ
        int nx = d.width / 2;
        int ny = d.height / 2;
        int x = (d.width - nx) / 2;
        int y = (d.height - ny) / 2;

        setLocation(x, y);
        setSize(nx, ny);
    }

    /**
     * ウィンドウの見た目を設定
     */
    private void initAppearance(Container c) {
        Color color = Color.black;

        c.setBackground(color);
    }

    /**
     * ウィンドウ終了時の動作を初期化
     */
    private void initCloseOperation() {
        // ウィンドウが閉じられたらプログラムを終了
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
