package ch20.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByteExecutor {

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("2 arguments are needed!");
        }

        InputStream in = System.in;
        OutputStream out = System.out;
        byte from = (byte) args[0].charAt(0);
        byte to = (byte) args[1].charAt(0);
        try {
            out = translateByte(in, out, from, to);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OutputStream translateByte(InputStream in, OutputStream out, byte from, byte to) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b == from ? to : b);
        }
        return out;
    }

}
