package ch20.ex06;

import java.io.FileReader;
import java.io.StreamTokenizer;

public class ExtractToken {
    public static void main(String[] args) throws Exception {
        FileReader fr = null;

        fr = new FileReader(args[0]);

        StreamTokenizer st = new StreamTokenizer(fr);

        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            switch (st.ttype) {
                case '"':
                    System.out.println("String = " + st.sval);
                    break;

                case StreamTokenizer.TT_EOL:
                    System.out.println("End-of-line");
                    break;

                case StreamTokenizer.TT_NUMBER:
                    System.out.println("Number = " + st.nval);
                    break;

                case StreamTokenizer.TT_WORD:
                    System.out.println("Word = " + st.sval);
                    break;

                default:
                    System.out.println("Other = " + (char) st.ttype);
            }
        }
    }
}
