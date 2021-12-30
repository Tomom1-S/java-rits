## 練習問題9.3

複数例外をキャッチする `catch` 節でキャッチした例外を再度スローする場合に、
その処理が書かれているメソッドの `throws` 宣言には、例外の方をどのように宣言しますか。
たとえば、次の例を考えなさい。

```java
public void process() throws ... {
    try {
        ...
    } catch (FileNotFoundException |
             UnknownHostException ex) {
        logger.log(Level.SEVERE, "...", ex);
        throw ex;
    }
}
```

### 解答

```java
public void process() throws FileNotFoundException, UnknownHostException {
    try {
        // Do something
    } catch (FileNotFoundException |
            UnknownHostException ex) {
        logger.log(Level.SEVERE, "...", ex);
        throw ex;
    }
}
```

`FileNotFoundException`, `UnknownHostException` の親クラスが同じなので、ひとつにまとめられる。
    
```java
public void process() throws IOException {
    try {
        // Do something
    } catch (FileNotFoundException |
            UnknownHostException ex) {
        logger.log(Level.SEVERE, "...", ex);
        throw ex;
    }
}
```

### 柴田さんコメント

個々の例外を宣言すると親切だが、実装の詳細を見せることになる。
`IOException` の方が使用者に余計なことを考えさせなくてよい。
