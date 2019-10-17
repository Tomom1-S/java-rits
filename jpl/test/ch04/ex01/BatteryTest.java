package ch04.ex01;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BatteryTest {

    @Test
    public void コンストラクタで設定した値をgetで取得できる() {
        double expected = 10.5;
        Battery battery = new Battery(expected);
        assertThat(battery.getRemainingAmount(), is(expected));
    }

    @Test
    public void setで設定した値をgetで取得できる() {
        Battery battery = new Battery();
        double expected = 10.5;
        battery.setRemainingAmount(expected);
        assertThat(battery.getRemainingAmount(), is(expected));
    }

    @Test
    public void 負の値をsetしようとすると例外が発生する() {
        String expectMsg = "Remaining amount should be more than or equal to 0!";

        Battery battery = new Battery();
        Exception e = assertThrows(IllegalArgumentException.class, () -> battery.setRemainingAmount(-1));
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void 残量が0のときにemptyがtrueとなる() {
        Battery battery = new Battery(0);
        assertThat(battery.empty(), is(true));
    }

    @Test
    public void 残量が正の値のときにemptyがfalseとなる() {
        Battery battery = new Battery(5.0);
        assertThat(battery.empty(), is(false));
    }

}