## 練習問題1.6

`Runnable` 内でチェックされる例外を処理しなければならないことが、いつも面倒だと思っていませんか。
チェックされる例外をすべての例外をキャッチし、それをチェックされない例外へ変える `uncheck` メソッドを書きなさい。
たとえば、次のように使用します。

```java
new Thread(uncheck(() ->
    { System.out.println("Zzz"); Thread.sleep(1000); })).
    start();
        // catch (InterruptedException) が必要ありません！
```

ヒント：
どのような例外でもスローできる `run` メソッドを持つ `RunnableEx` インタフェースを定義します。
そして、`public static Runnable uncheck(RunnableEx runner)` を実装します。
`uncheck` 関数内でラムダ式を使用します。
なぜ、`RunnableEx` の代わりに `Callable<Void>` を使用できないのでしょうか。

### 解答

`Callable<Void>` を使用した場合、`V call() throws Exception` を実装する必要がある。
`call` の返り値が必要になるため、`return null;` の1行を追加しないとコンパイルエラーになる。
