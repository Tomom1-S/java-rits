package ch03.ex12;

import ch03.ex11.SortMetrics;

import java.util.Comparator;
import java.util.Objects;

public abstract class SortObject {
    private Object[] values;
    private final SortMetrics curMetrics = new SortMetrics();

    /**
     * 全ソートするために呼び出される
     */
    public final SortMetrics sort(Object[] data) {
        values = data;
        curMetrics.init();
        doSort();
        return getMetrics();
    }

    public final SortMetrics getMetrics() {
        return curMetrics.clone();
    }

    /**
     * 拡張したクラスが要素の数を知るため
     */
    protected final int getDataLength() {
        return values.length;
    }

    /**
     * 拡張したクラスが要素を調べるため
     */
    protected final Object probe(int i) {
        curMetrics.probeCnt++;
        return values[i];
    }

    /**
     * 拡張したクラスが要素を比較するためs
     */
    protected final int compare(int i, int j) {
        curMetrics.compareCnt++;
        Object d1 = values[i];
        Object d2 = values[j];
        if (d1.hashCode() == d2.hashCode()) {
            return 0;
        } else {
            return (d1.hashCode() < d2.hashCode() ? -1 : 1);
        }
    }

    /**
     * 拡張したクラスが要素を交換するため
     */
    protected final void swap(int i, int j) {
        curMetrics.swapCnt++;
        Object tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    /**
     * 拡張したクラスが実装する --  ソートするのに使用される
     */
    protected abstract void doSort();
}
