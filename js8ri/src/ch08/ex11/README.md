## 練習問題8.11

パスワードで保護されたウェブページの内容を取得するプログラムを書きなさい。
`URLConnection connection = url.openConnection();` を呼び出しなさい。
文字列 `username:password` を生成して、それを Base64 でエンコードしなさい。
それから、`connection.setRequestProperty("Authorization", "Basic " + encoded string)` を呼び出し、
次に `connection.connect()` と `connection.getInputStream()` を呼び出しなさい。
