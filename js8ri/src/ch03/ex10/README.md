## 練習問題3.10

なぜ、次の呼び出しができないのでしょうか。

```java
UnaryOperator<Color> op = Color::brighter;
Image finalImage = 
    transform(image, op.compose(Color::grayscale));
```

`UnaryOperator<T>` の `compose` メソッドの戻り値型を注意深く調べなさい。
なぜ、`transform` メソッドに対しては、適切ではないのでしょうか。
関数合成に関しては、ストラクチャル型 (structural type) とノミナル型 (nominal type) のどちらがより役立つでしょうか。

### 解答

`op.compose` の返り値型は `Function<V, R>` であり、
今回の場合は `Function<Color, javafx.scene.paint.Color>` である。 

下記のようにすれば、エラーにならない。
```java
Image finalImage =
    transform(image, color -> color.brighter().grayscale());
```

関数合成に関しては、ストラクチャル型の方が柔軟である。
