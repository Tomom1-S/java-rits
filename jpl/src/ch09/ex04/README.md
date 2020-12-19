## 練習問題9.4

この章で学んだ事柄を生かして、実際にコードを書かないで、次の式のどれが正しくないかを判断しなさい。
そして、正しい式であれば、その型と値が何であるか考えなさい。

`3 << 2L - 1` => int 6  
// 柴田さん：3 << (2L -1)

`(3L << 2) -1` => long 11

`10 < 12 == 6 > 17` => true == false => boolean false

`10 << 12 == 6 >> 17` => boolean false

`13.5e-1 % Float.POSITIVE_INFINITY` => Float 13.5e-1  
// 柴田さん：Double

`Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY` => Float NaN  
// 柴田さん：Double

`Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY` => Double.POSITIVE_INFINITY

`0.0 / -0.0 == -0.0 / 0.0` => boolean false  
// 柴田さん：NaN は自分自身とも等しくない

`Integer.MAX_VALUE + Integer.MIN_VALUE` => int -1

`Long.MAX_VALUE + 5` => long -9223372036854775804

`(i < 15 ? 1.72e3f : 0)`  
// 柴田さん：float 0

`i++ + i++ + --i`
// 柴田さん：int 11

`(short) 5 * (byte) 10` => short 50