package ch21.ex06;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyConcatTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void 複数ファイルを読み込む() {
        List<String> paths = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            File src = new File("test" + fs + "ch21" + fs + "ex06", "file" + i + ".txt");
            paths.add(src.getAbsolutePath());
        }

        try {
            MyConcat.main(paths.toArray(new String[paths.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected =
                // file0
                "Humpty Dumpty sat on a wall," + ls
                        + "Humpty Dumpty had a great fall;" + ls
                        + "All the king's horses and all the king's men" + ls
                        + "Couldn't put Humpty together again." + ls
                        // file1
                        + "Ladybird, ladybird," + ls
                        + "Fly away home," + ls
                        + "Your house is on fire" + ls
                        + "And your children all gone;" + ls
                        + "All except one" + ls
                        + "And that's little Ann," + ls
                        + "And she has crept under" + ls
                        + "The warming pan." + ls
                        // file2
                        + "Baa, baa, black sheep" + ls
                        + "Have you any wool?" + ls
                        + "Yes sir, yes sir, three bags full." + ls
                        + "One for the master," + ls
                        + "And one for the dame," + ls
                        + "And one for the little boy" + ls
                        + "Who lives down the lane." + ls;

        assertThat(outContent.toString(), is(expected));
    }

}