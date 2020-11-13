## 練習問題3.5

次は、`ColorTransformer` の具体例です。
次のように、画像の周りに枠を追加します。
最初に、62ページの3.3節「関数型インタフェースの選択」の `transform` メソッドを、
`UnaryOperator<Color>` の代わりに `ColorTransformer` で実装しなさい。
それから、画像の周りの10ピクセルの灰色の枠で置き換えるために、その `transform` メソッドを適切なラムダ式で呼び出しなさい。
