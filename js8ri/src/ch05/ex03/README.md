## 練習問題5.3

`Predicate<LocalDate>` を受け取り、`TemporalAdjuster` を返す `next` メソッドを実装しなさい。
返された `TemporalAdjuster` は、`next` メソッドに渡された述語を満足する翌日の日付を生成します。
たとえば、次のコードを見てください。

```java
today.with(next(w -> w.getDayOfWeek().getValue() < 6))
```

このコードは、今日より後にあって平日となる最初の日付を返します。
