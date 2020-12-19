package ch03.ex12;

public class SortHarness extends SortObject {
    // 受け取る値を comparable にする
    // 柴田さん：comparable にできない場合は、比較用インタフェースを用意するのが一般的

    @Override
    protected void doSort() {
        for (int i = 0; i < getDataLength(); i++) {
            for (int j = i + 1; j < getDataLength(); j++){
                if (compare(i, j) > 0) {
                    swap(i, j);
                }
            }
        }
    }
}
