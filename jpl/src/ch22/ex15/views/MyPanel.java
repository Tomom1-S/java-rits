package ch22.ex15.views;

import ch22.ex15.models.MathFunction;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPanel extends JPanel {
    final MyCalculator parent;
    static final MathFunction MATH_FUNCTION = new MathFunction();
    final GridBagLayout layout = new GridBagLayout();

    static final class Key {
        static final String OP = "op";
        static final String VAL1 = "val1";
        static final String VAL2 = "val2";
    }

    Map<String, String> values = new HashMap<>() {
        {
            put(Key.OP, "");
            put(Key.VAL1, "");
            put(Key.VAL2, "");
        }
    };

    final static int TEXT_WIDTH = 10;
    final static int TEXT_HEIGHT = 2;
    final static int BTN_WIDTH = 1;
    final static int BTN_HEIGHT = 1;

    final JTextField textField = new JTextField();
    final List<JButton> btnNums = new ArrayList<>() {{
        add(new JButton("7"));
        add(new JButton("8"));
        add(new JButton("9"));
        add(new JButton("4"));
        add(new JButton("5"));
        add(new JButton("6"));
        add(new JButton("1"));
        add(new JButton("2"));
        add(new JButton("3"));
        add(new JButton("0"));
    }};
    final JButton btnDot = new JButton(".");
    final JButton btnEq = new JButton("=");
    final JButton btnClr = new JButton("C");
    final List<JButton> btnOps = new ArrayList<>() {{
        add(new JButton("+"));
        add(new JButton("-"));
        add(new JButton("*"));
        add(new JButton("/"));
        add(new JButton("%"));
    }};
    final List<JButton> btnFns = new ArrayList<>() {{
        add(new JButton("sin(x)"));
        add(new JButton("cos(x)"));
        add(new JButton("tan(x)"));
        add(new JButton("asin(x)"));
        add(new JButton("acos(x)"));
        add(new JButton("atan(x)"));
        add(new JButton("atan2(x,y)"));
        add(new JButton("toRadians(x)"));
        add(new JButton("toDegree(x)"));
        add(new JButton("exp(x)"));
        add(new JButton("sinh(x)"));
        add(new JButton("cosh(x)"));
        add(new JButton("tanh(x)"));
        add(new JButton("pow(x,y)"));
        add(new JButton("log(x)"));
        add(new JButton("log10(x)"));
        add(new JButton("sqrt(x)"));
        add(new JButton("cbrt(x)"));
        add(new JButton("signum(x)"));
        add(new JButton("ceil(x)"));
        add(new JButton("floor(x)"));
        add(new JButton("rint(x)"));
        add(new JButton("round(x)"));
        add(new JButton("abs(x)"));
        add(new JButton("max(x,y)"));
        add(new JButton("min(x,y)"));
        add(new JButton("hypot(x,y)"));
    }};


    MyPanel(MyCalculator parent) {
        this.parent = parent;
        init();
    }

    private void init() {
        setLayout(layout);

        textField.setHorizontalAlignment(SwingConstants.RIGHT); // 入力フィールドを右寄せ
        addComponent(textField, 0, 0, TEXT_WIDTH, TEXT_HEIGHT);

        initButtons();
    }

    private void initButtons() {
        int x = 0;
        int y = TEXT_HEIGHT;

        // Functions
        final int maxX = 5;
        for (final JButton btn : btnFns) {
            if (x > maxX) {
                x = 0;
                y++;
            }

            addComponent(btn, x++, y, BTN_WIDTH, BTN_HEIGHT);
            btn.addActionListener(e -> {
                values.put(Key.VAL1, textField.getText());
                values.put(Key.OP, ((JButton) e.getSource()).getText());

                final JButton src = (JButton) e.getSource();
                if (src.getText().contains("x,y")) {
                    updateTextField("", true);
                    return;
                }

                final double result = MATH_FUNCTION.callMath(
                        values.get(Key.OP),
                        Double.parseDouble(values.get(Key.VAL1)),
                        0
                );
                clearAll();
                updateTextField(new DecimalFormat("##.##").format(result), true);
            });
        }

        // Numbers
        int defaultX = maxX + 1;
        x = defaultX;
        y = TEXT_HEIGHT;
        for (final JButton btn : btnNums) {
            if (x > defaultX + 2) {
                x = defaultX;
                y++;
            }

            if (btn.getText().equals("0")) {
                addComponent(btn, x++, y, BTN_WIDTH + 1, BTN_HEIGHT);
                btn.addActionListener(e -> {
                    if (textField.getText().equals("0")) {
                        return;
                    }
                    final JButton src = (JButton) e.getSource();
                    updateTextField(src.getText(), false);
                });
            } else {
                addComponent(btn, x++, y, BTN_WIDTH, BTN_HEIGHT);
                btn.addActionListener(e -> {
                    final JButton src = (JButton) e.getSource();
                    updateTextField(src.getText(), false);
                });
            }
        }

        // Dot
        addComponent(btnDot, ++x, y, BTN_WIDTH, BTN_HEIGHT);
        btnDot.addActionListener(e -> {
            final String txt = textField.getText();
            if (txt.equals("") || txt.contains(".")) {
                return;
            }
            final JButton src = (JButton) e.getSource();
            updateTextField(src.getText(), false);

        });

        // Equal
        addComponent(btnEq, defaultX, ++y, BTN_WIDTH + 2, BTN_HEIGHT);
        btnEq.addActionListener(e -> {
            values.put(Key.VAL2, textField.getText());

            final double result = MATH_FUNCTION.callMath(
                    values.get(Key.OP),
                    Double.parseDouble(values.get(Key.VAL1)),
                    Double.parseDouble(values.get(Key.VAL2))
            );
            clearAll();
            updateTextField(new DecimalFormat("##.####").format(result), true);
        });

        // Operators
        x++;
        y = TEXT_HEIGHT;
        for (final JButton btn : btnOps) {
            addComponent(btn, x, y++, BTN_WIDTH, BTN_HEIGHT);
            btn.addActionListener(e -> {
                values.put(Key.OP, ((JButton) e.getSource()).getText());
                values.put(Key.VAL1, textField.getText());

                updateTextField("", true);
            });
        }

        // Clear
        btnClr.setForeground(Color.RED);
        addComponent(btnClr, x, y++, BTN_WIDTH, BTN_HEIGHT);
        btnClr.addActionListener(e -> {
            clearAll();
        });
    }

    private void addComponent(final JComponent c, final int x, final int y, final int width, final int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        layout.setConstraints(c, gbc);
        c.setVisible(true);
        add(c);
    }

    private void clearAll() {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            values.put(entry.getKey(), "");
        }
        textField.setText("");
    }

    private void updateTextField(final String str, final boolean reset) {
        if (reset) {
            textField.setText(str);
            return;
        }
        textField.setText(textField.getText() + str);
    }
}
