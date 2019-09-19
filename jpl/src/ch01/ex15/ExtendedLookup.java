package ch01.ex15;

public interface ExtendedLookup extends Lookup {
	/**
	 * name と関連付けされた値を設定する。
	 */
	void add(String name, Object value);

	/**
	 * name と関連付けされた値を削除する。
	 */
	void remove(String name);
}
