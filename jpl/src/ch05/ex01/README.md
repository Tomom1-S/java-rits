## 練習問題5.1

第4章の `Attr` クラスと `Attributed` クラスについて考えてみてください。
これらは、どちらかがどちらかのネストした型であるべきですか。
そうであれば、どちらがネストした型であることに意味がありますか。

### 解答

`Attributed` が `Attr` のネストした型であるべき。
これにより、`Attr` を拡張した `ColorAttr` でも `Attributed` で定義したメソッドを使用することできる。
