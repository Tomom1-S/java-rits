package ch08.ex03;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EuclideanAlgorithmTest {
    @Test
    public void useModに正の数を入力したとき() {
        final int actual = EuclideanAlgorithm.useMod(36, 54);
        assertThat(actual, is(18));
    }

    @Test
    public void useModに負の数を入力したとき() {
        final int actual = EuclideanAlgorithm.useMod(36, -54);
        assertThat(actual, is(18));
    }

    @Test
    public void useModに0を入力したとき() {
        final int actual = EuclideanAlgorithm.useMod(0, 54);
        assertThat(actual, is(54));
    }

    @Test
    public void useFloorModに正の数を入力したとき() {
        final int actual = EuclideanAlgorithm.useFloorMod(36, 54);
        assertThat(actual, is(18));
    }

    @Test
    public void useFloorModに負の数を入力したとき() {
        final int actual = EuclideanAlgorithm.useFloorMod(36, -54);
        assertThat(actual, is(18));
    }

    @Test
    public void useFloorModに0を入力したとき() {
        final int actual = EuclideanAlgorithm.useFloorMod(0, 54);
        assertThat(actual, is(54));
    }

    @Test
    public void useRemに正の数を入力したとき() {
        final int actual = EuclideanAlgorithm.useRem(36, 54);
        assertThat(actual, is(18));
    }

    @Test
    public void useRemに負の数を入力したとき() {
        final int actual = EuclideanAlgorithm.useRem(36, -54);
        assertThat(actual, is(18));
    }

    @Test
    public void useRemに0を入力したとき() {
        final int actual = EuclideanAlgorithm.useRem(0, 54);
        assertThat(actual, is(54));
    }
}