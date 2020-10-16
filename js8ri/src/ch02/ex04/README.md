## 練習問題2.4

配列 `int[] values = {1, 4, 9, 16}` があるとします。
`Stream.of(values)` は、何になるでしょうか。
代わりに、`int` のストリームをどうやって取得できるでしょうか。

### 解答

`Stream.of(values)` は、`int[]` のストリームを返す。

`int` のストリームは、`IntStream` を使う。
https://docs.oracle.com/javase/jp/8/docs/api/java/util/stream/IntStream.html
