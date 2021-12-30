package ch16.ex11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerLoader extends ClassLoader {
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        try {
            byte[] buf = byteForClass(name);
            return defineClass(name, buf, 0, buf.length);
        } catch (final IOException e) {
            throw new ClassNotFoundException(e.toString());
        }
    }

    protected  byte[] byteForClass(final String name) throws IOException, ClassNotFoundException {
        try (FileInputStream in = steamFor(name + ".class")) {
            int length = in.available();    // バイト数を得る
            if (length == 0) {
                throw new ClassNotFoundException(name);
            }
            byte[] buf = new byte[length];
            in.read(buf);
            return buf;
        }
    }

    private FileInputStream steamFor(String s) throws FileNotFoundException {
        return new FileInputStream(s);
    }
}
