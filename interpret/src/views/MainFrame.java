package views;

import models.ClassSearch;
import models.MyArray;
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
    List<ObjectFrame> objList = new ArrayList<>();
    List<ArrayFrame> arrList = new ArrayList<>();

    private String resultMsg = "";
    private static final String LS = "\n";

    private GridBagLayout layout = new GridBagLayout();
    private int gridX = FrameSetting.Grid.X_DEFAULT;
    private int gridY = FrameSetting.Grid.Y_DEFAULT;

    private static class ComponentPosition {
        private static Point constructorName = new Point();
        private static Point objectName = new Point();
        private static Point arrayName = new Point();
        private static int btnGridX = 0;

        public static void updateBtnGridX(int x) {
            btnGridX = (btnGridX > x + 1) ? btnGridX : x + 1;
        }
    }

    private final JLabel typeLabel = new JLabel(FrameSetting.TextLabel.CLASS);
    private final JLabel constructorLabel = new JLabel(FrameSetting.TextLabel.CONSTRUCTOR);

    private final JLabel arrayLabel = new JLabel(FrameSetting.TextLabel.ARRAY);
    private final JLabel arrayTypeLabel = new JLabel(FrameSetting.TextLabel.ARRAY_TYPE);
    private final JLabel arraySizeLabel = new JLabel(FrameSetting.TextLabel.ARRAY_SIZE);

    private final JLabel historyLabel = new JLabel(FrameSetting.TextLabel.HISTORY);
    private final JLabel historyObjectLabel = new JLabel(FrameSetting.TextLabel.OBJECT_HISTORY);
    private final JLabel historyArrayLabel = new JLabel(FrameSetting.TextLabel.ARRAY_HISTORY);

    private final JTextField typeText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
    private final JTextField constructorNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);  // JComboBox 表示前の仮置き
    private final JTextField arrayTypeText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
    private final JTextField arraySizeText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
    private final JTextField objectNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);   // JComboBox 表示前の仮置き
    private final JTextField arrayNameText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);   // JComboBox 表示前の仮置き

    private final JTextArea resultText = new JTextArea(FrameSetting.DEFAULT_RESULT,
            FrameSetting.RESULT_FIELD_ROWS, FrameSetting.RESULT_FIELD_COLUMNS);

    private JComboBox constructorChoice;
    private JComboBox objectChoice;
    private JComboBox arrayChoice;

    private final JButton btnSearchClass = new JButton(FrameSetting.ButtonLabel.SEARCH_CLASS);
    private final JButton btnConstructorParams = new JButton(FrameSetting.ButtonLabel.PARAMS);
    private final JButton btnCallConstructor = new JButton(FrameSetting.ButtonLabel.CALL_CONSTRUCTOR);
    private final JButton btnCreateArray = new JButton(FrameSetting.ButtonLabel.CREATE_ARRAY);
    private final JButton btnOpenObjectFrame = new JButton(FrameSetting.ButtonLabel.OPEN_OBJECT_WINDOW);
    private final JButton btnOpenArrayFrame = new JButton(FrameSetting.ButtonLabel.OPEN_ARRAY_WINDOW);

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
        int nx = (int) (d.width * 0.6);
        int ny = (int) (d.height * 0.6);
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

        btnSearchClass.addActionListener(this);
        putComponent(btnSearchClass,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnConstructorParams.addActionListener(this);
        btnConstructorParams.setForeground(FrameSetting.PARAMS_BTN_COLOR);
        putComponent(btnConstructorParams,
                ComponentPosition.btnGridX + 1, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        btnConstructorParams.setVisible(false);

        btnCallConstructor.addActionListener(this);
        putComponent(btnCallConstructor,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        btnCallConstructor.setVisible(false);

        btnCreateArray.addActionListener(this);
        gridY += 2;
        putComponent(btnCreateArray,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnOpenObjectFrame.addActionListener(this);
        putComponent(btnOpenObjectFrame,
                ComponentPosition.btnGridX + 2, ComponentPosition.objectName.y,
                FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        btnOpenObjectFrame.setVisible(false);

        btnOpenArrayFrame.addActionListener(this);
        putComponent(btnOpenArrayFrame,
                ComponentPosition.btnGridX + 2, ComponentPosition.arrayName.y,
                FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        btnOpenArrayFrame.setVisible(false);
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
        putComponent(typeText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Call constructors
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(constructorLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(constructorNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.constructorName = new Point(gridX, gridY);
        ComponentPosition.updateBtnGridX(gridX);
        constructorLabel.setVisible(false);
        constructorNameText.setVisible(false);

        // Create array
        gridX = FrameSetting.Grid.X_DEFAULT;
        arrayLabel.setForeground(FrameSetting.ACCENT_COLOR);
        putComponent(arrayLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(arrayTypeLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(arrayTypeText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(arraySizeLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(arraySizeText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Result
        gridX = FrameSetting.Grid.X_DEFAULT;
        resultText.setEditable(false);  // 結果表示用のフィールドなので編集不可
        JScrollPane jp = new JScrollPane(resultText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        putComponent(jp, gridX, ++gridY, FrameSetting.Grid.WIDTH * 6, FrameSetting.Grid.HEIGHT);

        // History
        gridX = FrameSetting.Grid.X_DEFAULT;
        historyLabel.setForeground(FrameSetting.ACCENT_COLOR);
        putComponent(historyLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        historyLabel.setVisible(false);

        // Open object's windows
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(historyObjectLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(objectNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.objectName = new Point(gridX, gridY);
        ComponentPosition.updateBtnGridX(gridX);
        historyObjectLabel.setVisible(false);
        objectNameText.setVisible(false);

        // Open array's windows
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(historyArrayLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(arrayNameText, ++gridX, gridY, FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.arrayName = new Point(gridX, gridY);
        ComponentPosition.updateBtnGridX(gridX);
        historyArrayLabel.setVisible(false);
        arrayNameText.setVisible(false);
    }

    /**
     * オブジェクトウィンドウの表示機能を初期化
     */
    private void initOpenObjectWindowFunc() {
        if (objList.isEmpty()) {
            return;
        }
        if (!historyLabel.isVisible()) {
            historyLabel.setVisible(true);
        }
        if (!historyObjectLabel.isVisible()) {
            historyObjectLabel.setVisible(true);
            replaceComponent(objectNameText, objectChoice);
            btnOpenObjectFrame.setVisible(true);
        }

        objectChoice.removeAllItems();
        putComboBoxItems(objectChoice, getObjectNameList());
    }

    List<String> getObjectNameList() {
        List<String> objNames = new ArrayList<>();
        for (ObjectFrame frame : objList) {
            MyObject obj = frame.getMyObject();
            objNames.add(obj.getCls().getName() + " #" + obj.getId());
        }
        return objNames;
    }

    /**
     * 配列ウィンドウの表示機能を初期化
     */
    private void initOpenArrayWindowFunc() {
        if (arrList.isEmpty()) {
            return;
        }

        if (arrayChoice == null) {
            setComboBoxesForArray();
        }
        if (!historyLabel.isVisible()) {
            historyLabel.setVisible(true);
        }
        if (!historyArrayLabel.isVisible()) {
            historyArrayLabel.setVisible(true);
            replaceComponent(arrayNameText, arrayChoice);
            btnOpenArrayFrame.setVisible(true);
        }

        arrayChoice.removeAllItems();
        putComboBoxItems(arrayChoice, getArrayNameList());
    }

    List<String> getArrayNameList() {
        List<String> arrNames = new ArrayList<>();
        for (final ArrayFrame frame : arrList) {
            final MyArray arr = frame.getMyArray();
            arrNames.add(arr.getCls().getName() + " Array #" + arr.getId());
        }
        return arrNames;
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
    private void setComboBoxesForObject() {
        // Constructors
        constructorChoice = new JComboBox();
        constructorChoice.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, constructorChoice.getPreferredSize().height));
        constructors = cs.getConstructors(cls);
        putComboBoxItems(constructorChoice, constructors);
        putComponent(constructorChoice, ComponentPosition.constructorName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        constructorChoice.addActionListener(this);
        constructorChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        if (!constructorChoice.isVisible()) {
            replaceComponent(constructorNameText, constructorChoice);
        }

        // Objects
        objectChoice = new JComboBox();
        objectChoice.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, objectChoice.getPreferredSize().height));
        putComponent(objectChoice, ComponentPosition.objectName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        objectChoice.addActionListener(this);
        objectChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        objectChoice.setVisible(false);
    }

    private void setComboBoxesForArray() {
        // Arrays
        arrayChoice = new JComboBox();
        arrayChoice.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, arrayChoice.getPreferredSize().height));
        putComponent(arrayChoice, ComponentPosition.arrayName,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        arrayChoice.addActionListener(this);
        arrayChoice.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
        arrayChoice.setVisible(false);
        if (!arrayChoice.isVisible()) {
            replaceComponent(arrayNameText, arrayChoice);
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
            if (isStringNull(typeText.getText())) {
                resultMsg += FrameSetting.Message.ERROR_EMPTY_VALUE + LS;
                resultText.setText(resultMsg);
                return;
            }
            constructors.clear();
            searchClass();
            showCallConstructor();
        } else if (e.getSource() == btnConstructorParams) {
            showParamWindow();
        } else if (e.getSource() == btnCreateArray) {
            createArray();
            initOpenArrayWindowFunc();
        } else if (e.getSource() == btnCallConstructor) {
            callConstructor();
            initOpenObjectWindowFunc();
        } else if (e.getSource() == btnOpenObjectFrame) {
            showClosedObjectWindow();
        } else if (e.getSource() == btnOpenArrayFrame) {
            showClosedArrayWindow();
        } else if (e.getSource() == constructorChoice) {
            values = new ArrayList<>();
        }
    }

    // クラスを検索
    private void searchClass() {
        if (isNullForTextField(typeText)) {
            return;
        }

        final String value = typeText.getText();
        resultMsg += FrameSetting.Message.SEARCH_CLASS + ": " + value + LS;
        try {
            cls = cs.searchClass(value);
        } catch (Exception e) {
            resultMsg += e + LS;
            resultText.setText(resultMsg);
            e.printStackTrace();
            return;
        }
        resultText.setText(resultMsg);
    }

    // コンストラクタ呼び出し
    private void callConstructor() {
        final Constructor c = constructors.get(constructorChoice.getSelectedIndex());
        resultMsg += FrameSetting.Message.CALL_CONSTRUCTOR + ": " + c.getName() + LS;

        Object obj;
        try {
            obj = cs.callConstructor(c, values.toArray());
            showNewObjectWindow(obj);
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
        return;
    }

    // コンストラクタ呼び出しのフィールドを表示
    private void showCallConstructor() {
        constructorLabel.setVisible(true);
        btnConstructorParams.setVisible(true);
        btnCallConstructor.setVisible(true);
        if (constructorChoice == null) {
            setComboBoxesForObject();
            return;
        }
        if (constructors.isEmpty()) {
            constructors = cs.getConstructors(cls);
        }
        constructorChoice.removeAllItems();
        putComboBoxItems(constructorChoice, constructors);
    }

    // 新しいオブジェクトウィンドウを表示
    private void createArray() {
        if (isNullForTextField(arrayTypeText) || isNullForTextField(arraySizeText)) {
            return;
        }

        final String arrayType = arrayTypeText.getText();
        Class arrayCls;
        try {
            arrayCls = cs.searchClass(arrayType);
        } catch (Exception e) {
            resultMsg += e + LS;
            resultText.setText(resultMsg);
            e.printStackTrace();
            return;
        }

        final MyArray myArray = new MyArray(arrayCls, Integer.parseInt(arraySizeText.getText()));
        resultMsg += FrameSetting.Message.CREATE_ARRAY + ": " + myArray.getName() + " #" + myArray.getId() + LS;
        resultText.setText(resultMsg);

        final ArrayFrame arrFrame = new ArrayFrame(this, myArray);
        arrList.add(arrFrame);
        arrFrame.setVisible(true);
    }

    // 新しいオブジェクトウィンドウを表示
    private void showNewObjectWindow(Object obj) {
        final MyObject myObject = new MyObject(cls, obj);
        resultMsg += FrameSetting.Message.OPEN_WINDOW + ": " + myObject.getCls() + " #" + myObject.getId() + LS;
        resultText.setText(resultMsg);

        final ObjectFrame objFrame = new ObjectFrame(this, myObject);
        objList.add(objFrame);
        objFrame.setVisible(true);
    }

    // 閉じたオブジェクトウィンドウを再度表示
    private void showClosedObjectWindow() {
        final ObjectFrame objFrame = objList.get(objectChoice.getSelectedIndex());
        final MyObject myObject = objFrame.getMyObject();

        if (objFrame.isVisible()) {
            objFrame.toFront();
        } else {
            objFrame.setVisible(true);
            resultMsg += FrameSetting.Message.OPEN_WINDOW + ": " + myObject.getCls() + " #" + myObject.getId() + LS;
            resultText.setText(resultMsg);
        }
    }

    // 閉じた配列ウィンドウを再度表示
    private void showClosedArrayWindow() {
        final ArrayFrame arrFrame = arrList.get(arrayChoice.getSelectedIndex());
        final MyArray myArray = arrFrame.getMyArray();

        if (arrFrame.isVisible()) {
            arrFrame.toFront();
        } else {
            arrFrame.setVisible(true);
            resultMsg += FrameSetting.Message.OPEN_WINDOW + ": " + myArray.getCls() + " #" + myArray.getId() + LS;
            resultText.setText(resultMsg);
        }
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

    // JTextField の値が null ならメッセージを出す
    private boolean isNullForTextField(final JTextField field) {
        final String text = field.getText();
        if (!isStringNull(text)) {
            return false;
        }
        resultMsg += FrameSetting.Message.ERROR_EMPTY_VALUE + LS;
        resultText.setText(resultMsg);
        return true;
    }
}
