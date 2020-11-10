## 練習問題3.24

`Pair<T>` に対する `flatMap` メソッドを定義することができますか。
できるとしたら、それは何ですか。
できないとしたら、その理由は何ですか。

### 解答

`Pair<T>` は要素数が2つに固定されているため、`flatMap` を定義することができない。
例えば、下記のようなクラスのペア `Pair<Foo>` に `flatMap` を適用した場合、
要素が4つになってしまい `Pair<T>` にできない。

```java
class Foo() {
    String name;
    String address;
}
``` 
