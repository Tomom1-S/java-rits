package ch08.ex12;

public class FailureSample {
    @TestCase(params = 1, expected = 2)
    @TestCase(params = -3, expected = -1)
    public static int addOne(final int x) {
        // 例外のテストのため、入力は０より大きい値に制限
        if (x < 0) {
            throw new IllegalArgumentException("x should be greater than 0");
        }

        return x + 2;
    }
}
