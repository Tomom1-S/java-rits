package ch20.ex02;

import java.io.*;

public class TranslateByteFilter extends FilterReader {
    private static byte from;
    private static byte to;

    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    protected TranslateByteFilter(Reader in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return (c == from ? to : c);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("2 arguments are needed!");
        }

        StringReader src = new StringReader(args[0]);
        FilterReader f = new TranslateByteFilter(src);
        from = (byte) args[1].charAt(0);
        to = (byte) args[2].charAt(0);
        int c;
        while ((c = f.read()) != -1) {
            System.out.print((char) c);
        }
        System.out.println();
    }

}
