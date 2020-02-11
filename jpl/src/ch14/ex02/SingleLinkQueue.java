package ch14.ex02;

// ch11 (p.216)
public class SingleLinkQueue<E> {
    protected Cell<E> head;
    protected Cell<E> tail;

    public void add(E item) {
        Cell<E> cell = new Cell<E>(item);
        if (tail == null) {
            head = tail = cell;
        } else {
            tail.setNext(cell);
            tail = cell;
        }
    }

    public E remove() {
        if (head == null) {
            return null;
        }
        Cell<E> cell = head;
        head = head.getNext();
        if (head == null) {
            tail = null;    // 空のキュー
        }
        return cell.getElement();
    }

    public int size() {
        if (head == null) {
            return 0;
        }

        Cell<E> cell = head;
        int size = 1;
        while (cell != tail) {
            size++;
            cell = cell.getNext();
        }

        return size;
    }
}
