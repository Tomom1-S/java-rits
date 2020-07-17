package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Parameter;
import java.util.List;

public class ObjectParamFrame<E> extends JFrame implements ActionListener {
    private final ObjectFrame parent;
    private final String name;
    private final Parameter[] params;
    private final int paramNum;
    private List<E> values;
    private final List<E> defaultValues;

    private BorderLayout layout = new BorderLayout();

    private DefaultTableModel tableModel;
    private JTable table;
    private final JComboBox cBox = new JComboBox();

    private JButton btnOk;
    private JButton btnClear;
    private JButton btnCancel;

    public ObjectParamFrame(ObjectFrame parent, String name, Parameter[] params, List<E> values) {
        this.parent = parent;
        this.name = name;
        this.params = params;
        this.paramNum = params.length;
        this.values = values;
        this.defaultValues = values;

        init();
        initBounds();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClear) {
            values = defaultValues;
            initValues();
        } else if (e.getSource() == btnCancel) {
            dispose();
        } else if (e.getSource() == btnOk) {
            for (int i = 0; i < paramNum; i++) {
                final String val = (String) tableModel.getValueAt(i, FrameSetting.ParamTable.VALUE_COLUMN);
                final MainFrame mFrame = parent.getMainFrame();

                int idx = findMatchedStringFromList(val, mFrame.getObjectNameList());
                if (idx >= 0) {
                    final ObjectFrame oFrame = (ObjectFrame) mFrame.objList.get(idx);
                    values.set(i, (E) oFrame.getMyObject().getObj());
                    continue;
                }
                idx = findMatchedStringFromList(val, mFrame.getArrayNameList());
                if (idx >= 0) {
                    final ArrayFrame aFrame = (ArrayFrame) mFrame.arrList.get(idx);
                    values.set(i, (E) aFrame.getMyArray().getArray());
                    continue;
                }

                values.set(i, (E) val);
            }
            parent.setMethodValues(values);
            dispose();
        }
    }

    /**
     * tgt が list に入っているか
     */
    private int findMatchedStringFromList(final String tgt, final List<String> list) {
        int i = 0;
        for (final String str: list) {
            if (tgt.equals(str)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    void init() {
        setTitle(FrameSetting.FRAME_PARAMS + ": " + name);
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
     * セルの中身を設定
     */
    private Object[][] initData() {
        Object[][] data = new Object[paramNum][FrameSetting.ParamTable.COLUMNS];
        int i = 0;
        for (Parameter param : params) {
            data[i][0] = param.getName();
            data[i][1] = param.getType();
            data[i][2] = values.get(i++);
        }
        return data;
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

    /**
     * Value を初期化
     */
    private void initValues() {
        int i = 0;
        for (E value : defaultValues) {
            tableModel.setValueAt(value, i++, FrameSetting.ParamTable.VALUE_COLUMN);
        }
    }
}
