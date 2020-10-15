## 練習問題2.3

`stream` ではなく、`parallelStream` で長い単語を数えた場合の速度の違いを計りなさい。
呼び出しの前後で `System.nanoTime` を呼び出して、差を表示しなさい。
高速なコンピュータを持っているのであれば、(『戦争と平和』(War and Peace) などの) もっと大きなドキュメントで試しなさい。

### 解答

* Alice’s Adventures in Wonderland
https://www.gutenberg.org/files/11/11-h/11-h.htm

  * 第1章(2,141 words)
    * streamCount's Time: 1176080 msec
    * parallelCount's Time: 898359 msec

  * 全文(26,441 words)
    * streamCount's Time: 1123501 msec
    * parallelCount's Time: 2125114 msec

* War and Peace
http://www.gutenberg.org/ebooks/2600

  * 全文(566,316 words)
    * streamCount's Time: 2069705 msec
    * parallelCount's Time: 2541624 msec
