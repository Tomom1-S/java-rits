package ch01.ex10;

public class MarkedNumber {
	int value;
	boolean isEven;

	public MarkedNumber(int value, boolean isEven) {
		this.value = value;
		this.isEven = isEven;
	}

	@Override
	public String toString() {
		final String mark = isEven ? " *" : "";
		return value + mark;
	}
}
