## 練習問題1.1

`Arrays.sort` メソッド内で呼び出されるコンパレータのコードは、`sort` メソッドを呼び出したスレッドで実行されるでしょうか。
それとも、別のスレッドで実行されるでしょうか。

### 解答

`sort` を呼び出したスレッドと同じスレッドで実行される。