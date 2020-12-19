## 練習問題3.19

`Stream<T>` のメソッドである 
`<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)` 
を見てみなさい。
`BiFunction` への最初の型引数で `U` を `? super U` と宣言すべきですか。
その理由は、何ですか。

### 解答

[`<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)`](https://docs.oracle.com/javase/jp/8/docs/api/java/util/stream/Stream.html#reduce-U-java.util.function.BiFunction-java.util.function.BinaryOperator-)

`accumlator` の `apply` の第1引数は、`apply` の返り値が繰り返し渡される。
従って、`? super U` と宣言しても `U` の親クラスが渡されることはないので、`? super U` と宣言すべきではない。 

### 柴田さんコメント

`reduce` の返り値も `U` なので、`? super U` と宣言すべきではない。
