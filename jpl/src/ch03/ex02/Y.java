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
        // 柴田さん：p.72のステップ1~7は表示できるはず
        // 6桁の16進数形式で表示すると分かりやすい
        System.out.printf("Y mask is %x. %n", yMask);
    }

    public static void main(String[] args) {
        Y y = new Y();
        y.printMask();
    }
}
