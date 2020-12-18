## 練習問題6.8

みなさんのコンピュータでは、`Arrays.parallelSort` は、配列がどのくらいの大きさであれば 
`Arrays.sort` より速くなりますか。

### 解答

1000~10000の長さで試した結果は、以下のようになった。

| 配列の長さ | `Array.sort` | `Array.parallelsort`
| -------: | -----------: | -------------------:
| 1000     | 0.232 msec   | 0.052 msec
| 2000     | 0.389 msec   | 0.606 msec
| 3000     | 0.211 msec   | 0.177 msec
| 4000     | 0.669 msec   | 0.137 msec
| 5000     | 0.480 msec   | 0.109 msec
| 6000     | 1.107 msec   | 0.243 msec
| 7000     | 0.732 msec   | 0.197 msec
| 8000     | 0.737 msec   | 0.499 msec
| 9000     | 1.573 msec   | 5.374 msec
| 10000    | 0.676 msec   | 2.719 msec

実際の実行結果は以下の通り。

>Array length: 1000  
>Arrays.sort: 0.231662 msec  
>Arrays.parallelSort: 0.051719 msec
>
>Array length: 2000  
>Arrays.sort: 0.388745 msec  
>Arrays.parallelSort: 0.606269 msec
>
>Array length: 3000  
>Arrays.sort: 0.210649 msec  
>Arrays.parallelSort: 0.176792 msec
>
>Array length: 4000  
>Arrays.sort: 0.669292 msec  
>Arrays.parallelSort: 0.137347 msec
>
>Array length: 5000  
>Arrays.sort: 0.480064 msec  
>Arrays.parallelSort: 0.108586 msec
>
>Array length: 6000  
>Arrays.sort: 1.107375 msec  
>Arrays.parallelSort: 0.242733 msec
>
>Array length: 7000  
>Arrays.sort: 0.73183 msec  
>Arrays.parallelSort: 0.196592 msec
>
>Array length: 8000  
>Arrays.sort: 0.736651 msec  
>Arrays.parallelSort: 0.499175 msec
>
>Array length: 9000  
>Arrays.sort: 1.573497 msec  
>Arrays.parallelSort: 5.373804 msec
>
>Array length: 10000  
>Arrays.sort: 0.675715 msec  
>Arrays.parallelSort: 2.71906 msec

この結果からは、あまり明確な差が読み取れなかった。
