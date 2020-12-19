## 練習問題1.2

HelloWorld のコードを一部変更して、どのようなエラーが発生するか調べてください。

### 結果

+ L1. パッケージの宣言を削除  <br>
→ "" というパッケージとして扱われるが、期待される名前と異なるためエラー
`The declared package "" does not match the expected package "ch01.ex02"`

+ L3. HelloWorld の前の `class` を削除 <br>
→ `public` の後にインターフェースを期待しているため構文エラー <br>
**class expected というエラーでないのはなぜ？**
`Syntax error on token "public", interface expected after this token`

+ L4. main の前の `void` を削除 <br>
→ メソッドの返り値の型がないためエラー <br>
`Return type for the method is missing`

+ L5. `System.out.println()` の中の " " を削除 <br>
→ `Hello`, `world` が変数として扱われるが、定義されていないためエラー
`Hello cannot be resolved to a variable`

+ L5. `System.out.println()` の `System` を削除 <br>
→ `out` が定義されていないためエラー
`out cannot be resolved`

+ L5. `System.out.println()` の `out` を削除 <br>
→ `System` に `println(String)` が定義されていないためエラー
`The method println(String) is undefined for the type System`

+ L5. `System.out.println()` の中の " " を削除 <br>
→ `Hello`, `world` が変数として扱われるが、定義されていないためエラー
`Hello cannot be resolved to a variable`
`world cannot be resolved to a variable`

+ L5. 行の終わりの `;` を削除 <br>
→ BlockStatements を完了するための ; がないため構文エラー
`Syntax error, insert ";" to complete BlockStatements`

+ L6. `}` を削除 <br>
→ ClassBody を完了するための } がないため構文エラー
`Syntax error, insert "}" to complete ClassBody`
