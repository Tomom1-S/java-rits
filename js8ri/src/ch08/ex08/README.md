## 練習問題8.8

`CheckedQueue` クラスの利点を示すプログラムを書きなさい。

### 結果

`main` を呼び出すと、以下のような出力があった。

```
Exception in thread "main" java.lang.ClassCastException: Attempt to insert class java.lang.String element into collection with element type interface java.nio.file.Path
	at java.base/java.util.Collections$CheckedCollection.typeCheck(Collections.java:3038)
	at java.base/java.util.Collections$CheckedCollection.add(Collections.java:3081)
	at ch08.ex08.CheckedQueueExample.addElements(CheckedQueueExample.java:20)
	at ch08.ex08.CheckedQueueExample.main(CheckedQueueExample.java:15)
```

つまり、`CheckedQueue` に誤った型の要素を追加しようとした場合には `ClassCastException` となるが、
通常の `Queue` の場合は例外が発生しない。
