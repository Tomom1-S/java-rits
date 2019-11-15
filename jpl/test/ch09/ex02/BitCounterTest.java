package ch09.ex02;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BitCounterTest {

    @Test
    public void countOnesが1の個数を返す() {
        BitCounter bc = new BitCounter();
        int val = 100;
        int actual = bc.countOnes(val);

        System.out.println(Integer.toBinaryString(val) + " has " + actual + " 1s.");
        assertThat(actual, is(Integer.bitCount(val)));
    }

}
