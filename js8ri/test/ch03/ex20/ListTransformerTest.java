package ch03.ex20;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListTransformerTest {

    @Test
    public void mapの正常系() {
        final List<String> list = Arrays.asList(new String[] {"foo", "bar", "baz"});
        final List<Character> actual = ListTransformer.map(list, s -> s.charAt(0));

        final List<Character> expected = Arrays.asList(new Character[] {'f', 'b', 'b'});
        assertThat(actual, is(expected));

    }

}