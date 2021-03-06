## 練習問題22.5

6面サイコロの個数が指定されると、可能性のある合計値ごとの理論的確率を計算できます。
たとえば、2個の6面サイコロでは、合計が7になる確率は、1/6です。
プログラムを作成して、特定の個数の6面サイコロでの合計値の理論的分布を、
1から6までの数を発生する Random を使用して膨大な数のサイコロを「振った」結果と比較しなさい。
どの乱数発生メソッドを使用するかは、問題となりますか。

### 解答
* 使用する乱数発生メソッドによって、精度に差が出る。
しかし、サイコロを振る回数が増えるとその差は小さくなる。

  * 10000回振ったとき
    * Math.random  
    [0.0273, 0.055, 0.083, 0.1115, 0.1411, 0.1691, 0.133, 0.1139, 0.082, 0.0559, 0.0282]
    * seed = 123456789L  
    [0.029, 0.0581, 0.0874, 0.1133, 0.1357, 0.1699, 0.1305, 0.1163, 0.0773, 0.0577, 0.0248]
    * seed = 987654321L  
    [0.0285, 0.0556, 0.0826, 0.1077, 0.1314, 0.1696, 0.1347, 0.1184, 0.0836, 0.0582, 0.0297]

  * 10000000回振ったとき
    * Math.random  
    [0.0276654, 0.055724, 0.0833261, 0.1111215, 0.1390073, 0.1665605, 0.1388317, 0.1109306, 0.0834106, 0.0555602, 0.0278621]
    * seed = 123456789L  
    [0.0277917, 0.0553765, 0.0833832, 0.1111756, 0.1385811, 0.1667008, 0.1389599, 0.1112186, 0.0834385, 0.0555642, 0.0278099]
    * seed = 987654321L  
    [0.0277636, 0.0555946, 0.0834065, 0.1111719, 0.1389041, 0.1667631, 0.1388477, 0.111, 0.0832874, 0.0555056, 0.0277555]
