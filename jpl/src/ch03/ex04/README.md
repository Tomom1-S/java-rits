## 練習問題3.4

もし、あるとしたら、 `Vehicle` と `PassengerVehicle` のどのメソッドを `final` にするのが適切ですか。

### 回答

* `Vehicle`
    * `getX`, `setX` のメソッドすべて
    * `stop`

*  `PassengerVehicle`
    * `getX`, `setX` のメソッドすべて

### 柴田さんコメント
* 乗車人数以外は final でよい

