package views;

import models.MyObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ObjectFrame<T, Eparam, Efield> extends JFrame implements ActionListener {
    private MyObject<T> obj;
    private final String name;

    private List<Field> fields;
    private Field lastSelectedField;
    private List<Method> methods;
    private Method lastSelectedMethod;
    private List<Efield> fieldValues = new ArrayList<>();
    private Efield fieldValue;
    private List<Eparam> methodValues = new ArrayList<>();

    private String resultMsg = "";
    private static final String LS = "\n";

    private GridBagLayout layout = new GridBagLayout();
    private int gridX = FrameSetting.Grid.X_DEFAULT;
    private int gridY = FrameSetting.Grid.Y_DEFAULT;

    private static class ComponentPosition {
        private static int btnGridX = 0;

        public static void updateBtnGridX(int x) {
            btnGridX = (btnGridX > x + 1) ? btnGridX : x + 1;
        }
    }

    private final JLabel fieldLabel = new JLabel(FrameSetting.TextLabel.FIELD);
    private final JLabel methodLabel = new JLabel(FrameSetting.TextLabel.METHOD);

    private JTextArea resultText;

    private JComboBox fieldChoice = new JComboBox();
    private JComboBox methodChoice = new JComboBox();

    private final JButton btnFieldParams = new JButton(FrameSetting.ButtonLabel.PARAMS);
    private final JButton btnChangeField = new JButton(FrameSetting.ButtonLabel.CHANGE_FIELD);
    private final JButton btnMethodParams = new JButton(FrameSetting.ButtonLabel.PARAMS);
    private final JButton btnCallMethod = new JButton(FrameSetting.ButtonLabel.CALL_METHOD);

    public ObjectFrame(MyObject obj) {
        this.obj = obj;
        this.name = obj.getName();
        fields = obj.getFields();
        methods = obj.getMethods();

        init();
    }

    void init() {
        setTitle(FrameSetting.FRAME_OBJECT + " " + name + " #" + obj.getId());
        initBounds();

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

        initInputFields();
        initButtons();
    }

    /**
     * ウィンドウの位置を設定
     */
    private void initBounds() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //画面全体のサイズ
        int nx = (int) (d.width * 0.5);
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

        btnFieldParams.addActionListener(this);
        btnFieldParams.setForeground(FrameSetting.PARAMS_BTN_COLOR);
        putComponent(btnFieldParams,
                ComponentPosition.btnGridX + 1, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnChangeField.addActionListener(this);
        putComponent(btnChangeField,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnMethodParams.addActionListener(this);
        btnMethodParams.setForeground(FrameSetting.PARAMS_BTN_COLOR);
        putComponent(btnMethodParams,
                ComponentPosition.btnGridX + 1, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnCallMethod.addActionListener(this);
        putComponent(btnCallMethod,
                ComponentPosition.btnGridX + 2, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
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
    private void initInputFields() {
        initGrid();

        // Fields
        putComponent(fieldLabel, gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        setComboBoxes(fieldChoice, obj.getFields());
        putComponent(fieldChoice, ++gridX, gridY,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

        // Methods
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(methodLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        setComboBoxes(methodChoice, methods);
        putComponent(methodChoice, ++gridX, gridY,
                FrameSetting.Grid.WIDTH * 2, FrameSetting.Grid.HEIGHT);
        ComponentPosition.updateBtnGridX(gridX);

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
     * Frame に紐づいたオブジェクトを取得
     */
    MyObject getMyObject() {
        return obj;
    }

    /**
     * FieldFrame で設定したフィールドの値
     */
    Efield getFieldValue() {
        return fieldValue;
    }

    /**
     * FieldFrame で設定したフィールドの値
     */
    List<Efield> getFieldValues() {
        return fieldValues;
    }

    /**
     * FieldFrame で設定したフィールドの値
     */
    List<Eparam> getMethodValues() {
        return methodValues;
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
     * コンボボックスを設定
     */
    private <E> void setComboBoxes(JComboBox c, List<E> items) {
        c.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, c.getPreferredSize().height));
        putComboBoxItems(c, items);
        c.addActionListener(this);
        c.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
    }

    /**
     * フィールドの値を保存
     */
    public void setFieldValue(Efield newValue) {
        fieldValue = newValue;
    }

    /**
     * メソッドパラメータの値を保存
     */
    public void setMethodValues(List<Eparam> newValues) {
        methodValues = newValues;
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
     * イベント発生時の挙動を設定
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFieldParams) {
            try {
                showFieldWindow();
            } catch (Exception ex) {
                resultMsg += ex + LS;
                resultText.setText(resultMsg);
                ex.printStackTrace();
            }
        } else if (e.getSource() == btnChangeField) {
            changeField();
        } else if (e.getSource() == btnMethodParams) {
            showParamWindow();
        } else if (e.getSource() == btnCallMethod) {
            callMethod();
        } else if (e.getSource() == fieldChoice) {
            fieldValue = null;
        } else if (e.getSource() == methodChoice) {
            methodValues = new ArrayList<>();
        }
    }

    // メソッドのパラメータ設定を表示
    private void showFieldWindow() throws NoSuchFieldException, IllegalAccessException {
        final Field f = fields.get(fieldChoice.getSelectedIndex());
        lastSelectedField = f;

        ObjectFieldFrame pFrame;
        setFieldValue((Efield) obj.getField(f.getName()));
        try {
            pFrame = new ObjectFieldFrame(this, f.getName(), f, this.fieldValue);
            pFrame.setVisible(true);
            lastSelectedField = f;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            resultText.setText(resultMsg);
            ex.printStackTrace();
        }
    }

    // メソッドのパラメータ設定を表示
    private void showParamWindow() {
        final Method m = methods.get(methodChoice.getSelectedIndex());
        Parameter[] params = m.getParameters();
        if (lastSelectedMethod != m) {
            methodValues = new ArrayList<>();
            for (int i = 0; i < params.length; i++) {
                methodValues.add((Eparam) "");
            }
        }
        final ObjectParamFrame pFrame = new ObjectParamFrame(this, m.getName(), m.getParameters(), methodValues);
        pFrame.setVisible(true);
        lastSelectedMethod = m;
    }

    // フィールドの変更
    private void changeField() {
        if (fieldValue == null) {
            return;
        }

        final Field f = fields.get(fieldChoice.getSelectedIndex());
        resultMsg += FrameSetting.Message.CHANGE_FIELD + ": " + f.getName() + LS;

        try {
            obj.changeField(f.getName(), fieldValue);
            resultMsg += FrameSetting.Message.SUCCESS + LS;
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
    }

    // メソッドの呼び出し
    private Object callMethod() {
        final Method m = methods.get(methodChoice.getSelectedIndex());
        resultMsg += FrameSetting.Message.CALL_METHOD + ": " + m.getName() + LS;

        Object result = new Object();
        try {
            result = obj.callMethod(m, methodValues.toArray());
            if (result != null) {
                resultMsg += m.getName() + " returns " + result.toString() + LS;
            } else {
                resultMsg += m.getName() + " returns nothing" + LS;
            }
        } catch (Exception ex) {
            resultMsg += ex + LS;
            ex.printStackTrace();
        }
        resultText.setText(resultMsg);
        return result;
    }
}
