package ch09.ex01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExceptionHandler {
    public static void withoutTryWithResources() {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(Paths.get("/usr/share/dict/words"));

            try {
                out = new PrintWriter("/tmp/out.txt");
                while (in.hasNext()) {
                    out.println(in.next().toLowerCase());
                }
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    // p.213
    public static void withTryWithResources() {
        try (
                final Scanner in = new Scanner(Paths.get("/usr/share/dict/words"));
                final PrintWriter out = new PrintWriter("/tmp/out.txt")) {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
