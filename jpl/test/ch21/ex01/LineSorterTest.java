package ch21.ex01;

import org.junit.Test;

import java.io.*;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LineSorterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final File diddle = new File("test" + fs + "ch21" + fs + "ex01", "diddle.txt");
    private final File failure = new File("test" + fs + "ch21" + fs + "ex01", "failure.txt");

    private final LineSorter sorter = new LineSorter();

    @Test
    public void ファイルを読み込んでソートされたListに保存する() throws IOException {
        final FileReader reader = new FileReader(diddle.getPath());
        final LinkedList<String> actual = sorter.readFile(reader);

        final LinkedList<String> expected = new LinkedList<>();
        expected.add("To see such sport,");
        expected.add("The little dog laughed");
        expected.add("The cow jumped over the moon.");
        expected.add("The cat and the fiddle,");
        expected.add("Hey, diddle, diddle,");
        expected.add("Hey, Diddle, Diddle");
        expected.add("And the dish ran away with the spoon.");

        assertThat(actual, is(expected));
    }

}