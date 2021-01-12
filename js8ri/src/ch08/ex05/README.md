## 練習問題8.5

第2章の初めで、リスト内の長い単語を `words.stream().filter(w -> w.length() > 12.count()` で数えました。
ラムダ式を用いて、ストリームを使用しないで、同じことを行いなさい。
長いリストに対してはどちらの操作が速いですか。

### 解答

いずれの場合もラムダ式の方が速かった。

* Alice’s Adventures in Wonderland
https://www.gutenberg.org/files/11/11-h/11-h.htm

  * 全文(26,441 words)
    * streamCount's Time: 790264 msec
    * lambdaCount's Time: 45860 msec

* War and Peace
http://www.gutenberg.org/ebooks/2600

  * 全文(566,316 words)
    * streamCount's Time: 1881213 msec
    * parallelCount's Time: 735682 msec
