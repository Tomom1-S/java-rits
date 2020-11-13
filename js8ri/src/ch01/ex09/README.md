## 練習問題1.9

`Collection` のサブインタフェースである `Collection2` を作成して、デフォルトメソッドとして
`void forEachIf(Consumer<T> action, Predicate<T> filter)` を追加しなさい。
そのメソッドは、`filter` が `true` を返してきた個々の要素に対して `action` を適用します。
どのような場面で、そのメソッドを活用できるでしょうか。

### 解答

`Collection2` を実装した `List` や `Map` などに相当する `Collection` を実装する必要があるため、あまり実用的ではない。
