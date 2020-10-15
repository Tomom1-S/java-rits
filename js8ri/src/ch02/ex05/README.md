## 練習問題2.5

`Stream.iterate` を使用して乱数の無限ストリームを生成しなさい。
このとき、`Math.random` を呼び出すのではなく、線形合同生成機 (linear congruential generator) を直接実装すること。
そのような生成器では、x_0 = seed で始めて、a、c、m の適切な値に対して、x_(n+1) = (ax_n + c) % m を生成します。
a = 25214903917、c = 11、m = 2^48 を試してみなさい。
