package ch01.ex11;

public class StringsDemo {
	public static void main(String[] args) {
		String myName = "Petronius";
		String occupation = "Reorganization Specialist";
		String birthPlace = "Florida";

		myName += " Arbiter";
		myName += " ";
		myName += "(" + occupation + ")";
		myName += " ";
		myName += "was born in " + birthPlace;
		myName += ".";
		System.out.println("Introduction: " + myName);
	}
}
