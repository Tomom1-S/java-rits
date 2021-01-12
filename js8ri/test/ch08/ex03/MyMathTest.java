package ch08.ex03;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyMathTest {
    @Test
    public void remの入力が2つとも正の数() {
        final int actual = MyMath.rem(34, 7);
        assertThat(actual, is(6));
    }

    @Test
    public void remの入力のうち被除数が負の数() {
        final int actual = MyMath.rem(-34, 7);
        assertThat(actual, is(1));
    }

    @Test
    public void remの入力のうち除数が負の数() {
        final int actual = MyMath.rem(34, -7);
        assertThat(actual, is(6));
    }

    @Test
    public void remの入力が2つとも負の数() {
        final int actual = MyMath.rem(-34, -7);
        assertThat(actual, is(1));
    }


}