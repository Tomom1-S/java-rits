## 練習問題2.7

上司が、メソッドとして `public static <T> boolean isFinite(Stream<T> stream)` を作成するように求めています。
それは、よくない考えでしょうか。
まずは作成してから、考えてみなさい。

### 解答

よくない。
`Stream` を閉じることなく、その大きさを調べることができないため。