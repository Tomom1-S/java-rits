package ch20.ex03;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

public class EncryptOutputStream extends FilterReader {
    private static byte[] key;
    private static int index = 0;

    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    protected EncryptOutputStream(Reader in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        if (index >= key.length) {
            index = 0;
        }
        return (c == -1 ? c : c ^ key[index++]);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("2 arguments are needed!");
        }

        StringReader src = new StringReader(args[0]);
        FilterReader f = new EncryptOutputStream(src);
        String[] keyStr = args[1].split("");
        key = new byte[keyStr.length];
        for (int i = 0; i < key.length; i++) {
            key[i] = Byte.parseByte(keyStr[i], 2);
        }
        int c;
        while ((c = f.read()) != -1) {
            System.out.print((char) c);
        }
        System.out.println();
    }
}
