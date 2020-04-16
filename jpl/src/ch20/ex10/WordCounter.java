package ch20.ex10;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class WordCounter {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("File path is needed!");
        }

        final String path = args[0];
        final File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Not found: " + path);
        } else if (!file.isFile()) {
            throw new IllegalArgumentException("Not file: " + path);
        }

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        InputStream in = new FileInputStream(file);
        Reader reader = new BufferedReader(new InputStreamReader(in));

        StreamTokenizer st = new StreamTokenizer(reader);
        st.wordChars('+', '+');
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            if (st.ttype != StreamTokenizer.TT_WORD) {
                continue;
            }

            final String key = st.sval;
            Integer count = map.get(key);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            map.put(key, count);
        }

        TreeMap<String, Integer> copy = new TreeMap<>(map);
        map.clear();
        map.putAll(copy);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }


}
