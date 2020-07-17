import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMenu extends JDialog implements ActionListener {
    private MyPanel parent;

    private GridBagLayout layout = new GridBagLayout();
    private int gridX = Settings.Menu.Grid.DEFAULT_POSITION;
    private int gridY = Settings.Menu.Grid.DEFAULT_POSITION;

    private static final ArrayList<String> FONT_NAMES = new ArrayList<>();
    private static final ArrayList<String> COLOR_NAMES = new ArrayList<>();

    private static class Label {
        private static final JLabel font = new JLabel(Settings.Menu.Label.FONT);
        private static final JLabel fontSize = new JLabel(Settings.Menu.Label.FONT_SIZE);
        private static final JLabel fontColor = new JLabel(Settings.Menu.Label.FONT_COLOR);
        private static final JLabel bgColor = new JLabel(Settings.Menu.Label.BG_COLOR);
    }

    private static class InputFields {
        private static final JComboBox font = new JComboBox();
        private static final JTextField fontSize = new JTextField(Settings.Menu.TEXT_FIELD_WIDTH);
        private static final JComboBox fontColor = new JComboBox();
        private static final JComboBox bgColor = new JComboBox();
    }

    private static class Button {
        private static final JButton OK = new JButton(Settings.Menu.Label.OK_BUTTON);
        private static final JButton CANCEL = new JButton(Settings.Menu.Label.CANCEL_BUTTON);
    }

    private static class LastValue {
        private static String FONT;
        private static int FONT_SIZE;
        private static Color FONT_COLOR;
        private static Color BG_COLOR;
    }

    MyMenu(final MyPanel parent) {
        this.parent = parent;

        init();
    }

    void init() {
        setTitle(Settings.Menu.TITLE);
        setSize(Settings.Menu.SIZE);

        final Container c = getContentPane();
        initAppearance(c);
        setResizable(false);
    }

    /**
     * ウィンドウの見た目を設定
     */
    private void initAppearance(final Container c) {
        setLayout(layout);

        initComboBoxItems();
        initInputFields();
        initButtons();
    }

    /**
     * ボタンを設定
     */
    private void initButtons() {
        Button.OK.addActionListener(this);
        putComponent(Button.OK, gridX, ++gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);

        Button.CANCEL.addActionListener(this);
        putComponent(Button.CANCEL, gridX, ++gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
    }

    /**
     * JComboBox の項目を設定
     */
    private void initComboBoxItems() {
        final List<Font> fontList = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
        FONT_NAMES.clear();
        fontList.forEach(f -> {
            FONT_NAMES.add(f.getFontName());
        });
        putComboBoxItems(InputFields.font, FONT_NAMES);

        initComboBoxRenderer(InputFields.fontColor);
        initComboBoxRenderer(InputFields.bgColor);
        COLOR_NAMES.clear();
        Settings.Menu.COLORS.forEach(c -> {
            COLOR_NAMES.add(Utils.colorToName(c));
        });
        putComboBoxItems(InputFields.fontColor, COLOR_NAMES);
        putComboBoxItems(InputFields.bgColor, COLOR_NAMES);

        InputFields.fontColor.setPreferredSize(new Dimension(
                Settings.Menu.COLOR_FIELD_WIDTH,
                InputFields.fontColor.getPreferredSize().height)
        );
        InputFields.bgColor.setPreferredSize(new Dimension(
                Settings.Menu.COLOR_FIELD_WIDTH,
                InputFields.bgColor.getPreferredSize().height)
        );
    }

    /**
     * JComboBox の renderer を設定
     */
    private void initComboBoxRenderer(JComboBox comboBox) {
        comboBox.setEditable(true);

        MyComboBoxRenderer renderer = new MyComboBoxRenderer();
        comboBox.setRenderer(renderer);
    }

    /**
     * ウィンドウの見た目を設定
     */
    private void initInputFields() {
        gridX = Settings.Menu.Grid.DEFAULT_POSITION;
        gridY = Settings.Menu.Grid.DEFAULT_POSITION;

        // Font
        putComponent(Label.font, gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
        putComponent(InputFields.font, ++gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.WEST);

        // Font size
        gridX = Settings.Menu.Grid.DEFAULT_POSITION;
        putComponent(Label.fontSize, gridX, ++gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
        putComponent(InputFields.fontSize, ++gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.WEST);

        // Font color
        gridX = Settings.Menu.Grid.DEFAULT_POSITION;
        putComponent(Label.fontColor, gridX, ++gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
        putComponent(InputFields.fontColor, ++gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.WEST);

        // Background color
        gridX = Settings.Menu.Grid.DEFAULT_POSITION;
        putComponent(Label.bgColor, gridX, ++gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
        putComponent(InputFields.bgColor, ++gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.WEST);
    }

    /**
     * ウィンドウを開いた時点の値を保存
     */
    void initLastValue() {
        final Font font = parent.getClockFont();
        LastValue.FONT = font.getFontName();
        LastValue.FONT_SIZE = font.getSize();
        LastValue.FONT_COLOR = parent.getFontColor();
        LastValue.BG_COLOR = parent.getBgColor();

        InputFields.font.setSelectedItem(LastValue.FONT);
        InputFields.fontSize.setText(String.valueOf(LastValue.FONT_SIZE));
        InputFields.fontColor.setSelectedItem(Utils.colorToName(LastValue.FONT_COLOR));
        InputFields.bgColor.setSelectedItem(Utils.colorToName(LastValue.BG_COLOR));
    }

    /**
     * コンポーネントを配置
     */
    private void putComponent(final Component component, final int x, final int y,
                              final int width, final int height, final int alignment) {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;    //位置x
        constraints.gridy = y;    //位置y
        constraints.gridwidth = width;    //コンポーネントの表示領域のセル数 横
        constraints.gridheight = height;    //コンポーネントの表示領域のセル数 縦
        constraints.anchor = alignment;
        layout.setConstraints(component, constraints);
        add(component);
    }

    /**
     * コンボボックスの項目を設定
     */
    private <T> void putComboBoxItems(final JComboBox comboBox, final List<T> list) {
        comboBox.removeAllItems();
        list.forEach(i -> {
            comboBox.addItem(i);
        });
    }

    /**
     * String を int に変換
     */
    private int convertStringToInt(final String str, final int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return defaultVal;
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == Button.CANCEL) {
            InputFields.font.setSelectedIndex(FONT_NAMES.indexOf(LastValue.FONT));
            InputFields.fontSize.setText(String.valueOf(LastValue.FONT_SIZE));
            InputFields.fontColor.setSelectedIndex(Settings.Menu.COLORS.indexOf(LastValue.FONT_COLOR));
            InputFields.bgColor.setSelectedIndex(Settings.Menu.COLORS.indexOf(LastValue.BG_COLOR));

            dispose();
        } else if (e.getSource() == Button.OK) {
            int fontSize = convertStringToInt(InputFields.fontSize.getText(), LastValue.FONT_SIZE);
            if (fontSize <= 0) {
                fontSize = LastValue.FONT_SIZE;
            }
            parent.setClockFont(new Font((String) InputFields.font.getSelectedItem(), Font.PLAIN, fontSize));
            parent.updatePanelSize(fontSize);
            parent.setFontColor(Settings.Menu.COLORS.get(InputFields.fontColor.getSelectedIndex()));
            parent.setBgColor(Settings.Menu.COLORS.get(InputFields.bgColor.getSelectedIndex()));
            parent.repaint();

            dispose();
        }
    }
}
