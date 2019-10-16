## 練習問題4.1

86頁の練習問題3.6の解答を、抽象クラスではなく、`EnergySource` のためのインタフェースを使用して書き直しなさい。

---

### 練習問題3.6

`Vehicle` を変更して、コンストラクタで `Vehicle` と関連付けされる `EnergySource` オブジェクトの参照を持つようにしなさい。
`EnergySource` クラスは `abstract` クラスでなければなりません。
なぜならば、`GadTank` オブジェクトの満タンの測定の方法は、 `Battery` オブジェクトとは異なるでしょう。
`EnergySource` に `abstract` の `empty` メソッドを入れて、`GasTank` と `Battery` クラスでそのメソッドを実装しなさい。
動力源が空でないことを保証する `start` メソッドを `Vehicle` に追加しなさい。
