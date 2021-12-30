## 練習問題14.10

第14章の追加問題として練習問題10を解いてもらいます。
練習問題は、以下のリポジトリの `ThreadPool.java` と `ThreadPoolTest.java` の二つのソースファイルです。

https://github.com/YoshikiShibata/jpltest/tree/master/jpl/ch14/ex10

ファイル内の `package` 宣言を `jpl.ch14.ex10` から `ch14.ex10` へ修正して、各人の `jpl/src/ch14/ex10` と `jpl/test/ch14/ex10` ディレクトリの下に、`ThreadPool.java` と `ThreadPoolTest.java` を配置してださい。

練習問題の内容は以下の通りです。
* `ThreadPoolTest.java` は完成したテストコードであり修正しない
* `ThreadPoolTest.java` 内のテストがすべて合格するように `ThreadPool.java` を完成させる

### メモ
* queue (List<Runnable>) の同期を取っているか？(add, pull, isEmpty() など)
* private final Runnable END を用意 (run() の中は空っぽ)
* stop の実装がポイント
* run に終了条件を書く (r = END になったら return)
