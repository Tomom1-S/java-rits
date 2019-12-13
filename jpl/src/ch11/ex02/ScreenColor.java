package ch11.ex02;

import java.util.Objects;

public class ScreenColor {
    private Object value;

    // 練習問題用の仮のクラス
    public ScreenColor(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof ScreenColor)) {
            return false;
        }

        ScreenColor screenColor = (ScreenColor) object;
        return Objects.deepEquals(this.getValue(), screenColor.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getValue());
    }
}
