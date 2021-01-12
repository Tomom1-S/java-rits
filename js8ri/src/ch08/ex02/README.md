## 練習問題8.2

`Math.negateExact(n)` が例外をスローすることになる整数 `n` の値は何ですか 
(ヒント：1つの値しか該当しません)。

### 解答

`negateExact(int n)` では `Integer.MIN_VALUE`、
`negateExact(long n)` では `Long.MIN_VALUE` のときに例外となる。