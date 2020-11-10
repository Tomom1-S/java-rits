## 練習問題4.5

次のメソッドを書きなさい。

```java
public static <T, R> ObservableValue<R> observe(
    Function<T, R> f, ObservableValue<T> t)
public static <T, U, R> ObservableValue<R> observe(
    BiFunction<T, U, R> f, ObservableValue<T> t,
    ObservableValue<U> u)
```

このメソッドは、`ObservableValue` を返し、その `ObservableValue` の `getValue` メソッドは、ラムダ式の値を返します。
そして、その `ObservableValue` の `InvalidationListerner` と `ChangeListener` が、
入力のどれかが無効あるいは変更になったときに呼び出されるようにしなさい。
たとえば、次の通りです。

```java
larger.disableProperty().bind(observe(
    t -> t.doubleValue() >= 100, gauge.widthProperty()));
```
