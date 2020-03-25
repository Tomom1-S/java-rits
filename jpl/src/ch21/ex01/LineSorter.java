package ch21.ex01;

import ch20.ex04.LineReader;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

public class LineSorter {
    public LinkedList<String> readFile(Reader in) throws IOException {
        final LineReader reader = new LineReader(in);

        final LinkedList<String> list = new LinkedList<>();
        String str = "";
        while (!(str = reader.readLine()).isEmpty()) {
            if (list.size() == 0) {
                list.add(str);
                continue;
            }
            int i = 0;
            while (i < list.size()) {
                if (str.compareTo(list.get(i)) > 0) {
                    list.add(i, str);
                    break;
                }
                i++;
                if (i == list.size()) {
                    list.addLast(str);
                    break;
                }
            }
            continue;
        }
        return list;
    }
}
