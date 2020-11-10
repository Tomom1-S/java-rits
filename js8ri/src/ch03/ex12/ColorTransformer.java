package ch03.ex12;

import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);

    // ch03.ex11.Sample に入れたメソッドを移行
    // ch03.ex11.Sample.combineColorTransformers
    default ColorTransformer doAfterAnother(
            final ColorTransformer another
    ) {
        return (x, y, colorAtXY) ->
                apply(x, y, another.apply(x, y, colorAtXY));
    }

    // ch03.ex11.Sample に入れたメソッドを移行
    static ColorTransformer convertUnaryOperator(final UnaryOperator<Color> op) {
        return (_x, _y, colorAtXY) -> op.apply(colorAtXY);
    }
}
