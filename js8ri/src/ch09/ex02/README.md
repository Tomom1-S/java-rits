## 練習問題9.2

練習問題1を改善して、`in.close()` や `out.close()` によりスローされる例外を抑制された例外として、
元々の例外へ追加するようにしなさい。

### コメント

`Scanner` と `PrintWriter` は `close` で例外をスローしないため、
代わりに `BufferedReader`, `BufferedWriter` で実装した。
