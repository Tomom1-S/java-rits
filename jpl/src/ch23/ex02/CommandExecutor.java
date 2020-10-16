package ch23.ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandExecutor {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("More than one arguments are needed!");
        }

        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);
        Process proc = builder.start();
        proc.waitFor();

        printInputStream(proc.getInputStream());
    }

    private static void printInputStream(final InputStream input) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input));) {
            int i = 1;
            for (; ; ) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }
                System.out.printf("%d: %s\n", i++, line);
            }
        }
    }
}
