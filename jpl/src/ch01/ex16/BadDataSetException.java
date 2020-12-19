package ch01.ex16;

import java.io.IOException;

public class BadDataSetException extends Exception {
	// 柴田さん：name を拾えるような getXxx を用意する
	// 柴田さん：super(e) で例外の内容を getCause() で取れるようにしておく

	/**
	 * データセットの例外を保持
	 */
	private static final long serialVersionUID = 1L;
	private static String name;
	private static IOException exception;
}
