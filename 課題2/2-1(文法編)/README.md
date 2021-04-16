**①問題2-1 Javaの演習問題：文法編**

解答方法
入力値は問題で定められた規則に従い、正しく入力されるものとし、入力ミスなどは考慮しなくてよい。

1.（起動パラメータ）Kadai1.java
起動パラメータに指定された引数をすべて表示するプログラムを作成しなさい

・最初に、引数の個数を出力する。
・引数は一つ以上指定されるものとする。

実行例：
java Man ABC あいうえお 12345

＜出力例＞
引数は3個指定されています。
1番目の引数は「ABC」です。
2番目の引数は「あいうえお」です。
3番目の引数は「12345」です。


1.（例外）Kadai2.java
プログラム中で、以下の例外を発生させる処理を記述し、その例外の詳細情報は出力しなさい。

・Null参照によるNullPointerException
・配列の範囲外参照によるArrayIndexOutOfBoundsException
・互換性の無いオブジェクト型の変換によるClassCastException
ただし、プログラムは例外による異常終了はせず、正常に終了すること。

＜出力例＞
java.lang.NullPointerException
    at Main.main(Main.java:8)
java.lang.ArrayIndexOutOfBoundsException: 0
    at Main.main(Main.java:14)
java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
    at Main.main(Main.java:21)
 

1.（static、ジェネリクス、アノテーション）Kadai3.java
起動パラメータに指定された引数配列に対し、

・List型の変数に格納する
・List型の変数に格納された値を表示する
を行うプログラムを作成しなさい。

実行例
java Man ABC あいうえお 12345

＜出力例＞
ABC
あいうえお
12345