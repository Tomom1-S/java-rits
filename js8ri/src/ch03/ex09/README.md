## 練習問題3.9

指定された順序で、指定されたフィールドを比較するコンパレータを生成する 
`lexicographicComparator(String... fieldNames)` メソッドを書きなさい。
たとえば、`lexicographicComparator("lastname", "firstname")` は、2つのオブジェクトを受け取り、
リフレクションを使用して、`lastname` フィールドの値を取得します。
2つのオブジェクトの `lastname` フィールドが異なれば、その差を返します。
同じであれば、`firstname` フィールドに移ります。
すべてのフィールドが同じであれば、`0` を返します。
