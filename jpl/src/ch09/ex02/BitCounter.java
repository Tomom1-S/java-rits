package ch09.ex02;

public class BitCounter {
    public int countOnes(int bits) {
        int num = 0;
        for (int mask = 1; mask != 0; mask = mask << 1) {
            if ((bits & mask) != 0) {
                num++;
            }
        }
        return num;
    }
}
