package ch22.ex07;

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
        final Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
        while (in.hasNextLine()) {
            final String line = in.findInLine(pat);
            if (line != null) {
                final String[] cells = new String[CELLS];
                final MatchResult match = in.match();
                for (int i = 0; i < CELLS; i++) {
                    cells[i] = match.group(i + 1);
                }
                vals.add(cells);
                in.nextLine();  // 改行を読み飛ばし
            } else {
                throw new IOException("input format error");
            }
        }

        final IOException ex = in.ioException();
        if (ex != null) {
            throw ex;
        }
        return vals;
    }

    public static List<String[]> readCSVTable(final Readable source, final int cellNum) throws IOException {
        if (cellNum <= 0) {
            throw new IllegalArgumentException("cellNum should be positive.");
        }

        final Scanner in = new Scanner(source);
        final List<String[]> vals = new ArrayList<>();
        final String exp = createExp(cellNum);
        final Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
        while (in.hasNextLine()) {
            final String line = in.findInLine(pat);
            if (line != null) {
                final String[] cells = new String[CELLS];
                final MatchResult match = in.match();
                for (int i = 0; i < CELLS; i++) {
                    cells[i] = match.group(i + 1);
                }
                vals.add(cells);
                in.nextLine();  // 改行を読み飛ばし
            } else {
                throw new IOException("input format error");
            }
        }

        final IOException ex = in.ioException();
        if (ex != null) {
            throw ex;
        }
        return vals;
    }

    private static String createExp(final int times) {
        final StringBuilder sb = new StringBuilder("^(.*)");
        for (int i = 1; i < times; i++) {
            sb.append(",(.*)");
        }
        return sb.toString();
    }
}
