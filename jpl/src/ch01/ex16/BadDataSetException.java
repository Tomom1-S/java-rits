package ch01.ex16;

import java.io.IOException;

public class BadDataSetException extends Exception {
	/**
	 * データセットの例外を保持
	 */
	private static final long serialVersionUID = 1L;
	private static String name;
	private static IOException exception;
}
