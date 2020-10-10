package ch01.ex04;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileSorterTest {
    private final String fs = File.separator;

    private final FileSorter sorter = new FileSorter();
    private final File[] dirs = {
            new File("test" + fs + "ch01" + fs + "ex01"),
            new File("src" + fs + "ch01" + fs + "ex04"),
            new File("test" + fs + "ch01" + fs + "ex04"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "foo"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "bar"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "src1.json"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "src1.txt"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "src2.txt")
    };

    private final File[] expected = {
            new File("src" + fs + "ch01" + fs + "ex04"),
            new File("test" + fs + "ch01" + fs + "ex01"),
            new File("test" + fs + "ch01" + fs + "ex04"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "bar"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "foo"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "src1.json"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "src1.txt"),
            new File("test" + fs + "ch01" + fs + "ex04" + fs + "src2.txt")
    };

    @Test
    public void sortFilesの正常系() {
        final File[] actual = sorter.sortFiles(dirs);
        assertThat(actual, is(expected));
    }

    @Test
    public void sortFilesWithLambdaの正常系() {
        final File[] actual = sorter.sortFilesWithLambda(dirs);
        assertThat(actual, is(expected));
    }
}