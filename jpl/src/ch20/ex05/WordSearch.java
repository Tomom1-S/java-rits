package ch20.ex05;

import java.io.*;
import java.util.stream.Stream;

public class WordSearch {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("2 arguments are needed!");
        }

        String word = args[0];
        FileReader fileIn = new FileReader(args[1]);
        LineNumberReader in = new LineNumberReader(fileIn);

        boolean found = false;

        try {
            String line;
            while ((line = in.readLine()) != null) {

                if (!(line.toLowerCase()).contains(word.toLowerCase())) {
                    continue;
                }
                System.out.println("L" + in.getLineNumber() + ": " + line);
                found = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!found) {
            System.out.println(word + " not found");
        }
    }
}
