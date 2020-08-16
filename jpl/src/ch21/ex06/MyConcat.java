package ch21.ex06;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MyConcat {
    public static void main(final String[] args) throws IOException {
        InputStream in; // 文字を読み込むべきストリーム
        EnumerationImpl<InputStream> files;
        if (args.length == 0) {
            in = System.in;

            int ch;
            while ((ch = in.read()) != -1) {
                System.out.write(ch);
            }
            return;
        }

        InputStream fileIn, bufIn;
        final List<InputStream> inputs = new ArrayList<>(args.length);
        for (final String arg : args) {
            fileIn = new FileInputStream(arg);
            bufIn = new BufferedInputStream(fileIn);
            inputs.add(bufIn);
        }
        files = new EnumerationImpl(inputs);

        while (files.hasMoreElements()) {
            in = (InputStream) files.nextElement();
            int ch;
            while ((ch = in.read()) != -1) {
                System.out.write(ch);
            }
        }
        // ...

    }
}
