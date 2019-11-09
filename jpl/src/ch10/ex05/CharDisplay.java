package ch10.ex05;

public class CharDisplay {
    public void showChars(char c1, char c2) {
        String str = "";
        char first = c1 <= c2 ? c1 : c2;
        char last = c1 <= c2 ? c2 : c1;
        for (char c = first; c <= last; c++) {
            str += c;
        }
        System.out.println(str);
    }
}
