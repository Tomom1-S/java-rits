package ch13.ex05;

public class delimitedStringNumber {
    private final static int DIGIT = 3;

    public String addComma(String string) {
        if (!string.matches("[0-9]+")) {
            throw new IllegalArgumentException("Only numbers can be accepted!");
        }

        int length = string.length();
        StringBuilder sb = new StringBuilder(string);
        int offset = length % DIGIT;

        while (offset < length) {
            // 柴田さん：3桁以下のときにそのままの文字列を返す処理は外に出すとよい
            if (offset == 0) {
                offset += 3;
            }
            if (offset == length) {
                break;
            }
            sb.insert(offset, ",");
            offset = offset + DIGIT + 1;
        }

        return sb.toString();
    }
}
