package ch11.ex02;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class ScreenColorTest {

    @Test
    public void 同値のときにequalsがtrueを返す() {
        List<String> obj1 = Arrays.asList("foo", "bar", "baz");
        ScreenColor sc1 = new ScreenColor(obj1);
        List<String> obj2 = Arrays.asList("foo", "bar", "baz");
        ScreenColor sc2 = new ScreenColor(obj2);

        assertThat(sc1.equals(sc2), is(true));
    }

    @Test
    public void 同値でないときにequalsがfalseを返す() {
        List<String> obj1 = Arrays.asList("foo", "bar", "baz");
        ScreenColor sc1 = new ScreenColor(obj1);
        List<String> obj2 = Arrays.asList("foo", "bar", "42");
        ScreenColor sc2 = new ScreenColor(obj2);

        assertThat(sc1.equals(sc2), is(false));
    }

    @Test
    public void 同値のときにhashCodeが同じ値を返す() {
        List<String> obj1 = Arrays.asList("foo", "bar", "baz");
        ScreenColor sc1 = new ScreenColor(obj1);
        List<String> obj2 = Arrays.asList("foo", "bar", "baz");
        ScreenColor sc2 = new ScreenColor(obj2);

        assertThat(sc1.equals(sc2), is(true));
        assertThat(sc1.hashCode(), is(sc2.hashCode()));
    }

    @Test
    public void 同値でないときにhashCodeが異なる値を返す() {
        List<String> obj1 = Arrays.asList("foo", "bar", "baz");
        ScreenColor sc1 = new ScreenColor(obj1);
        List<String> obj2 = Arrays.asList("foo", "bar", "42");
        ScreenColor sc2 = new ScreenColor(obj2);

        assertThat(sc1.equals(sc2), is(false));
        assertThat(sc1.hashCode(), is(not(sc2.hashCode())));
    }

}