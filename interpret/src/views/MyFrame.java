package views;

import models.ObjectHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class MyFrame extends JFrame implements ActionListener {
    FrameSetting setting = new FrameSetting();

    JTextField typeText;
    JTextField nameText;
    JTextArea resultText;
    JButton button;

    public static void main(String[] args) {
        MyFrame me = new MyFrame();
        me.init();
        me.setVisible(true);
    }

    private void init() {
        setTitle(setting.frameTitle);
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
        Color color = setting.bgColor;
        c.setBackground(color);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);

        initTextFields(layout, constraints);
        initButtons(layout, constraints);
    }

    /**
     * ボタンを設定
     */
    private void initButtons(GridBagLayout layout, GridBagConstraints constraints) {
        button = new JButton(setting.buttonLabel);
        button.addActionListener(this);

        constraints.gridx = 1;	//位置x
        constraints.gridy = 4;	//位置y
        constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
        constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
        layout.setConstraints(button, constraints);
        add(button);
    }

    /**
     * テキストフィールドを設定
     */
    private void initTextFields(GridBagLayout layout, GridBagConstraints constraints) {
        JLabel typeLabel = new JLabel(setting.typeLabel);
        typeText = new JTextField(setting.textFieldLength);
        JLabel nameLabel = new JLabel(setting.variableNameLabel);
        nameText = new JTextField(setting.textFieldLength);
        resultText = new JTextArea(setting.defaultResult);
        resultText.setEditable(false);  // 結果表示用のフィールドなので編集不可

        constraints.gridx = 0;	//位置x
        constraints.gridy = 0;	//位置y
        constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
        constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
        layout.setConstraints(typeLabel, constraints);
        add(typeLabel);

        constraints.gridx = 1;	//位置x
        constraints.gridy = 0;	//位置y
        constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
        constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
        layout.setConstraints(typeText, constraints);
        add(typeText);


        constraints.gridx = 0;	//位置x
        constraints.gridy = 1;	//位置y
        constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
        constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
        layout.setConstraints(nameLabel, constraints);
        add(nameLabel);

        constraints.gridx = 1;	//位置x
        constraints.gridy = 1;	//位置y
        constraints.gridwidth = 1;	//コンポーネントの表示領域のセル数 横
        constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
        layout.setConstraints(nameText, constraints);
        add(nameText);

        constraints.gridx = 0;	//位置x
        constraints.gridy = 3;	//位置y
        constraints.gridwidth = 2;	//コンポーネントの表示領域のセル数 横
        constraints.gridheight = 1;	//コンポーネントの表示領域のセル数 縦
        layout.setConstraints(resultText, constraints);
        add(resultText);
    }

    /**
     * ウィンドウ終了時の動作を初期化
     */
    private void initCloseOperation() {
        // ウィンドウが閉じられたらプログラムを終了
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * イベント発生時の挙動を設定
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            ObjectHandler oh = new ObjectHandler();
            String text;
            try {
                oh.createObject(typeText.getText());
            } catch (ClassNotFoundException ex) {
                text = ex.getMessage();
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

//             fields = oh.getFields();
//
//            resultText.setText(fields.toString());
        }
    }
}
