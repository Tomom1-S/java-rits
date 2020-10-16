package ch21.ex04;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnotherShortStringsTest {
    final List<String> list = new ArrayList<>(Arrays.asList(new String[]{
            "abc", "defgh", "ijkl", "m", "no", "pgrstu", "vw", "xyz"
    }));
    final List<String> validList = new ArrayList<>(Arrays.asList(new String[]{
            "abc", "ijkl", "m", "no", "vw", "xyz"
    }));
    final ListIterator<String> it = list.listIterator();
    final int maxLen = 4;

    @Test
    public void nextの正常系() {
        final AnotherShortStrings strings = new AnotherShortStrings(it, maxLen);

        for (final String e : validList) {
            final String actualStr = strings.next();
            assertThat(actualStr, is(e));

            final boolean actualBln = strings.hasNext();
            if (e == validList.get(validList.size() - 1)) {
                assertFalse(actualBln);
                break;
            }
            assertTrue(actualBln);
        }
    }

    @Test
    public void previousの正常系() {
        final AnotherShortStrings strings = new AnotherShortStrings(it, maxLen);
        while (strings.hasNext()) { // 最後まで進める
            strings.next();
        }

        List<String> reversedValidList = validList;
        Collections.reverse(validList);
        for (final String e : reversedValidList) {
            final String actualStr = strings.previous();
            assertThat(actualStr, is(e));

            final boolean actualBln = strings.hasPrevious();
            if (e == reversedValidList.get(reversedValidList.size() - 1)) {
                assertFalse(actualBln);
                break;
            }
            assertTrue(actualBln);
        }
    }

    @Test
    public void removeの正常系() {
        final AnotherShortStrings strings = new AnotherShortStrings(it, maxLen);
        strings.next();
        strings.next();
        strings.remove();   // "ijkl" を削除

        final String actual = strings.next();
        final String expected = "m";
        assertThat(actual, is(expected));
    }

    @Test
    public void removeを2回続けて呼び出したときの正常系() {
        final AnotherShortStrings strings = new AnotherShortStrings(it, maxLen);
        strings.next();
        strings.previous();
        strings.remove();   // "abc" を削除
        strings.remove();

        final String actual = strings.next();
        final String expected = "ijkl";
        assertThat(actual, is(expected));
    }

}