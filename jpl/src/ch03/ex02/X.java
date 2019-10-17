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
        System.out.printf("X mask is %x. %n", xMask);
        System.out.printf("Full mask is %x. %n", fullMask);
    }
}
