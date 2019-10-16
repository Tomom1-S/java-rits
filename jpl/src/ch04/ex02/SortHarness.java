package ch04.ex02;

import ch03.ex11.SortMetrics;

public class SortHarness implements SortObject {
    Object[] values = {};
    final SortMetrics curMetrics = new SortMetrics();

    public SortMetrics sort(Object[] data) {
        values = data;
        curMetrics.init();
        doSort();
        return getMetrics();
    }

    public SortMetrics getMetrics() {
        return curMetrics.clone();
    }

    public int getDataLength() {
        return values.length;
    }

    public Object probe(int i) {
        curMetrics.probeCnt++;
        return values[i];
    }

    public int compare(int i, int j) {
        curMetrics.compareCnt++;
        Object d1 = values[i];
        Object d2 = values[j];
        if (d1.hashCode() == d2.hashCode()) {
            return 0;
        } else {
            return (d1.hashCode() < d2.hashCode() ? -1 : 1);
        }
    }

    public void swap(int i, int j) {
        curMetrics.swapCnt++;
        Object tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    @Override
    public void doSort() {
        for (int i = 0; i < getDataLength(); i++) {
            for (int j = i + 1; j < getDataLength(); j++){
                if (compare(i, j) > 0) {
                    swap(i, j);
                }
            }
        }
    }
}
