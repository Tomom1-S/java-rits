package ch20.ex07;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AttrTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    File inFile = new File("test" + fs + "ch20" + fs + "ex07", "in.txt");
    File outFile = new File("test" + fs + "ch20" + fs + "ex07", "out.txt");
    File expectedFile = new File("test" + fs + "ch20" + fs + "ex07", "expected.txt");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void DataOutputStreamに状態を書き込む() throws IOException {
        Attr attr = new Attr("John Smith");
        attr.setValues(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        attr.writeData(outFile.getAbsolutePath());

        assertEquals(
                FileUtils.readFileToString(outFile, "utf-8"),
                FileUtils.readFileToString(expectedFile, "utf-8")
        );
    }

    @Test
    public void DataInputStreamから状態を読み込める() throws IOException {
        InputStream fin = new FileInputStream(inFile);
        DataInputStream in = new DataInputStream(fin);
        Attr attr = new Attr(in);

        assertThat(attr.getName(), is("foo"));
    }

}