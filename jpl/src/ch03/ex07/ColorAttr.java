package ch03.ex07;

import java.util.Objects;

public class ColorAttr extends Attr {
    private ScreenColor myColor; // 変換された色

    public ColorAttr(String name, Object value) {
        super(name, value);
        decodeColor();
    }

    public ColorAttr(String name) {
        super(name, "transparent");
    }

    public ColorAttr(String name, ScreenColor value) {
        super(name, value.toString());
        myColor = value;
    }

    public Object setValue(Object newValue) {
        // スーパークラスの setValue を最初に行う
        Object retval = super.setValue(newValue);
        decodeColor();
        return retval;
    }

    /** 値を記述ではなく ScreenColor に設定する */
    public ScreenColor setValue(ScreenColor newValue) {
        // スーパークラスの setValue を最初に行う
        super.setValue(newValue.toString());
        ScreenColor oldValue = myColor;
        myColor = newValue;
        return oldValue;
    }

    /** 変換された ScreenColor オブジェクトを返す */
    public ScreenColor getColor() {
        return myColor;
    }

    /** getValue() で得られる記述から ScreenColor を設定する */
    protected void decodeColor() {
        if (getValue() == null) {
            myColor = null;
        } else {
            myColor = new ScreenColor(getValue());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof ColorAttr)) {
            return false;
        }

        // 柴田さん：キャストに失敗する場合はどうする？
        // キャストの前に if (!object instanceof ColorAttr) { return false } が必要
        // まず自分と同じインスタンスなのかを確認
        ColorAttr colorAttr = (ColorAttr)object;
        if (colorAttr.getColor() != null) {
            return this.getName() == colorAttr.getName()
                    && myColor.equals(colorAttr.getColor());
        }
        return this.getName() == colorAttr.getName()
                && Objects.deepEquals(this.getValue(), colorAttr.getValue());
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;   // 計算用の奇数の素数

        result = prime * result + Objects.hashCode(this.getName());
        result = prime * result + Objects.hashCode(this.getValue());
        result = prime * result + myColor.hashCode();

        System.out.println(result);
        System.out.println(Objects.hashCode(getName()) + ", " + Objects.hashCode(this.getValue()) + ", " + myColor.hashCode());
        return result;
    }
}
