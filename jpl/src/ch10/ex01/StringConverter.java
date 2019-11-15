package ch10.ex01;

public class StringConverter {
    public String convertSpecialChar(String str) {
        if (str.contains("\n")) {
            str.replaceAll("\n", "\\n");
        }
        if (str.contains("\t")) {
            str.replaceAll("\t", "\\t");
        }
        if (str.contains("\b")) {
            str.replaceAll("\b", "\\b");
        }
        if (str.contains("\r")) {
            str.replaceAll("\r", "\\r");
        }
        if (str.contains("\f")) {
            str.replaceAll("\f", "\\f");
        }
        if (str.contains("\\")) {
            str.replace("\\", "\\\\");
        }
        if (str.contains("\'")) {
            str.replaceAll("\'", "\\\'");
        }
        if (str.contains("\"")) {
            str.replaceAll("\"", "\\\"");
        }

        return str;
    }
}
