import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMenu extends JDialog implements ActionListener {
    private JComponent parent;

    private GridBagLayout layout = new GridBagLayout();
    private int gridX = Settings.Menu.Grid.DEFAULT_POSITION;
    private int gridY = Settings.Menu.Grid.DEFAULT_POSITION;

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

    MyMenu(JComponent parent) {
        this.parent = parent;

        Settings.Menu.COLORS.forEach(c -> {
            COLOR_NAMES.add(Settings.Menu.getColorName(c));
        });
        init();
    }

    void init() {
        setTitle(Settings.Menu.TITLE);
        setSize(Settings.Menu.SIZE);

        Container c = getContentPane();
        initAppearance(c);
        setResizable(false);

        initLastValue();
    }

    /**
     * ウィンドウの見た目を設定
     */
    private void initAppearance(Container c) {
        setLayout(layout);

        initCombobox();
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
     * ウィンドウの見た目を設定
     */
    private void initCombobox() {
        gridX = Settings.Menu.Grid.DEFAULT_POSITION;
        gridY = Settings.Menu.Grid.DEFAULT_POSITION;

        // Font
        putComponent(Label.font, gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
        putComponent(InputFields.font, ++gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.WEST);
        List<Font> fontList = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
        ArrayList<String> fonts = new ArrayList<>(fontList.size());
        fontList.forEach(f -> {
            fonts.add(f.getFontName());
        });
        putComboBoxItems(InputFields.font, fonts);

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
        putComboBoxItems(InputFields.fontColor, COLOR_NAMES);

        // Background color
        gridX = Settings.Menu.Grid.DEFAULT_POSITION;
        putComponent(Label.bgColor, gridX, ++gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.EAST);
        putComponent(InputFields.bgColor, ++gridX, gridY,
                Settings.Menu.Grid.DEFAULT_WIDTH, Settings.Menu.Grid.DEFAULT_HEIGHT, GridBagConstraints.WEST);
        putComboBoxItems(InputFields.bgColor, COLOR_NAMES);
    }

    /**
     * ウィンドウを開いた時点の値を保存
     */
    void initLastValue() {
        Font font = parent.getFont();
        LastValue.FONT = font.getFontName();
        LastValue.FONT_SIZE = font.getSize();
//        LastValue.FONT_COLOR = font.ge
    }

    /**
     * コンポーネントを配置
     */
    private void putComponent(Component component, int x, int y, int width, int height, int alignment) {
        GridBagConstraints constraints = new GridBagConstraints();
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
    private <T> void putComboBoxItems(JComboBox comboBox, List<T> list) {
        list.forEach(i -> {
            comboBox.addItem(i);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Button.CANCEL) {
            System.out.println("CANCEL!");
            dispose();
        } else if (e.getSource() == Button.OK) {
            System.out.println("OK!");
            dispose();
        }
    }
}
