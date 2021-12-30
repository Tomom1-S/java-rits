package ch20.ex08;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class EntryReaderTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    File inFile = new File("test" + fs + "ch20" + fs + "ex08", "in.txt");
    File outFile = new File("test" + fs + "ch20" + fs + "ex08", "out.txt");
    File expectedFile = new File("test" + fs + "ch20" + fs + "ex08", "expected.txt");


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void readEntries() throws IOException {
        EntryReader reader = new EntryReader(inFile);
        reader.readEntries();

//        assertEquals(
//                FileUtils.readFileToString(outFile, "utf-8"),
//                FileUtils.readFileToString(expectedFile, "utf-8")
//        );
    }
}