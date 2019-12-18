package ch13.ex01;

public class CharacterCounter {
    String str;

    public CharacterCounter(String str) {
        this.str = str;
    }

    public int countCharacter(char ch) throws Exception {
        int begPos = str.indexOf(ch);
        if (begPos < 0) {
            throw new Exception(ch + " is not found in " + str);  // ch が見つからない
        }

        int count = 0;
        while (begPos >= 0) {
            begPos = str.indexOf(ch, ++begPos);
            count++;
        }

        return count;
    }
}
