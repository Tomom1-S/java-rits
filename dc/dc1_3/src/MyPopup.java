import java.awt.*;
import java.util.List;

public class MyPopup extends PopupMenu {
    MyFrame parent;
    String label;

    MyPopup() {super();

    }

    MyPopup(MyFrame parent) {
        super();
        this.parent = parent;
    }

    MyPopup(String label, MyFrame parent) {
        super(label);
        this.parent = parent;
        this.label = label;
    }

    public void addSubMenu(List<String> subLabels) {
        subLabels.forEach(l -> {
            MenuItem item = new MenuItem(l);
            item.addActionListener(parent);
            this.add(item);
        });
    }
}
