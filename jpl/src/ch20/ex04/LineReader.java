package ch20.ex04;

import java.io.*;

public class LineReader extends FilterReader {
    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    protected LineReader(Reader in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return (c == '\n' ? -2 : c);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("1 argument is needed!");
        }

        StringReader src = new StringReader(args[0]);
        LineReader f = new LineReader(src);

        OutputStream out = System.out;
        int c;
        while ((c = f.read()) != -1) {
            if (c < -1) {
                out.write('\n');
                out.flush();
            }
            out.write(c);
        }
        out.flush();
    }
}
