import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MyComponentListener implements ComponentListener {
    MyFrame parent;

    MyComponentListener(MyFrame parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Component c = e.getComponent();
        Dimension size = c.getSize();
        parent.setWidth(size.width);
        parent.setHeight(size.height);
        parent.setFontSize(calculateFontSize(size));
        c.repaint();
    }

    private int calculateFontSize(Dimension size) {
        int shortSide = size.width < size.height ? size.width : size.height;
        return shortSide / 5;
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
