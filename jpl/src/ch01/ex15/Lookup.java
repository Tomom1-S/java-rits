package ch01.ex15;

public interface Lookup {
	// 柴田さん：インタフェース内で find を実装しておく

	/**
	 * name と関連付けされた値を追加する。 そのような値がなければ null を返す。
	 */
	Object find(String name);
}
