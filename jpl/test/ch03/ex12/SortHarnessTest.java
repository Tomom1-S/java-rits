package ch03.ex12;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SortHarnessTest {
    static Object[] objects = {"foo", 42, 123.45};

    @Test
    public void doSortでオブジェクトをソートできる() {
        SortObject sObj = new SortHarness();
        sObj.sort(objects);
        Object[] expected = {123.45, 42, "foo"};
        assertThat(objects, is(expected));
    }

}