package ch06.ex05;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyFileReaderTest {
    @Test
    public void getWordで単語をひとつずつ取得する() {
        MyFileReader reader = null;
        try {
            reader = new MyFileReader(new File("test/ch06/ex05/ladybird.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final List<String> actualList = new ArrayList<>();
        try {
            String word;
            while ((word = reader.getWord()) != null) {
                actualList.add(word);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        final List<String> expectedList = Arrays.asList(new String[]{
                "Ladybird", "ladybird",
                "Fly", "away", "home",
                "Your", "house", "is", "on", "fire",
                "And", "your", "children", "all", "gone",
                "All", "except", "one",
                "And", "that", "s", "little", "Ann",
                "And", "she", "has", "crept", "under",
                "The", "warming", "pan"
        });

        assertThat(actualList.size(), is(expectedList.size()));
        for (
                int i = 0; i < actualList.size(); i++) {
            assertThat(actualList.get(i), is(expectedList.get(i)));
        }
    }

}