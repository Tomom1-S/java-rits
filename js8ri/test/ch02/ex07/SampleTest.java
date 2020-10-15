package ch02.ex07;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void isFiniteが有限ストリームでtrueを返す() {
        assertTrue(Sample.isFinite(Stream.of("A", "B", "C", "D", "E", "F")));
    }

    @Test
    public void isFiniteが無限ストリームでfalseを返す() {
        // TODO: このテストが通せていない
        assertFalse(Sample.isFinite(Stream.generate(() -> Math.random())));
    }

}