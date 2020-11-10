## 練習問題3.2

`ReentrantLock` を使用する場合には、次のイデオムでロックとアンロックをする必要があります。

```java
myLock();
try {
    何らかの処理
} finally {
    myLock.unLock();
}
```

次のように呼び出すことができる `withLock` メソッドを提供しなさい。

```java
withLock(myLock, () -> { 何らかの処理 })
```
