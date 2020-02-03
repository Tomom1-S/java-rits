package views;

import models.ObjectHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class MyFrame extends JFrame implements ActionListener {
    ObjectHandler oh = new ObjectHandler();
    private String resultMsg = "";
    private static final String LS = "\n";

    private GridBagLayout layout = new GridBagLayout();
    private int gridX = FrameSetting.Grid.X_DEFAULT;
    private int gridY = FrameSetting.Grid.Y_DEFAULT;

    private static class ComponentPosition {
        private static Point constructorName = new Point();
        private static Point fieldName = new Point();
        private static Point methodName = new Point();
        private static int btnGridX = 0;

        public static void updateBtnGridX(int x) {
            btnGridX = (btnGridX > x + 1) ? btnGridX : x + 1;
        }
    }

    private JTextField typeText;
    private JTextField fieldNameText;   // TODO 選択式にする
    private JTextField fieldText;
    private JTextField methodNameText;   // TODO 選択式にする
    private JTextField methodParamText;
    private JTextField constructorNameText;   // TODO 選択式にする
    private JTextField constructorParamText;
    private JTextArea resultText;

    private JComboBox constructorChoice;
    private JComboBox fieldChoice;
    private JComboBox methodChoice;

    private JButton btnCreateObject;
    private JButton btnCallConstructor;
    private JButton btnChangeField;
    private JButton btnCallMethod;
    private JButton btnCreateArray;
    private JButton btnGetArrayElement;
    private JButton btnSetArrayElement;

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
        int nx = (int) (d.width * 0.8);
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
                ComponentPosition.btnGridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnCallConstructor = new JButton(FrameSetting.ButtonLabel.CALL_CONSTRUCTOR);
        btnCallConstructor.addActionListener(this);
        putComponent(btnCallConstructor,
                ComponentPosition.btnGridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnChangeField = new JButton(FrameSetting.ButtonLabel.CHANGE_FIELD);
        btnChangeField.addActionListener(this);
        putComponent(btnChangeField,
                ComponentPosition.btnGridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnCallMethod = new JButton(FrameSetting.ButtonLabel.CALL_METHOD);
        btnCallMethod.addActionListener(this);
        putComponent(btnCallMethod,
                ComponentPosition.btnGridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
    }

    /**
     * テキストフィールドを設定
     */
    private void initTextFields(GridBagLayout layout, GridBagConstraints constraints) {
        initGrid();

        // Create object
        putComponent(new JLabel(FrameSetting.TextLabel.TYPE),
                gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        typeText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(typeText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);
        gridX++;    // 幅が2倍だから

        // Call constructor
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(new JLabel(FrameSetting.TextLabel.CONSTRUCTOR),
                gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        constructorNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(constructorNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.constructorName = new Point(gridX, gridY);
        gridX++;    // 幅が2倍だから
        putComponent(new JLabel(FrameSetting.TextLabel.PARAMETER),
                ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        constructorParamText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(constructorParamText, ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Change field
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(new JLabel(FrameSetting.TextLabel.FIELD),
                gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        fieldNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(fieldNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.fieldName = new Point(gridX, gridY);
        gridX += 2;    // 幅が2倍だから
        fieldText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(fieldText, ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Call method
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(new JLabel(FrameSetting.TextLabel.METHOD),
                gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        methodNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(methodNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.methodName = new Point(gridX, gridY);
        gridX++;    // 幅が2倍だから
        putComponent(new JLabel(FrameSetting.TextLabel.PARAMETER),
                ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        methodParamText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(methodParamText, ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Result
        gridX = FrameSetting.Grid.X_DEFAULT;
        resultText = new JTextArea(FrameSetting.DEFAULT_RESULT,
                FrameSetting.RESULT_FIELD_ROWS, FrameSetting.RESULT_FIELD_COLUMNS);
        resultText.setEditable(false);  // 結果表示用のフィールドなので編集不可
        JScrollPane jp = new JScrollPane(resultText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        putComponent(jp, gridX, ++gridY, FrameSetting.Grid.WIDTH * 6, FrameSetting.Grid.HEIGHT);
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

    private void putComponent(Component component, Point pt, int width, int height) {
        putComponent(component, pt.x, pt.y, width, height);
    }

    /**
     * コンボボックスを設定
     */
    private void setComboBoxes() {
        // Constructors
        constructorChoice = new JComboBox();
        constructorChoice.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, constructorChoice.getPreferredSize().height));
        putComboBoxItems(constructorChoice, oh.getConstructors());
        putComponent(constructorChoice, ComponentPosition.constructorName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        constructorChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        replaceComponent(constructorNameText, constructorChoice);

        // Fields
        fieldChoice = new JComboBox();
        fieldChoice.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, constructorChoice.getPreferredSize().height));
        putComboBoxItems(fieldChoice, oh.getFields());
        putComponent(fieldChoice, ComponentPosition.fieldName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        fieldChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        replaceComponent(fieldNameText, fieldChoice);

        // Methods
        methodChoice = new JComboBox();
        methodChoice.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, constructorChoice.getPreferredSize().height));
        putComboBoxItems(methodChoice, oh.getMethods());
        putComponent(methodChoice, ComponentPosition.methodName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        methodChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        replaceComponent(methodNameText, methodChoice);
    }

    /**
     * コンボボックスの項目を設定
     */
    private <T> void putComboBoxItems(JComboBox comboBox, List<T> list) {
        for (Iterator<T> i = list.iterator(); i.hasNext(); ) {
            T t = i.next();
            comboBox.addItem(t);
        }
    }

    /**
     * コンポーネントを置き換え
     */
    private void replaceComponent(Component oldComp, Component newComp) {
        oldComp.setVisible(false);
        newComp.setVisible(true);
        newComp.revalidate();
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
            createObject();
            setComboBoxes();
        } else if (e.getSource() == btnCallConstructor) {
            callConstructor();
        } else if (e.getSource() == btnChangeField) {
            changeField();
        } else if (e.getSource() == btnCallMethod) {
            callMethod();
        }
    }

    private void createObject() {
        resultMsg += FrameSetting.Message.CREATE_OBJECT + LS;
        try {
            oh.createObject(typeText.getText());
            resultMsg += FrameSetting.Message.SUCCESS + LS;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
    }

    private void searchClass() {
        resultMsg += FrameSetting.Message.SEARCH_CLASS + LS;
        resultMsg += FrameSetting.Message.SUCCESS + LS;
        resultText.setText(resultMsg);
    }

    private void callConstructor() {
        resultMsg += FrameSetting.Message.CALL_CONSTRUCTOR + LS;
        try {
            oh.callConstructor();
            resultMsg += FrameSetting.Message.SUCCESS + LS;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
    }

    private void changeField() {
        resultMsg += FrameSetting.Message.CHANGE_FIELD + LS;

        final String selected = String.valueOf(fieldChoice.getSelectedItem());
        final String fieldName = selected.substring(selected.lastIndexOf('.') + 1);
        try {
            oh.changeField(fieldName, fieldText.getText());
            resultMsg += FrameSetting.Message.SUCCESS + LS;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
    }

    private void callMethod() {
        resultMsg += FrameSetting.Message.CALL_METHOD + LS;
        try {
            Object result = oh.callMethod(methodNameText.getText());
            resultMsg += "Result: " + result.toString() + LS;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
    }
}
