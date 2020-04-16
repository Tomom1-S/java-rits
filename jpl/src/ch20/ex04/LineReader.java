package ch20.ex04;

import java.io.*;

public class LineReader extends FilterReader {
    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    public LineReader(Reader in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return (c == '\n' ? -2 : c);
    }

    public String readLine() throws IOException {
        final StringBuffer buffer = new StringBuffer();

        int c;
        while ((c = read()) != -1) {
            if (c < 0) {
                break;
            }
            buffer.append((char) c);
        }

        return buffer.toString();
    }
}
