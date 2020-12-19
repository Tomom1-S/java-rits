package ch01.ex10;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		MarkedNumber[] numbers = new MarkedNumber[MAX_INDEX + 1];
		for (int i = 0; i <= MAX_INDEX; i++) {
			int newValue;
			if (i <= 1) {
				newValue = 1;
			} else {
				newValue = numbers[i - 2].value + numbers[i - 1].value;
			}
			numbers[i] = new MarkedNumber(newValue, newValue % 2 == 0);
			System.out.println(numbers[i].toString());
		}
	}
}
