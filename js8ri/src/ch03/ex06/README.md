## 練習問題3.6

65ページの3.4節「関数を返す」で見た次のメソッドを完成させなさい。

```java
public static <T> Image transform(
    Image in, BiFunction<Color, T, Color> f, T arg)
```
