package ch06.ex06;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordMapperTest {
    @Test
    public void createの正常系() {
        // sample/shortText にある全ての txt ファイルを対象にテスト
        final List<File> files = Arrays.asList(
                new File("sample/shortText").getAbsoluteFile()
                        .listFiles(pathname -> pathname.getName().endsWith(".txt")));
        final List<String> list = files.stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());

        final HashMap<String, Set<File>> expected = new HashMap<>();
        expected.put("This", Set.of(files.get(0), files.get(1), files.get(2)));
        expected.put("That", Set.of(files.get(2)));
        expected.put("These", Set.of(files.get(1)));
        expected.put("is", Set.of(files.get(0), files.get(1), files.get(2)));
        expected.put("are", Set.of(files.get(1)));
        expected.put("not", Set.of(files.get(0), files.get(2)));
        expected.put("a", Set.of(files.get(0), files.get(1), files.get(2)));
        expected.put("file", Set.of(files.get(1), files.get(2)));
        expected.put("files", Set.of(files.get(1)));
        expected.put("folder", Set.of(files.get(0)));

        final ConcurrentHashMap<String, Set<File>> actual = WordMapper.create(list);
        assertThat(actual.get("This"), is(expected.get("This")));
        assertThat(actual.get("That"), is(expected.get("That")));
        assertThat(actual.get("These"), is(expected.get("These")));
        assertThat(actual.get("is"), is(expected.get("is")));
        assertThat(actual.get("are"), is(expected.get("are")));
        assertThat(actual.get("not"), is(expected.get("not")));
        assertThat(actual.get("a"), is(expected.get("a")));
        assertThat(actual.get("file"), is(expected.get("file")));
        assertThat(actual.get("files"), is(expected.get("files")));
        assertThat(actual.get("folder"), is(expected.get("folder")));
        // どのファイルにも含まれない単語
        assertThat(actual.containsKey("I"), is(false));
    }
}