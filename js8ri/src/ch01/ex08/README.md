## 練習問題1.8

ラムダ式が次のような拡張 for ループ内の値をキャプチャした場合には、どうなりますか。

```java
String[] names = { "Peter", "Paul", "Mary" };
List<Runnable> runners = new ArrayList<>();
for (String name : names)
    runners.add(() -> System.out.println(name));
```

これは、正当なコードでしょうか。
各ラムダ式は異なる値をキャプチャするのでしょうか。
それとも、すべてのラムダ式が最後の値をキャプチャするのでしょうか。
従来の、`for (int i = 0; i < names.length; i++)` ループを使用すると、どうなるでしょうか。

### 回答

異なる値をキャプチャする。
出力結果は以下の通り。

```
Peter
Paul
Mary
```

また、従来の for ループについても同様に、異なる値をキャプチャする。

