package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ObjectFieldFrame<E> extends JFrame implements ActionListener {
    private final ObjectFrame parent;
    private final String name;
    private final Field field;
    private E value;
    private final E defaultValue;

    private BorderLayout layout = new BorderLayout();

    private DefaultTableModel tableModel;

    private JButton btnOk;
    private JButton btnClear;
    private JButton btnCancel;

    public ObjectFieldFrame(ObjectFrame parent, String name, Field field, E value) {
        this.parent = parent;
        this.name = name;
        this.field = field;
        this.value = value;
        this.defaultValue = this.value;

        init();
        initBounds();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClear) {
            value = defaultValue;
            initValues();
            parent.setFieldValue(value);
        } else if (e.getSource() == btnCancel) {
            parent.setFieldValue(null);
            dispose();
        } else if (e.getSource() == btnOk) {
            value = (E) tableModel.getValueAt(0, FrameSetting.ParamTable.VALUE_COLUMN);
            parent.setFieldValue(value);
            dispose();
        }
    }

    void init() {
        setTitle(FrameSetting.FRAME_FIELD + ": " + name);
        initBounds();

        Container c = getContentPane();
        initAppearance(c);

        setResizable(true);
    }

    /**
     * ウィンドウの見た目を設定
     */
    private void initAppearance(Container c) {
        setLayout(layout);

        initTable(c);
        initButtons();
    }

    /**
     * ウィンドウの位置を設定
     */
    private void initBounds() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //画面全体のサイズ
        int nx = (int) (d.width * 0.5);
        int ny = d.height / 3;
        int x = (int) ((d.width - nx) / 2 * 1.1);
        int y = (int) ((d.height - ny) / 2 * 1.1);

        setLocation(x, y);
        setSize(nx, ny);
    }

    /**
     * ボタンを設定
     */
    private void initButtons() {
        JPanel btnPanel = new JPanel(new FlowLayout());
        add(btnPanel, BorderLayout.SOUTH);

        btnClear = new JButton(FrameSetting.ButtonLabel.CLEAR);
        btnClear.addActionListener(this);
        btnPanel.add(btnClear);

        btnCancel = new JButton(FrameSetting.ButtonLabel.CANCEL);
        btnCancel.addActionListener(this);
        btnPanel.add(btnCancel);

        btnOk = new JButton(FrameSetting.ButtonLabel.OK);
        btnOk.addActionListener(this);
        btnPanel.add(btnOk);
    }

    /**
     * セルの中身を設定
     */
    private Object[][] initData() {
        Object[][] data = new Object[][]{{name, field.getType(), value}};
        return data;
    }

    /**
     * Value を初期化
     */
    private void initValues() {
        tableModel.setValueAt(value, 0, FrameSetting.ParamTable.VALUE_COLUMN);
    }

    /**
     * テーブルを設定
     */
    private void initTable(Container c) {
        tableModel = new DefaultTableModel(initData(), FrameSetting.ParamTable.ITEM_NAME) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Editable only value column
                return column == 2;
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
    }
}
