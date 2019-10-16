package ch05.ex02;

public class BankAccount {
    private long number;    // 口座番号
    private long balance;   // 現在の残高（単位は、セント）
    private Action lastAct;  // 最後に行われた処理
    private History actHistory = new History();

    public class Action {
        private String act;
        private long amount;

        Action(String act, long amount) {
            this.act = act;
            this.amount = amount;
        }

        public String toString() {
            // identify our enclosing account
            return number + ": " + act + " " + amount;
        }
    }

    public class History {
        private int MAX = 10;
        private Node head = null;

        private class Node {
            Action action;
            Node next;

            Node(Action action, Node next) {
                this.action = action;
                this.next = next;
            }
        }

        private Action next(Node node) {
            Node nextNode = node.next;
            if (nextNode == null) {
                return null;
            }
            return nextNode.action;
        }

        private void addAction(Action action) {
            addLast(action);
            if (getLength() > MAX) {
                removeFirst();
            }
        }

        private void addLast(Action action) {
            if (head == null) {
                head = new Node(action, null);
                return;
            }

            if (action.act == "transfer") {
                removelast();
            }

            Node prevNode = getNode(getLength() - 1);
            Node newNode = new Node(action, prevNode.next);
            prevNode.next = newNode;
        }

        private void removeFirst() {
            if (head == null) {
                return;
            }
            head = head.next;
        }

        private void removelast() {
            if (head == null) {
                return;
            }

            Node prevNode = getNode(getLength() - 2);
            prevNode.next = null;
        }

        private int getLength() {
            int length = 0;
            if (head == null) {
                return length;
            }
            length++;
            for (Node node = head; node.next != null; node = node.next){
                length++;
            }
            return length;
        }

        private Node getNode(int index) {
            Node node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }

        public void show() {
            for (int i = 0; i < getLength(); i++){
                System.out.println(getNode(i).action);
            }
        }
    }

    public History history() {
        return actHistory;
    }

    public void deposit(long amount) {
        balance += amount;
        lastAct = new Action("deposit", amount);
        history().addAction(lastAct);
    }

    public void withdraw(long amount) {
        balance -= amount;
        lastAct = new Action("withdraw", amount);
        history().addAction(lastAct);
    }

    public void transfer(BankAccount other, long amount) {
        other.withdraw(amount);
        deposit(amount);
        lastAct = this.new Action("transfer", amount);
        other.lastAct = other.new Action("transfer", amount);
        history().addAction(lastAct);
    }
}
