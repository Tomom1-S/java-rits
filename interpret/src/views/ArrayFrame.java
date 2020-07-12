package views;

import models.MyArray;
import models.MyObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArrayFrame<T> extends JFrame implements ActionListener {
    private final MainFrame parent;
    private MyArray<T> array;
    private final String name;

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

    private final JLabel indexLabel = new JLabel(FrameSetting.TextLabel.ARRAY_INDEX);
    private final JLabel elementLabel = new JLabel(FrameSetting.TextLabel.ARRAY_ELEMENT);

    private JTextArea resultText;

    private final JTextField indexText = new JTextField(FrameSetting.TEXT_FIELD_LENGTH);
    private JComboBox objectChoice = new JComboBox();

    private final JButton btnGetElement = new JButton(FrameSetting.ButtonLabel.GET_ARRAY_ELEMENT);
    private final JButton btnSetElement = new JButton(FrameSetting.ButtonLabel.SET_ARRAY_ELEMENT);

    public ArrayFrame(MainFrame parent, MyArray array) {
        this.parent = parent;
        this.array = array;
        this.name = array.getName();

        init();
    }

    void init() {
        setTitle(FrameSetting.FRAME_ARRAY + " " + name + " #" + array.getId());
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

        btnGetElement.addActionListener(this);
        putComponent(btnGetElement,
                ComponentPosition.btnGridX + 1, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        btnSetElement.addActionListener(this);
        putComponent(btnSetElement,
                ComponentPosition.btnGridX + 1, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
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

        // Index
        putComponent(indexLabel, gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        putComponent(indexText, ++gridX, gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);

        // Element
        gridX = FrameSetting.Grid.X_DEFAULT;
        putComponent(elementLabel, gridX, ++gridY, FrameSetting.Grid.WIDTH, FrameSetting.Grid.HEIGHT);
        objectChoice.removeAllItems();
        putComboBoxItems(objectChoice, parent.getObjectNameList());
        putComponent(objectChoice, ++gridX, gridY,
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
     * Frame に紐づいた配列を取得
     */
    MyArray getMyArray() {
        return array;
    }

    /**
     * オブジェクトの JComboBox を取得
     */
    JComboBox getObjectChoice() {
        return objectChoice;
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
    <E> void setComboBoxes(JComboBox c, List<E> items) {
        c.setPreferredSize(
                new Dimension(FrameSetting.COMBO_BOX_WIDTH, c.getPreferredSize().height));
        putComboBoxItems(c, items);
        c.addActionListener(this);
        c.setFont(new Font("", Font.PLAIN, FrameSetting.COMBO_BOX_FONT_SIZE));
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
        if (e.getSource() == btnGetElement) {
            getElement();
        } else if (e.getSource() == btnSetElement) {
            setElement();
        }
    }

    private void getElement() {
        final int index = Integer.parseInt(indexText.getText());
        resultMsg += FrameSetting.Message.GET_ELEMENT + ": index " + index + LS;

        T object;
        try {
            object = array.getElement(index);
        } catch (Exception e) {
            resultMsg += e + LS;
            resultText.setText(resultMsg);
            e.printStackTrace();
            return;
        }

        if (object == null) {
            resultMsg += FrameSetting.Message.EMPTY_ELEMENT + ": index " + index + LS;
            resultText.setText(resultMsg);
            return;
        }

        ObjectFrame objFrame;
        try {
            objFrame = (ObjectFrame) parent.objList.get(index);
        } catch (Exception e) {
            resultMsg += e + LS;
            resultText.setText(resultMsg);
            e.printStackTrace();
            return;
        }

        MyObject myObject = objFrame.getMyObject();
        if (objFrame.isVisible()) {
            objFrame.toFront();
        } else {
            objFrame.setVisible(true);
            resultMsg += FrameSetting.Message.OPEN_WINDOW + ": " + myObject.getCls() + " #" + myObject.getId() + LS;
        }

        resultText.setText(resultMsg);
    }

    private void setElement() {
        final int index = Integer.parseInt(indexText.getText());
        final ObjectFrame objectFrame = (ObjectFrame) parent.objList.get(objectChoice.getSelectedIndex());
        array.setElement(index, (T) objectFrame.getMyObject().getObj());

        resultMsg += FrameSetting.Message.SET_ELEMENT + ": index " + index + LS;
        resultText.setText(resultMsg);
    }
}
