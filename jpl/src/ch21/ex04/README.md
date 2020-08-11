## 練習問題21.4

`ListIterator` オブジェクトをフィルターするために `ListIterator` を実装した `ShortStrings` の別のバージョンを作成しなさい。
そのクラスは、`ShortStrings` を拡張すべきですか。

### 回答
`ShortStrings` を拡張すべき。
`hasNext`, `next` は流用できるため、拡張によって実装を減らすことができる。
