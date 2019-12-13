import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MyMouseListener implements MouseInputListener {
    MyFrame parent;

    MyMouseListener(MyFrame parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.parent.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.parent.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.parent.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        Point ePoint = e.getPoint();
        Component c = e.getComponent();
        Point newWindowLocation = new Point(
                ePoint.x + c.getLocationOnScreen().x,
                ePoint.y + c.getLocationOnScreen().y
        );
        c.setLocation(newWindowLocation);
        c.repaint();
    }
}
