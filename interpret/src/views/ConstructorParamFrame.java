package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Parameter;
import java.util.List;

public class ParamFrame extends JFrame implements ActionListener {
    private final MainFrame parent;
    private final String name;
    private final Parameter[] params;
    private final int paramNum;
    private List<String> values;
    private final List<String> defaultValues;

    private BorderLayout layout = new BorderLayout();

    private DefaultTableModel tableModel;

    private JButton btnOk;
    private JButton btnClear;
    private JButton btnCancel;

    public ParamFrame(MainFrame parent, String name, Parameter[] params, List<String> values) {
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
                values.set(i, (String) tableModel.getValueAt(i, FrameSetting.ParamTable.VALUE_COLUMN));
            }
            parent.setValues(values);
            dispose();
        }
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
     * Value を初期化
     */
    private void initValues() {
        int i = 0;
        for (String value : defaultValues) {
            tableModel.setValueAt(value, i++, FrameSetting.ParamTable.VALUE_COLUMN);
        }
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
