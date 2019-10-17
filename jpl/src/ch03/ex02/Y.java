package ch03.ex02;

public class Y extends X {
    protected int yMask = 0xff00;

    public Y() {
        printMask();
        fullMask |= yMask;
    }

    @Override
    public void printMask() {
        super.printMask();
        System.out.printf("Y mask is %x. %n", yMask);
    }

    public static void main(String[] args) {
        Y y = new Y();
        y.printMask();
    }
}
