package ch13.ex06;

public class delimitedStringNumber {
    public String addComma(String string, int digit) {
        if (digit <= 0) {
            throw new IllegalArgumentException("Digit should be positive!");
        }

        if (!string.matches("[0-9]+")) {
            throw new IllegalArgumentException("Only numbers can be accepted!");
        }

        int length = string.length();
        StringBuilder sb = new StringBuilder(string);
        int offset = length % digit;

        while (offset < length) {
            if (offset == 0) {
                offset += digit;
            }
            if (offset == length) {
                break;
            }
            sb.insert(offset, ",");
            offset = offset + digit + 1;
        }

        return sb.toString();
    }

    public String addComma(String string, char delimiter, int digit) {
        if (digit <= 0) {
            throw new IllegalArgumentException("Digit should be positive!");
        }

        if (!string.matches("[0-9]+")) {
            throw new IllegalArgumentException("Only numbers can be accepted!");
        }

        int length = string.length();
        StringBuilder sb = new StringBuilder(string);
        int offset = length % digit;

        while (offset < length) {
            if (offset == 0) {
                offset += digit;
            }
            if (offset == length) {
                break;
            }
            sb.insert(offset, delimiter);
            offset = offset + digit + 1;
        }

        return sb.toString();
    }
}
