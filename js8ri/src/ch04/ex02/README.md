## 練習問題4.2

チャートやテーブルといった多くの JavaFX プロパティを持つクラスを考えなさい。
特定のアプリケーションでは、ほとんどのプロパティには決してリスナーが登録されない可能性が高いです。
したがって、プロパティごとにプロパティオブジェクトを持つことは無駄です。
プロパティ値を保存するために最初に普通のフィールドを使用して、
初めて `xxxProperty()` メソッドが呼び出されたときにだけプロパティオブジェクトを使用するように、
要求に応じてプロパティを構築する方法を示しなさい。
