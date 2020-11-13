## 練習問題3.22

`CompletableFuture` に対する `flatMap` 操作は存在しますか。
存在するなら、それは何ですか。

### 解答

[thenCompose](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html#thenCompose-java.util.function.Function-) 
が `flatMap` 操作である。
