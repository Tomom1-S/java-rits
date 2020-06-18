import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyWindowMouseListener implements MouseMotionListener {
    private final MyWindow window;
    private Point mPoint;

    MyWindowMouseListener(final MyWindow window) {
        super();
        this.window = window;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        mPoint = e.getLocationOnScreen();
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        Point p = window.getLocation();
        p.x += e.getXOnScreen() - mPoint.x;
        p.y += e.getYOnScreen() - mPoint.y;
        mPoint.setLocation(e.getLocationOnScreen());
        window.setLocation(p);
    }
}
