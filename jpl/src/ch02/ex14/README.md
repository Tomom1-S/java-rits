## 練習問題2.14

`LinkedList` クラスのフィールドを `private` にして、フィールドに対するアクセッサーメソッドを追加しなさい。
どのフィールドが変更を許すメソッドを持ち、どのフィールドがそのようなメソッドを持つべきではないですか。

### 回答

変更を許す：<br>
`data`

変更を許さない：<br>
`next`, `head`, `tail` <br>
→ クラス内で自動的に決定されるため、変更を許すとリストの連結が崩れる可能性がある。