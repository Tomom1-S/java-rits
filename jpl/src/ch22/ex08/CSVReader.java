package ch22.ex08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
    static final int CELLS = 4;

    public static List<String[]> readCSVTable(final Readable source) throws IOException {
        final Scanner in = new Scanner(source);
        final List<String[]> vals = new ArrayList<>();
        final String exp = "^(.*),(.*),(.*),(.*)";
        final String invalidExp = "^(.*),(.*),(.*),(.*),(.*)";
        final Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
        final Pattern invalidPat = Pattern.compile(invalidExp, Pattern.MULTILINE);

        while (in.hasNextLine()) {
            if (in.findInLine(invalidPat) != null) {
                throw new IOException("input format error");
            }

            final String line = in.findInLine(pat);
            if (line == null) {
                in.nextLine();  // 改行を読み飛ばし
                continue;
            }

            final String[] cells = new String[CELLS];
            final MatchResult match = in.match();
            for (int i = 0; i < CELLS; i++) {
                cells[i] = match.group(i + 1);
            }
            vals.add(cells);
            in.nextLine();  // 改行を読み飛ばし

        }

        final IOException ex = in.ioException();
        if (ex != null) {
            throw ex;
        }
        return vals;
    }
}
