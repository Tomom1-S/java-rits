package ch03.ex07;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ColorAttrTest {

    @Test
    public void nameだけ指定して同値のときにequalsがtrueを返す() {
        ColorAttr ca1 = new ColorAttr("foo");
        ColorAttr ca2 = new ColorAttr("foo");

        assertThat(ca1.equals(ca2), is(true));
    }

    @Test
    public void nameだけ指定して同値でないときにequalsがfalseを返す() {
        ColorAttr ca1 = new ColorAttr("foo");
        ColorAttr ca2 = new ColorAttr("bar");

        assertThat(ca1.equals(ca2), is(false));
    }

    @Test
    public void nameとvalueを指定して同値のときにequalsがtrueを返す() {
        int[] obj1 = {255, 255, 255};
        ColorAttr ca1 = new ColorAttr("foo", obj1);
        int[] obj2 = {255, 255, 255};
        ColorAttr ca2 = new ColorAttr("foo", obj2);

        assertThat(ca1.equals(ca2), is(true));
    }

    @Test
    public void nameとvalueを指定して同値でないときにequalsがfalseを返す() {
        int[] obj1 = {255, 255, 255};
        ColorAttr ca1 = new ColorAttr("foo", obj1);
        int obj2 = 42;
        ColorAttr ca2 = new ColorAttr("foo", obj2);

        assertThat(ca1.equals(ca2), is(false));
    }

    @Test
    public void nameとmyColorを指定して同値のときにequalsがtrueを返す() {
        List<String> obj1 = Arrays.asList("#000000", "#ffffff", "#ffffff");
        ColorAttr ca1 = new ColorAttr("foo", new ScreenColor(obj1));
        List<String> obj2 = Arrays.asList("#000000", "#ffffff", "#ffffff");
        ColorAttr ca2 = new ColorAttr("foo", new ScreenColor(obj2));

        assertThat(ca1.equals(ca2), is(true));
    }

    @Test
    public void nameとmyColorを指定して同値でないときにequalsがfalseを返す() {
        List<String> obj1 = Arrays.asList("#000000", "#ffffff", "#ffffff");
        ColorAttr ca1 = new ColorAttr("foo", new ScreenColor(obj1));
        String obj2 = "foo";
        ColorAttr ca2 = new ColorAttr("foo", new ScreenColor(obj2));

        assertThat(ca1.equals(ca2), is(false));
    }

    @Test
    public void 同値のときにhashCodeが同じ値を返す() {
        List<Integer> obj1 = Arrays.asList(1, 2, 3);
        ColorAttr ca1 = new ColorAttr("foo", new ScreenColor(obj1));
        List<Integer> obj2 = Arrays.asList(1, 2, 3);
        ColorAttr ca2 = new ColorAttr("foo", new ScreenColor(obj2));

        assertThat(ca1.hashCode(), is(ca2.hashCode()));
    }

}