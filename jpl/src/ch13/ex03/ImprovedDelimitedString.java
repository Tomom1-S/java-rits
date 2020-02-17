package ch13.ex03;

import ch13.ex02.StringCounter;

import java.util.ArrayList;
import java.util.List;

public class ImprovedDelimitedString {
    public static String[] delimitedStrings(String from, char start, char end) {
        List<String> list = new ArrayList<String>();

        int startPos = 0;;
        while (startPos >= 0) {
            startPos = from.indexOf(start, startPos);
            if (startPos == -1) {   // 開始文字が見つからない
                break;
            }

            int endPos = from.indexOf(end, ++startPos);
            if (endPos == -1) {   // 終了文字が見つからない
                String fragment = from.substring(startPos);
                if (fragment != null && !fragment.isEmpty()) {
                    list.add(fragment);
                }
                break;
            }
            // 柴田さん：開始文字・終了文字を含めた文字列を返す
            list.add(from.substring(startPos, endPos));
        }

        return list.toArray(new String[0]);
    }
}
