package ch21.ex06;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Concat {
    public static void main(final String[] args) throws IOException {
        InputStream in; // 文字を読み込むべきストリーム
        if (args.length == 0) {
            in = System.in;
        } else {
            InputStream fileIn, bufIn;
            final List<InputStream> inputs = new ArrayList<>(args.length);
            for (final String arg : args) {
                fileIn = new FileInputStream(arg);
                bufIn = new BufferedInputStream(fileIn);
                inputs.add(bufIn);
            }
            final Enumeration<InputStream> files = Collections.enumeration(inputs);
            in = new SequenceInputStream(files);
        }
        int ch;
        while ((ch = in.read()) != -1) {
            System.out.write(ch);
        }
        // ...

    }
}
