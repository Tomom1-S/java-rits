## 練習問題3.4

名前に `Filter` を含む関数型インタフェースが、Java API にはいくつありますか。
そのうちのどれが、`Predicate<T>` よりも付加価値がありますか。

### 解答
以下の8つが関数型インタフェースである。
* [`FileFilter`](https://docs.oracle.com/javase/jp/11/docs/api/java.base/java/io/FileFilter.html)
* [`FilenameFilter`](https://docs.oracle.com/javase/jp/11/docs/api/java.base/java/io/FilenameFilter.html)
* [`DirectoryStream.Filter`](https://docs.oracle.com/javase/jp/8/docs/api/java/nio/file/DirectoryStream.Filter.html)
* [`Filter`](https://docs.oracle.com/javase/jp/8/docs/api/java/util/logging/Filter.html)
* [`ServiceRegistry.Filter`](https://docs.oracle.com/javase/jp/8/docs/api/javax/imageio/spi/ServiceRegistry.Filter.html)
* [`NotificationFilter`](https://docs.oracle.com/javase/jp/8/docs/api/javax/management/NotificationFilter.html)
* [`EventFilter`](https://docs.oracle.com/javase/jp/8/docs/api/javax/xml/stream/EventFilter.html)
* [`StreamFilter`](https://docs.oracle.com/javase/jp/8/docs/api/javax/xml/stream/StreamFilter.html)

`Predicate<T>` よりも付加価値があるのは以下。
* `FilenameFilter`: 引数を2つ取れる
* `DirectoryStream.Filter`: 例外をスローできる
