package views;

import models.ClassSearch;
import models.MyObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class MainFrame<E> extends JFrame implements ActionListener {
    ClassSearch cs = new ClassSearch();
    Class cls;
    List<Constructor> constructors = new ArrayList<>();
    List<E> values = new ArrayList<>();
    Constructor lastSelected;

    private String resultMsg = "";
    private static final String LS = "\n";

    private GridBagLayout layout = new GridBagLayout();
    private int gridX = FrameSetting.Grid.X_DEFAULT;
    private int gridY = FrameSetting.Grid.Y_DEFAULT;

    private static class ComponentPosition {
        private static Point constructorName = new Point();
        private static int btnGridX = 0;

        public static void updateBtnGridX(int x) {
            btnGridX = (btnGridX > x + 1) ? btnGridX : x + 1;
        }
    }

    private JLabel typeLabel = new JLabel(FrameSetting.TextLabel.CLASS);
    private JLabel constructorLabel = new JLabel(FrameSetting.TextLabel.CONSTRUCTOR);

    private JTextField typeText;
    private JTextField constructorNameText;   // JComboBox 表示前の仮置き
    private JTextArea resultText;

    private JComboBox constructorChoice;

    private JButton btnSearchClass;
    private JButton btnConstructorParams;
    private JButton btnCallConstructor;

    public static void main(String[] args) {
        MainFrame me = new MainFrame();
        me.init();
        me.setVisible(true);
    }

    private void init() {
        setTitle(FrameSetting.FRAME_TITLE);
        initBounds();
        initCloseOperation();

        Container c = getContentPane();
        initAppearance(c);

        setResizable(true);
    }

    /**
     * ウィンドウの見た目を設定
     */
    private void initAppearance(Container c) {
        Color color = FrameSetting.BG_COLOR;
        c.setBackground(color);

        setLayout(layout);

        initTextFields();
        initButtons();
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
     * ボタンを設定
     */
    private void initButtons() {
        initGrid();

        btnSearchClass = new JButton(FrameSetting.ButtonLabel.SEARCH_CLASS);
        btnSearchClass.addActionListener(this);
        putComponent(btnSearchClass,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnConstructorParams = new JButton(FrameSetting.ButtonLabel.PARAMS);
        btnConstructorParams.addActionListener(this);
        btnConstructorParams.setForeground(FrameSetting.PARAMS_BTN_COLOR);
        putComponent(btnConstructorParams,
                ComponentPosition.btnGridX + 1, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        btnConstructorParams.setVisible(false);

        btnCallConstructor = new JButton(FrameSetting.ButtonLabel.CALL_CONSTRUCTOR);
        btnCallConstructor.addActionListener(this);
        putComponent(btnCallConstructor,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        btnCallConstructor.setVisible(false);
    }

    /**
     * ウィンドウ終了時の動作を初期化
     */
    private void initCloseOperation() {
        // ウィンドウが閉じられたらプログラムを終了
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * GridBagConstraints の設定に使う値を初期化
     */
    private void initGrid() {
        gridX = FrameSetting.Grid.X_DEFAULT;
        gridY = FrameSetting.Grid.Y_DEFAULT;
    }

    /**
     * テキストフィールドを設定
     */
    private void initTextFields() {
        initGrid();

        // Search class
        putComponent(typeLabel, gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        typeText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(typeText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Call constructors
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(constructorLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        constructorNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
        putComponent(constructorNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.constructorName = new Point(gridX, gridY);
        ComponentPosition.updateBtnGridX(gridX);
        constructorLabel.setVisible(false);
        constructorNameText.setVisible(false);

        // Result
        gridX = FrameSetting.Grid.X_DEFAULT;
        resultText = new JTextArea(FrameSetting.DEFAULT_RESULT,
                FrameSetting.RESULT_FIELD_ROWS, FrameSetting.RESULT_FIELD_COLUMNS);
        resultText.setEditable(false);  // 結果表示用のフィールドなので編集不可
        JScrollPane jp = new JScrollPane(resultText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        putComponent(jp, gridX, ++gridY, FrameSetting.Grid.WIDTH * 6, FrameSetting.Grid.HEIGHT);
    }

    /**
     * String が null か
     */
    private boolean isStringNull(String str) {
        return str == null || str.isEmpty();
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
        constructors = cs.getConstructors(cls);
        putComboBoxItems(constructorChoice, cs.getConstructors(cls));
        putComponent(constructorChoice, ComponentPosition.constructorName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        constructorChoice.addActionListener(this);
        constructorChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        if (!constructorChoice.isVisible()) {
            replaceComponent(constructorNameText, constructorChoice);
        }
    }

    /**
     * パラメータの値を保存
     */
    public void setValues(List<E> newValues) {
        values = newValues;
    }

    /**
     * コンボボックスの項目を設定
     */
    private <T> void putComboBoxItems(JComboBox comboBox, List<T> list) {
        list.forEach(i -> {
            comboBox.addItem(i);
        });
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
     * ボタンを押したときの挙動
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearchClass) {
            constructors.clear();
            searchClass();
            showCallConstructor();
        } else if (e.getSource() == btnConstructorParams) {
            showParamWindow();
        } else if (e.getSource() == btnCallConstructor) {
            Object obj = callConstructor();
            showObjectWindow(obj);
        } else if (e.getSource() == constructorChoice) {
            values = new ArrayList<>();
        }
    }

    // クラスを検索
    private void searchClass() {
        final String value = typeText.getText();
        if (isStringNull(value)) {
            resultMsg += FrameSetting.Message.ERROR_EMPTY_VALUE + LS;
            resultText.setText(resultMsg);
            return;
        }

        resultMsg += FrameSetting.Message.SEARCH_CLASS + LS;
        try {
            cls = cs.searchClass(value);
            resultMsg += FrameSetting.Message.SUCCESS + LS;
        } catch (Exception e) {
            resultMsg += e + LS;
            resultText.setText(resultMsg);
            e.printStackTrace();
            return;
        }
        resultText.setText(resultMsg);
    }

    // コンストラクタ呼び出し
    private Object callConstructor() {
        final Constructor c = constructors.get(constructorChoice.getSelectedIndex());
        Object obj = new Object();
        try {
            obj = cs.callConstructor(c, values.toArray());
            resultMsg += FrameSetting.Message.SUCCESS + LS;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
        return obj;
    }

    // コンストラクタ呼び出しのフィールドを表示
    private void showCallConstructor() {
        constructorLabel.setVisible(true);
        btnConstructorParams.setVisible(true);
        btnCallConstructor.setVisible(true);
        if (constructorChoice == null) {
            setComboBoxes();
            return;
        }
        if (constructors.isEmpty()) {
            constructors = cs.getConstructors(cls);
        }
        constructorChoice.removeAllItems();
//        setComboBoxes();
        putComboBoxItems(constructorChoice, constructors);
    }

    // コンストラクタのパラメータ設定を表示
    private void showObjectWindow(Object obj) {
        MyObject myObject = new MyObject(cls, obj);
        ObjectFrame objFrame = new ObjectFrame(myObject);
        objFrame.setVisible(true);
    }

    // コンストラクタのパラメータ設定を表示
    private void showParamWindow() {
        final Constructor c = constructors.get(constructorChoice.getSelectedIndex());
        Parameter[] params = c.getParameters();
        if (lastSelected != c) {
            values = new ArrayList<>();
            for (int i = 0; i < params.length; i++) {
                values.add((E) "");
            }
        }
        final ConstructorParamFrame pFrame = new ConstructorParamFrame(this, c.getName(), c.getParameters(), values);
        pFrame.setVisible(true);
        lastSelected = c;
    }
}
