package ch04.ex02;

import ch03.ex11.SortMetrics;

interface SortObject {
    /**
     * 全ソートするために呼び出される
     */
    SortMetrics sort(Object[] data);

    SortMetrics getMetrics();

    /**
     * 拡張したクラスが要素の数を知るため
     */
    int getDataLength();

    /**
     * 拡張したクラスが要素を調べるため
     */
    Object probe(int i);

    /**
     * 拡張したクラスが要素を比較するためs
     */
    int compare(int i, int j);

    /**
     * 拡張したクラスが要素を交換するため
     */
    void swap(int i, int j);

    /**
     * 拡張したクラスが実装する --  ソートするのに使用される
     */
    void doSort();
}
