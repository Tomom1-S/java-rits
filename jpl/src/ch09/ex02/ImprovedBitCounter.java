
package ch09.ex02;

public class ImprovedBitCounter {
    public int countOnes(int bits) {
        bits = (bits & 0x55555555) + (bits >> 1 & 0x55555555);  // 2bitごとに計算
        bits = (bits & 0x33333333) + (bits >> 2 & 0x33333333);  // 4bitごとに計算
        bits = (bits & 0x0f0f0f0f) + (bits >> 4 & 0x0f0f0f0f);  // 8bitごとに計算
        bits = (bits & 0x00ff00ff) + (bits >> 8 & 0x00ff00ff);  // 16bitごとに計算
        return (bits & 0x0000ffff) + (bits >> 16);  // 32bitごとに計算
    }
}
