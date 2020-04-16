package ch20.ex03;

import java.io.IOException;

public class DecryptInputStream {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("2 arguments are needed!");
        }

        EncryptOutputStream.main(args);
    }
}
