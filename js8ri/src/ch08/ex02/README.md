## 練習問題8.2

`Math.negateExact(n)` が例外をスローすることになる整数 `n` の値は何ですか 
(ヒント：1つの値しか該当しません)。

### 解答

`negateExact(int n)` では `Integer.MIN_VALUE`、
`negateExact(long n)` では `Long.MIN_VALUE` のときに例外となる。

### 柴田さん

`-Long.MIN_VALUE` とすると、`Long.MIN_VALUE` と同じ値になる。
2の補数表現で負の数を求めるときは、全てのビットを反転させて1を足す。

`Long.MIN_VALUE` を2進数で表すと、1000...000。
`-Long.MIN_VALUE` を2進数で表した場合も、1000...000。
