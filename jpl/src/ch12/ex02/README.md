## 練習問題12.2

次の状態は、どのような方法でプログラマに知らせるべきかを決めなさい。

1. `PassengerVehicle` オブジェクトの定員を負の値に設定しようとした。
1. オブジェクトがその初期値を設定するのに使用する設定ファイルに、文法エラーが見つかった。
1. プログラマが指定した単語を文字列の配列から検索するメソッドが、その単語を発見できない。
1. open メソッドへ指定されたファイルが存在しない。
1. open メソッドへ指定されたファイルは存在するが、セキュリティにより使用できない。
1. リモートのサーバプロセスにネットワークコネクションを確立しようとするが、リモートのマシンと接続できない。
1. リモートのサーバプロセスとのやり取りの最中に、ネットワークコネクションが切れる。

### 解答

// 柴田さんコメント
// どのようなケースを想定して考えたかによって、適切な解答は異なる

1. `java.lang.IllegalArgumentException` をスローして、定員は0以上の値ではないといけないことを示す。
1. `java.io.IOException` をスローする。  
// 柴田さんコメント  
// Parsing Error （try-catch すべきエラー）を出すのが適切
1. `ObjectNotFoundException` を作成し、それをスローする。  
// 柴田さんコメント  
// 見つからないと毎回例外をスローするのは適切でない
1. `java.io.FileNotFoundException: [ファイル名] (そのようなファイルやディレクトリはありません)` をスローする。
1. `java.nio.file.AccessDeniedException: [ファイル名] (許可がありません)` をスローする。
1. `java.net.ConnectException: 接続が拒否されました` をスローする。
1. `java.net.ConnectException` をスローする。  
ネットワークの切断を知らせるエラーがある？  
// 柴田さんコメント  
// 例えば read/write （低レベルなメソッド）を想定すると、read/write の度に try-catch を書くのは煩雑
// もっと上のレベル（何らかの操作をする etc）で try-catch するのがよい
