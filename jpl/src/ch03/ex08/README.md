## 練習問題3.8

`Vehicle` と `PassengerVehicle` を `Cloneable` 型にしなさい。
複製に関して、4つの選択肢のどれを選択すべきでしょうか。
`Object.clone` による単純なコピーは、それらのクラスの `clone` メソッドとしては正しいですか。

### 回答

* `Vehicle`: 条件付きで `clone` をサポート（p.88の2つ目）
* `PassengerVehicle`:  `clone` をサポート（p.88の1つ目）

いずれのオブジェクトについても、単純なコピーは `clone` メソッドとして正しくない。

#### 理由

単純なコピーをした場合、コピー先とコピー元で同じデータを指してしまうため。
