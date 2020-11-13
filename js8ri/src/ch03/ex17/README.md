## 練習問題3.17

`first` と `second` を並列に実行し、どちらかのメソッドが例外をスローしたら `handler` を呼び出す 
`doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler)` を実装しなさい。
