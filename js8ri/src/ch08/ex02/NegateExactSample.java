package ch08.ex02;

public class NegateExactSample {
    public static void main(final String[] _args) {
        Math.negateExact(Integer.MIN_VALUE);
        Math.negateExact(Long.MIN_VALUE);

        // 柴田さん
        System.out.println(-2147483648);    // これはOK
//        System.out.println(-(2147483648));  // これはコンパイルエラー
    }
}
