package ch23.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CommandExecutor {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 2) {
            throw new IllegalArgumentException("More than two arguments are needed!");
        }

        final String[] commandStr = Arrays.copyOfRange(args, 1, args.length);
        ProcessBuilder builder = new ProcessBuilder(commandStr);
        builder.redirectErrorStream(true);
        Process proc = builder.start();
        proc.waitFor();

        final String target = args[0];
        printInputStream(proc.getInputStream(), target);
    }

    private static void printInputStream(final InputStream input, final String target) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input));) {
            for (; ; ) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                } else if (line.contains(target)) {
                    System.out.println(line);
                    break;

                }
                System.out.println(line);
            }
        }
    }
}
