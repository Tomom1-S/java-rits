package views;

import models.ObjectHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class MyFrame extends JFrame implements ActionListener {
    GridBagLayout layout = new GridBagLayout();
    int gridX = FrameSetting.Grid.X_DEFAULT;
    int gridY = FrameSetting.Grid.Y_DEFAULT;

    JTextField typeText;
    JTextField fieldNameText;   // TODO 選択式にする
    JTextField fieldText;
    JTextField methodNameText;   // TODO 選択式にする
    JTextField methodParamText;
    JTextField searchTypeText;  // TODO 不要になる？
    JTextField constructorNameText;   // TODO 選択式にする
    JTextField constructorParamText;
    JTextArea resultText;

    JButton btnCreateObject;
    JButton btnSearchClass;
    JButton btnCallConstructor;
    JButton btnChangeField;
    JButton btnCallMethod;
    JButton btnCreateArray;
    JButton btnGetArrayElement;
    JButton btnSetArrayElement;

    public static void main(String[] args) {
        MyFrame me = new MyFrame();
        me.init();
        me.setVisible(true);
    }

    private void init() {
        setTitle(FrameSetting.FRAME_TITLE);
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
        Color color = FrameSetting.BG_COLOR;
        c.setBackground(color);

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);

        initTextFields(layout, constraints);
        initButtons(layout, constraints);
    }

    /**
     * ボタンを設定
     */
    private void initButtons(GridBagLayout layout, GridBagConstraints constraints) {
        initGrid();

        btnCreateObject = new JButton(FrameSetting.ButtonLabel.CREATE_OBJECT);
        btnCreateObject.addActionListener(this);
        putComponent(btnCreateObject,
                FrameSetting.Grid.X_BUTTON, gridY++, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnCallConstructor = new JButton(FrameSetting.ButtonLabel.CALL_CONSTRUCTOR);
        btnCallConstructor.addActionListener(this);
        putComponent(btnCallConstructor,
                FrameSetting.Grid.X_BUTTON, gridY++, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnChangeField = new JButton(FrameSetting.ButtonLabel.CHANGE_FIELD);
        btnChangeField.addActionListener(this);
        putComponent(btnChangeField,
                FrameSetting.Grid.X_BUTTON, gridY++, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnCallMethod = new JButton(FrameSetting.ButtonLabel.CALL_METHOD);
        btnCallMethod.addActionListener(this);
        putComponent(btnCallMethod,
                FrameSetting.Grid.X_BUTTON, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
    }

    /**
     * テキストフィールドを設定
     */
    private void initTextFields(GridBagLayout layout, GridBagConstraints constraints) {
        initGrid();

        // Create object
        putComponent(new JLabel(FrameSetting.TextLabel.TYPE),
                gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        typeText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(typeText, gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        // Call constructor
        gridX = FrameSetting.Grid.X_DEFAULT;
        gridY++;
        putComponent(new JLabel(FrameSetting.TextLabel.CONSTRUCTOR),
                gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        constructorNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(constructorNameText, gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(new JLabel(FrameSetting.TextLabel.PARAMETER),
                gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        constructorParamText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(constructorParamText, gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        // Change field
        gridX = FrameSetting.Grid.X_DEFAULT;
        gridY++;
        putComponent(new JLabel(FrameSetting.TextLabel.FIELD),
                gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        fieldNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(fieldNameText, gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        fieldText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(fieldText, ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        // Call method
        gridX = FrameSetting.Grid.X_DEFAULT;
        gridY++;
        putComponent(new JLabel(FrameSetting.TextLabel.METHOD),
                gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        methodNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(methodNameText, gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(new JLabel(FrameSetting.TextLabel.PARAMETER),
                gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        methodParamText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(methodParamText, gridX++, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        // Result
        gridX = FrameSetting.Grid.X_DEFAULT;
        gridY++;
        resultText = new JTextArea(FrameSetting.DEFAULT_RESULT,
                FrameSetting.RESULT_FIELD_ROWS, FrameSetting.RESULT_FIELD_COLUMNS);
        resultText.setEditable(false);  // 結果表示用のフィールドなので編集不可
        putComponent(resultText, gridX++, gridY, FrameSetting.Grid.WIDTH * 5, FrameSetting.Grid.HEIGHT);
    }

    /**
     * GridBagConstraints の設定に使う値を初期化
     */
    private void initGrid() {
        gridX = FrameSetting.Grid.X_DEFAULT;
        gridY = FrameSetting.Grid.Y_DEFAULT;
    }

    /**
     * コンポーネントを配置
     */
    private void putComponent(Component component, int x, int y, int width, int height) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;    //位置x
        constraints.gridy = y;    //位置y
        constraints.gridwidth = width;    //コンポーネントの表示領域のセル数 横
        constraints.gridheight = height;    //コンポーネントの表示領域のセル数 縦
        layout.setConstraints(component, constraints);
        add(component);
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
        if (e.getSource() == btnCreateObject) {
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
