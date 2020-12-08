package ch06.ex05;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFileReader implements Closeable {
    private final BufferedReader reader;
    private boolean finished = false;
    // 1行分の単語のリスト
    private final List<String> words = new ArrayList<>();

    public MyFileReader(final File file) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public String getWord() throws IOException {
        while (words.isEmpty()) {
            final String line = reader.readLine();

            // ファイルの終わり
            if (line == null) {
                finished = true;
                return null;
            }

            for (final String word : Arrays.asList(line.split("[\\P{L}]+"))) {
                words.add(word);
            }
        }
        return words.remove(0);
    }

    public boolean isFinished() {
        return finished;
    }
}
