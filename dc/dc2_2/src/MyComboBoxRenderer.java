import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
    private JLabel labelItem = new JLabel();

    public MyComboBoxRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(
            final JList list,
            final Object value,
            final int index,
            final boolean isSelected,
            final boolean cellHasFocus) {
        if (index < 0) {
            return this;
        }

        setText(value.toString());

        // set color chip
        BufferedImage image = new BufferedImage(
                Settings.Menu.COLOR_ICON_SIZE.width,
                Settings.Menu.COLOR_ICON_SIZE.height,
                BufferedImage.TYPE_INT_RGB
        );
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(Settings.Menu.COLORS.get(index));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        ImageIcon imageIcon = new ImageIcon(image);
        setIcon(imageIcon);

        return this;
    }
}

