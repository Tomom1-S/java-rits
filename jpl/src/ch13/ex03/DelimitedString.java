package ch13.ex03;

public class DelimitedString {
    public static String delimitedString(String from, char start, char end) {
        int startPos = from.indexOf(start);
        int endPos = from.lastIndexOf(end);
        if (startPos == -1) {   // 開始文字が見つからない
            return null;
        } else if (endPos == -1) {   // 終了文字が見つからない
            return from.substring(startPos);
        } else if (startPos > endPos) { // 開始文字が終了文字の後にある
            return null;
        } else {
            return from.substring(startPos, endPos + 1);
        }
    }
}
