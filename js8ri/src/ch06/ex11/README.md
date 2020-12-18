## 練習問題6.11

次のメソッドを作成しなさい。

```java
public static <T> CompletableFuture<T> repeat(
    Supplier<T> action, Predicate<T> until)
```

このメソッドは、`until` 関数が受け入れる値を生成するまで、`action` を非同期に繰り返します。
`until` 関数も非同期に実行されるべきです。
コンソールから `java.net.PasswordAuthentication` を読み込む関数、および、
1秒間スリープすることで正当性検査をシミュレートし、
パスワードが"`secret`"であるかを検査する関数を用いてテストしなさい。
ヒント：再帰を使用します。

### 解答

```
User Name: Suzuki
Password: password
User Name: Tomomi
Password: secret
Welcome, Tomomi!
```
