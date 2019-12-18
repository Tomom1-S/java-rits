package ch13.ex02;

public class StringCounter {
    String str;

    public StringCounter(String str) {
        this.str = str;
    }

    public int countString(String target) throws Exception {
        int begPos = str.indexOf(target);
        if (begPos < 0) {
            throw new Exception(target + " is not found in " + str);  // target が見つからない
        }

        int count = 0;
        while (begPos >= 0) {
            begPos = str.indexOf(target, ++begPos);
            count++;
        }

        return count;
    }
}
