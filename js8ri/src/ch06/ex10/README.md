## 練習問題6.10

ユーザーに URL を問い合わせて、その URL のウェブページを読み込み、すべてのリンクを表示するプログラムを作成しなさい。
各ステップに対して、`CompletableFuture` を使用しなさい。
`get` を呼び出さないこと。
プログラムの処理が終わる前に終了しないようにするために、次の呼び出しを行いなさい。

```java
ForkJoinPool.commonPool().
    awaitQuiescence(10, TimeUnit.SECONDS);
```

### 解答

* `InteractiveLinkListViewer` の実行結果

```
Enter URL: foobar
Enter URL: https://www.google.com
https://www.google.co.jp/imghp?hl=ja&tab=wi
https://maps.google.co.jp/maps?hl=ja&tab=wl
https://play.google.com/?hl=ja&tab=w8
https://www.youtube.com/?gl=JP&tab=w1
https://news.google.com/?tab=wn
https://mail.google.com/mail/?tab=wm
https://drive.google.com/?tab=wo
http://www.google.co.jp/history/optout?hl=ja
https://accounts.google.com/ServiceLogin?hl=ja&passive=true&continue=https://www.google.com/&ec=GAZAAQ
http://www.google.co.jp/intl/ja/services/
https://www.google.com/setprefdomain?prefdom=JP&amp;prev=https://www.google.co.jp/&amp;sig=K_DfRvn-CZRRzZuQ-XGUd4nGlHbP0%3D
```
