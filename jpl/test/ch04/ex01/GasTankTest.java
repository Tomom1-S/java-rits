package ch04.ex01;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GasTankTest {

    @Test
    public void コンストラクタで設定した値をgetで取得できる() {
        double expected = 10.5;
        GasTank gasTank = new GasTank(expected);
        assertThat(gasTank.getRemainingAmount(), is(expected));
    }

    @Test
    public void setで設定した値をgetで取得できる() {
        GasTank gasTank = new GasTank();
        double expected = 10.5;
        gasTank.setRemainingAmount(expected);
        assertThat(gasTank.getRemainingAmount(), is(expected));
    }

    @Test
    public void 負の値をsetしようとすると例外が発生する() {
        String expectMsg = "Remaining amount should be more than or equal to 0!";

        GasTank gasTank = new GasTank();
        Exception e = assertThrows(IllegalArgumentException.class, () -> gasTank.setRemainingAmount(-1));
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void 残量が0のときにemptyがtrueとなる() {
        GasTank gasTank = new GasTank(0);
        assertThat(gasTank.empty(), is(true));
    }

    @Test
    public void 残量が正の値のときにemptyがfalseとなる() {
        GasTank gasTank = new GasTank(5.0);
        assertThat(gasTank.empty(), is(false));
    }

}