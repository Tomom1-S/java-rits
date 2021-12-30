package ch03.ex02;

public class X {
    protected int xMask = 0x00ff;
    protected int fullMask;

    public X() {
        printMask();
        fullMask = xMask;
        printMask();
    }

    public int mask(int orig) {
        return (orig & fullMask);
    }

    public void printMask() {
        // 柴田さん：p.72のステップ1~7は表示できるはず（初めから xMask, yMask, fullMask の3つを出す）
        // → print 用のメソッドを Y.java に書く、X.java には abstract メソッドとして書くだけ
        // [Point] コンストラクタの中でオーバーライドされたメソッドを呼び出すことができる
        // 6桁の16進数形式で表示すると分かりやすい
        System.out.printf("X mask is %x. %n", xMask);
        System.out.printf("Full mask is %x. %n", fullMask);
    }
}
