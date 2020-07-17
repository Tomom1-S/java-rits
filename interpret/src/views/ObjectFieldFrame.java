package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class ObjectFieldFrame<E> extends JFrame implements ActionListener {
    private final ObjectFrame parent;
    private final String name;
    private final Field field;
    private E value;
    private final E defaultValue;

    private BorderLayout layout = new BorderLayout();

    private DefaultTableModel tableModel;
    private JTable table;
    private final JComboBox cBox = new JComboBox();

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
            final String val = (String) tableModel.getValueAt(0, FrameSetting.ParamTable.VALUE_COLUMN);
            final MainFrame mFrame = parent.getMainFrame();

            final int objIdx = findMatchedStringFromList(val, mFrame.getObjectNameList());
            final int arrIdx = findMatchedStringFromList(val, mFrame.getArrayNameList());
            if (objIdx >= 0) {
                final ObjectFrame oFrame = (ObjectFrame) mFrame.objList.get(objIdx);
                parent.setFieldValue(oFrame.getMyObject().getObj());
            } else if (arrIdx >= 0) {
                final ArrayFrame aFrame = (ArrayFrame) mFrame.arrList.get(arrIdx);
                parent.setFieldValue(aFrame.getMyArray().getArray());
            } else {
                value = (E) tableModel.getValueAt(0, FrameSetting.ParamTable.VALUE_COLUMN);
                parent.setFieldValue(value);
            }

            dispose();
        }
    }

    /**
     * tgt が list に入っているか
     */
    private int findMatchedStringFromList(final String tgt, final List<String> list) {
        int i = 0;
        for (String str: list) {
            if (tgt.equals(str)) {
                return i;
            }
            i++;
        }
        return -1;
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

        initTable();
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
     * Value コラムを初期化
     */
    private void initValueColumn(final JTable table) {
        cBox.removeAllItems();

        setJComboboxItems(cBox, parent.getMainFrame().getObjectNameList());
        setJComboboxItems(cBox, parent.getMainFrame().getArrayNameList());

        final TableColumn column = table.getColumnModel().getColumn(2);
        column.setCellEditor(new DefaultCellEditor(cBox));
        cBox.setEditable(true); // 生成済オブジェクト・配列以外も設定可能にする
    }

    private void setJComboboxItems(final JComboBox comboBox, final List<String> list) {
        for (final String str : list) {
            comboBox.addItem(str);
        }
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
    private void initTable() {
        tableModel = new DefaultTableModel(initData(), FrameSetting.ParamTable.ITEM_NAME) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Editable only value column
                return column == 2;
            }
        };


        table = new JTable(tableModel);
        initValueColumn(table);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
    }
}
