package ch08.ex12;

public class SuccessSample {
    @TestCase(params = 1, expected = 2)
    @TestCase(params = 3, expected = 4)
    public static int addOne(final int x) {
        return x + 1;
    }
}
