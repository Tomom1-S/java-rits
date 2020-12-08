## 練習問題6.9

フィボナッチ (*Fibonacci*) 数の計算を並列化するために `parallelPrefix` メソッドを使用することができます。
*n* 番目のフィボナッチ数は、*F = (1, 1; 1, 0)* とした場合の、*F^n* を計算した結果の行列の左上の値です。
2×2の行列で配列を埋めなさい。
乗算のメソッドを持つ `Matrix` クラスを定義し、行列の配列を作成するために `parallelSetAll` を使用し、
行列の乗算をするために `parallelPrefix` を使用しなさい。