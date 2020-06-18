package ch20.ex06;

import java.io.*;
import java.util.HashMap;

public class Calculator {
    private static HashMap<String, Double> map = new HashMap() {{
        put("X", 0.0);
        put("Y", 0.0);
        put("Z", 0.0);
    }};

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Argument is needed");
        }

        FileReader reader = null;
        reader = new FileReader(args[0]);
        StreamTokenizer in = new StreamTokenizer(reader);
        in.ordinaryChar('+');
        in.ordinaryChar('-');
        in.ordinaryChar('=');

        String name = "";
        Operator op = null;
        double num = 0;
        boolean isNameSet = false;
        boolean isOpSet = false;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            switch (in.ttype) {
                case StreamTokenizer.TT_EOL:
                    break;
                case StreamTokenizer.TT_NUMBER:
                    if (!isNameSet || !isOpSet) {
                        throwInvalidContent(String.valueOf(in.nval));
                    }

                    num = in.nval;
                    map.put(name, op.apply(map.get(name), num));

                    isNameSet = false;
                    isOpSet = false;
                    break;
                case StreamTokenizer.TT_WORD:
                    if (isNameSet || isOpSet) {
                        throwInvalidContent(in.sval);
                    }

                    name = getValidName(in.sval);
                    isNameSet = true;
                    break;
                case '+':
                    if (!isNameSet || isOpSet) {
                        throwInvalidContent(in.ttype);
                    }

                    op = Operator.PLUS;
                    isOpSet = true;
                    break;
                case '-':
                    if (!isNameSet || isOpSet) {
                        throwInvalidContent(in.ttype);
                    }

                    op = Operator.MINUS;
                    isOpSet = true;
                    break;
                case '=':
                    if (!isNameSet || isOpSet) {
                        throwInvalidContent(in.ttype);
                    }

                    op = Operator.EQUAL;
                    isOpSet = true;
                    break;
                default:
                    throwInvalidContent(in.ttype);
            }
        }

        for (String key : map.keySet()) {
            System.out.printf("%s = %.5f\n", key, map.get(key));
        }

        // ファイルを閉じる
        reader.close();
    }

    private static String getValidName(final String str) {
        if (!Name.contains(str)) {
            throwInvalidContent(str);
        }
        return str;
    }

    private static void throwInvalidContent(int tokenType) {
        throw new IllegalArgumentException("Invalid contents: " + (char) tokenType);
    }

    private static void throwInvalidContent(String str) {
        throw new IllegalArgumentException("Invalid contents: " + str);
    }
}
