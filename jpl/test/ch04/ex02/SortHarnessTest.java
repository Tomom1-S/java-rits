package ch04.ex02;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SortHarnessTest {
    static Object[] objects = {"foo", 42, 123.45, 42};

    @Test
    public void doSortでオブジェクトをソートできる() {
        SortObject sObj = new SortHarness();
        sObj.sort(objects);
        Object[] expected = {123.45, 42, 42, "foo"};
        assertThat(objects, is(expected));
    }

}