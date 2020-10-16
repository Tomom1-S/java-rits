## 練習問題2.8

`public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)` メソッドを作成しなさい。
そのメソッドは、ストリームである `first` と `second` から要素を交互に取り出し、
どちらかのストリームから要素がなくなったら停止します。
